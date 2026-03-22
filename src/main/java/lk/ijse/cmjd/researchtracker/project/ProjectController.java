package lk.ijse.cmjd.researchtracker.project;

import lk.ijse.cmjd.researchtracker.project.dto.ProjectCreateDto;
import lk.ijse.cmjd.researchtracker.project.dto.ProjectDto;
import lk.ijse.cmjd.researchtracker.project.dto.ProjectStatusUpdateDto;
import lk.ijse.cmjd.researchtracker.project.dto.ProjectUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectCreateDto createDto) {
        ProjectDto createdProject = projectService.createProject(createDto);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> listAllProjects() {
        List<ProjectDto> projects = projectService.listAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> viewProjectDetails(@PathVariable String id) {
        ProjectDto project = projectService.viewProjectDetails(id);
        return ResponseEntity.ok(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable String id, @RequestBody ProjectUpdateDto updateDto) {
        ProjectDto updatedProject = projectService.updateProject(id, updateDto);
        return ResponseEntity.ok(updatedProject);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ProjectDto> updateProjectStatus(@PathVariable String id, @RequestBody ProjectStatusUpdateDto statusUpdateDto) {
        ProjectDto updatedProject = projectService.updateProjectStatus(id, statusUpdateDto);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable String id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
