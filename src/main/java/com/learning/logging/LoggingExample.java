package com.learning.logging;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggingExample {
    static Logger logger = LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME);

    void logMessage(){
        logger.log(Level.INFO,"This is a sample log message");
    }

    public static void main(String[] args) {
        new LoggingExample().logMessage();
    }
}

class LoggerHandler{
    static Logger logger = Logger.getLogger("com.learning.logging");
    public static void main(String[] args) throws IOException {
        logger.log(Level.INFO,"This is a sample log message");
    }

}
