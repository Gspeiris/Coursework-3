package lk.ijse.cmjd.researchtracker.milestone;

import lk.ijse.cmjd.researchtracker.project.Project;
import lk.ijse.cmjd.researchtracker.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "milestones")
public class Milestone {

    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    private String title;
    @Lob
    private String description;
    private LocalDate dueDate;
    private boolean isCompleted;
    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;
}
