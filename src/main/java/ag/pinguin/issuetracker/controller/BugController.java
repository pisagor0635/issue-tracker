package ag.pinguin.issuetracker.controller;

import ag.pinguin.issuetracker.model.BugAssignRequest;
import ag.pinguin.issuetracker.model.BugRequest;
import ag.pinguin.issuetracker.model.BugResponse;
import ag.pinguin.issuetracker.service.BugService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/bug")
@Api(tags = "Bug Operations")
public class BugController {

    private final BugService bugService;

    @PostMapping("/add")
    @ApiOperation(value = "add bug")
    public ResponseEntity<BugResponse> add(@RequestBody BugRequest bugRequest) {

        return ResponseEntity.ok(bugService.add(bugRequest));
    }

    @GetMapping("/list")
    @ApiOperation(value = "list all bugs")
    public ResponseEntity<List<BugResponse>> getBugs() {
        return ResponseEntity.ok(bugService.getBugs());
    }

    @PutMapping("/assign")
    @ApiOperation(value = "assign bug to developer")
    public ResponseEntity<BugResponse> assignToDeveloper(@RequestBody BugAssignRequest bugAssignRequest) {
        return ResponseEntity.ok(bugService.assignToDeveloper(bugAssignRequest.getBugId(), bugAssignRequest.getDeveloperId()));
    }

}
