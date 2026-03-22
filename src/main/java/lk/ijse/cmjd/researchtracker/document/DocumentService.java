package lk.ijse.cmjd.researchtracker.document;

import lk.ijse.cmjd.researchtracker.document.dto.DocumentCreateDto;
import lk.ijse.cmjd.researchtracker.document.dto.DocumentDto;
import lk.ijse.cmjd.researchtracker.document.mapper.DocumentMapper;
import lk.ijse.cmjd.researchtracker.project.Project;
import lk.ijse.cmjd.researchtracker.project.ProjectRepository;
import lk.ijse.cmjd.researchtracker.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final ProjectRepository projectRepository;
    private final DocumentMapper documentMapper;

    public DocumentDto uploadDocument(String projectId, DocumentCreateDto createDto) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // In a real app, you'd handle file storage here and get a URL/path
        // For now, we'll use the one from the DTO
        String fileUrlOrPath = createDto.getUrlOrPath();

        Document document = Document.builder()
                .id(UUID.randomUUID().toString())
                .project(project)
                .title(createDto.getTitle())
                .description(createDto.getDescription())
                .urlOrPath(fileUrlOrPath)
                .uploadedBy(currentUser)
                .uploadedAt(LocalDateTime.now())
                .build();

        Document savedDocument = documentRepository.save(document);
        return documentMapper.toDto(savedDocument);
    }

    public List<DocumentDto> listDocumentsForProject(String projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new RuntimeException("Project not found");
        }
        return documentRepository.findAllByProjectId(projectId).stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteDocument(String id) {
        if (!documentRepository.existsById(id)) {
            throw new RuntimeException("Document not found");
        }
        documentRepository.deleteById(id);
    }
}
