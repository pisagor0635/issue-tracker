package ag.pinguin.issuetracker.controller;

import ag.pinguin.issuetracker.model.StoryAssignRequest;
import ag.pinguin.issuetracker.model.StoryRequest;
import ag.pinguin.issuetracker.model.StoryResponse;
import ag.pinguin.issuetracker.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/story")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @PostMapping("/add")
    public ResponseEntity<StoryResponse> add(@RequestBody StoryRequest storyRequest){
        return ResponseEntity.ok(storyService.add(storyRequest));
    }

    @GetMapping("/list")
    public ResponseEntity<List<StoryResponse>> getStories() {
        return ResponseEntity.ok(storyService.getStories());
    }

    @PutMapping("/assign")
    public ResponseEntity<StoryResponse> assignToDeveloper(@RequestBody StoryAssignRequest storyAssignRequest) {
        return ResponseEntity.ok(storyService.assignToDeveloper(storyAssignRequest.getStoryId(), storyAssignRequest.getDeveloperId()));
    }


}
