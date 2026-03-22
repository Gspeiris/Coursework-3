package lk.ijse.cmjd.researchtracker.milestone.dto;

import lk.ijse.cmjd.researchtracker.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MilestoneDto {
    private String id;
    private String projectId;
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean isCompleted;
    private UserDto createdBy;
}
