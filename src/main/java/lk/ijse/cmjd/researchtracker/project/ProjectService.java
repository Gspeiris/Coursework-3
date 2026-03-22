package lk.ijse.cmjd.researchtracker.project;

import lk.ijse.cmjd.researchtracker.project.dto.ProjectCreateDto;
import lk.ijse.cmjd.researchtracker.project.dto.ProjectDto;
import lk.ijse.cmjd.researchtracker.project.dto.ProjectStatusUpdateDto;
import lk.ijse.cmjd.researchtracker.project.dto.ProjectUpdateDto;
import lk.ijse.cmjd.researchtracker.project.mapper.ProjectMapper;
import lk.ijse.cmjd.researchtracker.user.User;
import lk.ijse.cmjd.researchtracker.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMapper projectMapper;

    public ProjectDto createProject(ProjectCreateDto createDto) {
        User pi = userRepository.findById(createDto.getPiId())
                .orElseThrow(() -> new UsernameNotFoundException("Principal Investigator not found"));

        Project project = Project.builder()
                .id(UUID.randomUUID().toString())
                .title(createDto.getTitle())
                .summary(createDto.getSummary())
                .status(Status.PLANNING)
                .pi(pi)
                .tags(createDto.getTags())
                .startDate(createDto.getStartDate())
                .endDate(createDto.getEndDate())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Project savedProject = projectRepository.save(project);
        return projectMapper.toDto(savedProject);
    }

    public List<ProjectDto> listAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProjectDto viewProjectDetails(String id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return projectMapper.toDto(project);
    }

    public ProjectDto updateProject(String id, ProjectUpdateDto updateDto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        User pi = userRepository.findById(updateDto.getPiId())
                .orElseThrow(() -> new UsernameNotFoundException("Principal Investigator not found"));

        project.setTitle(updateDto.getTitle());
        project.setSummary(updateDto.getSummary());
        project.setPi(pi);
        project.setTags(updateDto.getTags());
        project.setStartDate(updateDto.getStartDate());
        project.setEndDate(updateDto.getEndDate());
        project.setUpdatedAt(LocalDateTime.now());

        Project updatedProject = projectRepository.save(project);
        return projectMapper.toDto(updatedProject);
    }

    public ProjectDto updateProjectStatus(String id, ProjectStatusUpdateDto statusUpdateDto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setStatus(statusUpdateDto.getStatus());
        project.setUpdatedAt(LocalDateTime.now());

        Project updatedProject = projectRepository.save(project);
        return projectMapper.toDto(updatedProject);
    }

    public void deleteProject(String id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Project not found");
        }
        projectRepository.deleteById(id);
    }
}
