package lk.ijse.cmjd.researchtracker.project.dto;

import lk.ijse.cmjd.researchtracker.project.Status;
import lk.ijse.cmjd.researchtracker.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private String id;
    private String title;
    private String summary;
    private Status status;
    private UserDto pi;
    private String tags;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
