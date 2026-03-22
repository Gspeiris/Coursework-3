package lk.ijse.cmjd.researchtracker.document;

import lk.ijse.cmjd.researchtracker.document.dto.DocumentCreateDto;
import lk.ijse.cmjd.researchtracker.document.dto.DocumentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/projects/{projectId}/documents")
    public ResponseEntity<DocumentDto> uploadDocument(
            @PathVariable String projectId,
            @RequestBody DocumentCreateDto createDto
    ) {
        DocumentDto uploadedDocument = documentService.uploadDocument(projectId, createDto);
        return new ResponseEntity<>(uploadedDocument, HttpStatus.CREATED);
    }

    @GetMapping("/projects/{projectId}/documents")
    public ResponseEntity<List<DocumentDto>> listDocumentsForProject(@PathVariable String projectId) {
        List<DocumentDto> documents = documentService.listDocumentsForProject(projectId);
        return ResponseEntity.ok(documents);
    }

    @DeleteMapping("/documents/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable String id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }
}
