package ag.pinguin.issuetracker.exception;

import ag.pinguin.issuetracker.logging.LogManager;

public final class GeneralExceptionHandler {
    private GeneralExceptionHandler() {
    }

    public static void handleException(Exception e) {
        LogManager.getInstance().writeLog("An error occured : ", e);
    }

    public static void handleException(String message, Exception e) {
        LogManager.getInstance().writeLog("An error occured" + message, e);
    }

    public static void writeInfo(String message) {
        LogManager.getInstance().writeInfoLog(message);
    }
}