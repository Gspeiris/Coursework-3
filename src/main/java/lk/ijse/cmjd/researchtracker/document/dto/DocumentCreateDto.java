package lk.ijse.cmjd.researchtracker.document.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentCreateDto {
    private String title;
    private String description;
    // In a real application, this would likely be a MultipartFile
    // and the service would handle storage and generate the URL/path.
    private String urlOrPath;
}
