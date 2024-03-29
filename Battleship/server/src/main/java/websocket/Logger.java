package websocket;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static void info(String message) {
        log("INFO", message);
    }

    public static void warning(String message) {
        log("WARNING", message);
    }

    public static void error(String message) {
        log("ERROR", message);
    }

    private static void log(String type, String message) {
        Date now = new Date();
        String formattedDate = dateFormat.format(now);
        String logEntry = String.format("[%s] %s - %s", type, formattedDate, message);
        System.out.println(logEntry);
    }

    public static void main(String[] args) {
        Logger.info("This is an information message.");
        Logger.warning("This is a warning message.");
        Logger.error("This is an error message.");
    }
}