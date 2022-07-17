package org.sofka.utilities;


import org.jboss.logging.Logger;

public class PrintLogger {

    private static final Logger LOGGER = Logger.getLogger(PrintLogger.class);

    private PrintLogger() {
        throw new IllegalStateException("Utility class");
    }

    public static void printMessage(String message) {
        LOGGER.info("\n\n"+message);
    }

}
