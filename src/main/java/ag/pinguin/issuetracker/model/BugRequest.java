package ag.pinguin.issuetracker.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BugRequest {

    private Long issueId;
    private String title;
    private String description;
    private String status;
    private String priority;
}
