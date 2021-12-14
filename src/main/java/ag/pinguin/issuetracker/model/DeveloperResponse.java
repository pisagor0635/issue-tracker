package ag.pinguin.issuetracker.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class DeveloperResponse {
    private String name;
    private Set<StoryResponse> stories;
    private Set<BugResponse> bugs;
}
