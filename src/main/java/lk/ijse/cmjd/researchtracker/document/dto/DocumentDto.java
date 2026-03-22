package lk.ijse.cmjd.researchtracker.document.dto;

import lk.ijse.cmjd.researchtracker.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto {
    private String id;
    private String projectId;
    private String title;
    private String description;
    private String urlOrPath;
    private UserDto uploadedBy;
    private LocalDateTime uploadedAt;
}
