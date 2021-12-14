package ag.pinguin.issuetracker.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoryResponse {
    private Long issueId;
    private String title;
    private String description;
    private int storyPoint;
    private String status;
    private DeveloperResponseWithoutStory developer;
}
