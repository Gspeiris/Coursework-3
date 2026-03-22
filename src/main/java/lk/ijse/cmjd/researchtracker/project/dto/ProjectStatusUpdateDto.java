package lk.ijse.cmjd.researchtracker.project.dto;

import lk.ijse.cmjd.researchtracker.project.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectStatusUpdateDto {
    private Status status;
}
