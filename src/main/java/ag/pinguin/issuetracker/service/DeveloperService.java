package ag.pinguin.issuetracker.service;

import ag.pinguin.issuetracker.model.DeveloperRequest;
import ag.pinguin.issuetracker.model.DeveloperResponse;

import java.util.List;
import java.util.Map;

public interface DeveloperService {

    DeveloperResponse add(DeveloperRequest developerRequest);

    List<DeveloperResponse> getDevelopers();

    Map<String, Boolean> remove(Long id);
}
