package ag.pinguin.issuetracker.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class GeneralExceptionHandler {
    private GeneralExceptionHandler() {
    }

    public static void handleException(Exception e) {
        log.error("An error occured : ", e);
    }

    public static void handleException(String message, Exception e) {
        log.error("An error occured" + message, e);
    }

    public static void writeInfo(String message) {
        log.info(message);
    }
}