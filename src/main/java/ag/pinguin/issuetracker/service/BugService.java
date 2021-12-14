package ag.pinguin.issuetracker.service;

import ag.pinguin.issuetracker.model.BugRequest;
import ag.pinguin.issuetracker.model.BugResponse;

import java.util.List;

public interface BugService {

    BugResponse add(BugRequest bugRequest);

    BugResponse assignToDeveloper(Long bugId, Long developerId);

    List<BugResponse> getBugs();
}
