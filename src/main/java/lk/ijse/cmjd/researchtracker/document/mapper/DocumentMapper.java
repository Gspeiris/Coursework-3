package lk.ijse.cmjd.researchtracker.document.mapper;

import lk.ijse.cmjd.researchtracker.document.Document;
import lk.ijse.cmjd.researchtracker.document.dto.DocumentDto;
import lk.ijse.cmjd.researchtracker.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentMapper {

    private final UserMapper userMapper;

    public DocumentDto toDto(Document document) {
        if (document == null) {
            return null;
        }
        return new DocumentDto(
                document.getId(),
                document.getProject().getId(),
                document.getTitle(),
                document.getDescription(),
                document.getUrlOrPath(),
                userMapper.toDto(document.getUploadedBy()),
                document.getUploadedAt()
        );
    }
}
