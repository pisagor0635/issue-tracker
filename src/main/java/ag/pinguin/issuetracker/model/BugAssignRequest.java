package ag.pinguin.issuetracker.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugAssignRequest {
    private Long bugId;
    private Long developerId;
}
