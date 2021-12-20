package ag.pinguin.issuetracker.controller;

import ag.pinguin.issuetracker.model.StoryAssignRequest;
import ag.pinguin.issuetracker.model.StoryRequest;
import ag.pinguin.issuetracker.model.StoryResponse;
import ag.pinguin.issuetracker.service.StoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/story")
@Api(tags = "Story Operations")
public class StoryController {

    private final StoryService storyService;

    @PostMapping("/add")
    @ApiOperation(value = "add story")
    public ResponseEntity<StoryResponse> add(@RequestBody StoryRequest storyRequest) {
        return ResponseEntity.ok(storyService.add(storyRequest));
    }

    @GetMapping("/list")
    @ApiOperation(value = "list all stories")
    public ResponseEntity<List<StoryResponse>> getStories() {
        return ResponseEntity.ok(storyService.getStories());
    }

    @PutMapping("/assign")
    @ApiOperation(value = "assign story to developer")
    public ResponseEntity<StoryResponse> assignToDeveloper(@RequestBody StoryAssignRequest storyAssignRequest) {
        return ResponseEntity.ok(storyService.assignToDeveloper(storyAssignRequest.getStoryId(), storyAssignRequest.getDeveloperId()));
    }


}
