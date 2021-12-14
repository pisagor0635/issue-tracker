package ag.pinguin.issuetracker.controller;

import ag.pinguin.issuetracker.model.DeveloperRequest;
import ag.pinguin.issuetracker.model.DeveloperResponse;
import ag.pinguin.issuetracker.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/developer")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @PostMapping("/add")
    public ResponseEntity<DeveloperResponse> add(@RequestBody DeveloperRequest developerRequest) {
        return ResponseEntity.ok(developerService.add(developerRequest));
    }
}
