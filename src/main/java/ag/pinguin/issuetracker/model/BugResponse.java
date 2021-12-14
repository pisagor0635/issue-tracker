package ag.pinguin.issuetracker.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugResponse {
    private Long issueId;
    private String title;
    private String description;
    private String status;
    private String priority;
    private DeveloperResponseWithoutStory developer;
}
