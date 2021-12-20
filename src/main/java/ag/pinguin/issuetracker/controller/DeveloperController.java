package ag.pinguin.issuetracker.controller;

import ag.pinguin.issuetracker.model.DeveloperRequest;
import ag.pinguin.issuetracker.model.DeveloperResponse;
import ag.pinguin.issuetracker.service.DeveloperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/developer")
@RequiredArgsConstructor
@Api(tags = "Developer Operations")
public class DeveloperController {

    private final DeveloperService developerService;

    @PostMapping("/add")
    @ApiOperation(value = "add developer")
    public ResponseEntity<DeveloperResponse> add(@RequestBody DeveloperRequest developerRequest) {
        return ResponseEntity.ok(developerService.add(developerRequest));
    }

    @GetMapping("/list")
    @ApiOperation(value = "list all developers")
    public ResponseEntity<List<DeveloperResponse>> developers(){
        return ResponseEntity.ok(developerService.getDevelopers());
    }

    @DeleteMapping("/remove/{id}")
    @ApiOperation(value = "delete developer")
    public ResponseEntity<Map<String, Boolean>> remove(@PathVariable Long id) {
        return ResponseEntity.ok(developerService.remove(id));
    }
}
