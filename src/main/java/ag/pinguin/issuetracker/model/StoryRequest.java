package ag.pinguin.issuetracker.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StoryRequest {

    private Long issueId;
    private String title;
    private String description;
    private int storyPoint;
    private String status;
    private Date fromDate;
    private Date toDate;
    private Date sprintStartDate;
    private Date sprintEndDate;

}
