package lk.ijse.cmjd.researchtracker.milestone;

import lk.ijse.cmjd.researchtracker.milestone.dto.MilestoneCreateDto;
import lk.ijse.cmjd.researchtracker.milestone.dto.MilestoneDto;
import lk.ijse.cmjd.researchtracker.milestone.dto.MilestoneUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MilestoneController {

    private final MilestoneService milestoneService;

    @PostMapping("/projects/{projectId}/milestones")
    public ResponseEntity<MilestoneDto> addMilestone(
            @PathVariable String projectId,
            @RequestBody MilestoneCreateDto createDto
    ) {
        MilestoneDto createdMilestone = milestoneService.addMilestone(projectId, createDto);
        return new ResponseEntity<>(createdMilestone, HttpStatus.CREATED);
    }

    @GetMapping("/projects/{projectId}/milestones")
    public ResponseEntity<List<MilestoneDto>> listMilestonesForProject(@PathVariable String projectId) {
        List<MilestoneDto> milestones = milestoneService.listMilestonesForProject(projectId);
        return ResponseEntity.ok(milestones);
    }

    @PutMapping("/milestones/{id}")
    public ResponseEntity<MilestoneDto> updateMilestone(
            @PathVariable String id,
            @RequestBody MilestoneUpdateDto updateDto
    ) {
        MilestoneDto updatedMilestone = milestoneService.updateMilestone(id, updateDto);
        return ResponseEntity.ok(updatedMilestone);
    }

    @DeleteMapping("/milestones/{id}")
    public ResponseEntity<Void> deleteMilestone(@PathVariable String id) {
        milestoneService.deleteMilestone(id);
        return ResponseEntity.noContent().build();
    }
}
