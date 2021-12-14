package ag.pinguin.issuetracker.controller;

import ag.pinguin.issuetracker.model.DeveloperRequest;
import ag.pinguin.issuetracker.model.DeveloperResponse;
import ag.pinguin.issuetracker.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/developer")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @PostMapping("/add")
    public ResponseEntity<DeveloperResponse> add(@RequestBody DeveloperRequest developerRequest) {
        return ResponseEntity.ok(developerService.add(developerRequest));
    }

    @GetMapping("/list")
    public ResponseEntity<List<DeveloperResponse>> developers(){
        return ResponseEntity.ok(developerService.getDevelopers());
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Map<String, Boolean>> remove(@PathVariable Long id) {
        return ResponseEntity.ok(developerService.remove(id));
    }
}
