package lk.ijse.cmjd.researchtracker.milestone;

import lk.ijse.cmjd.researchtracker.milestone.dto.MilestoneCreateDto;
import lk.ijse.cmjd.researchtracker.milestone.dto.MilestoneDto;
import lk.ijse.cmjd.researchtracker.milestone.dto.MilestoneUpdateDto;
import lk.ijse.cmjd.researchtracker.milestone.mapper.MilestoneMapper;
import lk.ijse.cmjd.researchtracker.project.Project;
import lk.ijse.cmjd.researchtracker.project.ProjectRepository;
import lk.ijse.cmjd.researchtracker.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;
    private final MilestoneMapper milestoneMapper;

    public MilestoneDto addMilestone(String projectId, MilestoneCreateDto createDto) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Milestone milestone = Milestone.builder()
                .id(UUID.randomUUID().toString())
                .project(project)
                .title(createDto.getTitle())
                .description(createDto.getDescription())
                .dueDate(createDto.getDueDate())
                .isCompleted(false)
                .createdBy(currentUser)
                .build();

        Milestone savedMilestone = milestoneRepository.save(milestone);
        return milestoneMapper.toDto(savedMilestone);
    }

    public List<MilestoneDto> listMilestonesForProject(String projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new RuntimeException("Project not found");
        }
        return milestoneRepository.findAllByProjectId(projectId).stream()
                .map(milestoneMapper::toDto)
                .collect(Collectors.toList());
    }

    public MilestoneDto updateMilestone(String id, MilestoneUpdateDto updateDto) {
        Milestone milestone = milestoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Milestone not found"));

        milestone.setTitle(updateDto.getTitle());
        milestone.setDescription(updateDto.getDescription());
        milestone.setDueDate(updateDto.getDueDate());
        milestone.setCompleted(updateDto.isCompleted());

        Milestone updatedMilestone = milestoneRepository.save(milestone);
        return milestoneMapper.toDto(updatedMilestone);
    }

    public void deleteMilestone(String id) {
        if (!milestoneRepository.existsById(id)) {
            throw new RuntimeException("Milestone not found");
        }
        milestoneRepository.deleteById(id);
    }
}
