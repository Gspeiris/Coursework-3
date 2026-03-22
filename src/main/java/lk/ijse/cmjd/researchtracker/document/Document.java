package lk.ijse.cmjd.researchtracker.document;

import lk.ijse.cmjd.researchtracker.project.Project;
import lk.ijse.cmjd.researchtracker.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "documents")
public class Document {

    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    private String title;
    @Lob
    private String description;
    private String urlOrPath;
    @ManyToOne
    @JoinColumn(name = "uploaded_by_id")
    private User uploadedBy;
    private LocalDateTime uploadedAt;
}
