package ag.pinguin.issuetracker.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoryAssignRequest {
    private Long storyId;
    private Long developerId;
}
