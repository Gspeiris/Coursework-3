package lk.ijse.cmjd.researchtracker.project.mapper;

import lk.ijse.cmjd.researchtracker.project.Project;
import lk.ijse.cmjd.researchtracker.project.dto.ProjectDto;
import lk.ijse.cmjd.researchtracker.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectMapper {

    private final UserMapper userMapper;

    public ProjectDto toDto(Project project) {
        if (project == null) {
            return null;
        }
        return new ProjectDto(
                project.getId(),
                project.getTitle(),
                project.getSummary(),
                project.getStatus(),
                userMapper.toDto(project.getPi()),
                project.getTags(),
                project.getStartDate(),
                project.getEndDate(),
                project.getCreatedAt(),
                project.getUpdatedAt()
        );
    }
}
