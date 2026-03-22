package lk.ijse.cmjd.researchtracker.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCreateDto {
    private String title;
    private String summary;
    private String piId;
    private String tags;
    private LocalDate startDate;
    private LocalDate endDate;
}
