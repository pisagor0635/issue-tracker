package ag.pinguin.issuetracker.service;

import ag.pinguin.issuetracker.model.DeveloperRequest;
import ag.pinguin.issuetracker.model.DeveloperResponse;

public interface DeveloperService {

    DeveloperResponse add(DeveloperRequest developerRequest);

}
