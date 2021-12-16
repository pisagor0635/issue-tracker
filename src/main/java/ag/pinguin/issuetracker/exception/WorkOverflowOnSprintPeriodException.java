package ag.pinguin.issuetracker.exception;

public class WorkOverflowOnSprintPeriodException extends RuntimeException {
    public WorkOverflowOnSprintPeriodException(String message) {
        super(message);
    }
}
