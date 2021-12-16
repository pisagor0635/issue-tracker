package ag.pinguin.issuetracker.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    private String status;
    private String message;
}
