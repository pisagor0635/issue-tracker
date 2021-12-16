package ag.pinguin.issuetracker.exception;

import ag.pinguin.issuetracker.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler({Exception.class})
    public final ResponseEntity<ErrorResponse> handleAllExceptions(RuntimeException ex) {
        GeneralExceptionHandler.handleException(ex);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public final ResponseEntity<ErrorResponse> handleResourceNotFoundException(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.toString());
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({WorkOverflowOnSprintPeriodException.class})
    public final ResponseEntity<ErrorResponse> handleWorkOverflowOnSprintPeriodException(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus("WORK OVER FLOW");
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler({DuplicateEntryException.class})
    public final ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus("Data Integrity Violation");
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
