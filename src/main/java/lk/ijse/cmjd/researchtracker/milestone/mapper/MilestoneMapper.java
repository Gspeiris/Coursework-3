package lk.ijse.cmjd.researchtracker.milestone.mapper;

import lk.ijse.cmjd.researchtracker.milestone.Milestone;
import lk.ijse.cmjd.researchtracker.milestone.dto.MilestoneDto;
import lk.ijse.cmjd.researchtracker.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MilestoneMapper {

    private final UserMapper userMapper;

    public MilestoneDto toDto(Milestone milestone) {
        if (milestone == null) {
            return null;
        }
        return new MilestoneDto(
                milestone.getId(),
                milestone.getProject().getId(),
                milestone.getTitle(),
                milestone.getDescription(),
                milestone.getDueDate(),
                milestone.isCompleted(),
                userMapper.toDto(milestone.getCreatedBy())
        );
    }
}
