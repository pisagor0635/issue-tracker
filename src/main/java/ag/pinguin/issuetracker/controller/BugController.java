package ag.pinguin.issuetracker.controller;

import ag.pinguin.issuetracker.model.BugAssignRequest;
import ag.pinguin.issuetracker.model.BugRequest;
import ag.pinguin.issuetracker.model.BugResponse;
import ag.pinguin.issuetracker.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/bug")
public class BugController {

    @Autowired
    private BugService bugService;

    @PostMapping("/add")
    public ResponseEntity<BugResponse> add(@RequestBody BugRequest bugRequest) {

        return ResponseEntity.ok(bugService.add(bugRequest));
    }

    @GetMapping("/list")
    public ResponseEntity<List<BugResponse>> getBugs() {
        return ResponseEntity.ok(bugService.getBugs());
    }

    @PutMapping("/assign")
    public ResponseEntity<BugResponse> assignToDeveloper(@RequestBody BugAssignRequest bugAssignRequest) {
        return ResponseEntity.ok(bugService.assignToDeveloper(bugAssignRequest.getBugId(), bugAssignRequest.getDeveloperId()));
    }

}
