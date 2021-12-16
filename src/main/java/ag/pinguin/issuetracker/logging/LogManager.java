package ag.pinguin.issuetracker.logging;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogManager {

    private static LogManager instance;

    private LogManager() {
    }

    public static LogManager getInstance() {
        if (instance == null) {
            synchronized (LogManager.class) {
                instance = new LogManager();
            }
        }

        return instance;
    }

    public void writeLog(String message, Exception e) {
        log.error(message, e);
    }

    public void writeInfoLog(String message) {
        log.info(message);
    }
}