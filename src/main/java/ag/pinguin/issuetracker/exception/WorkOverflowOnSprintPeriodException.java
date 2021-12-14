package ag.pinguin.issuetracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WorkOverflowOnSprintPeriodException extends RuntimeException {
    public WorkOverflowOnSprintPeriodException(String message) {
        super(message);
    }
}
