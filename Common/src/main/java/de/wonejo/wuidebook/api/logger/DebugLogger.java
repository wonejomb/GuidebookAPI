package de.wonejo.wuidebook.api.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;

public class DebugLogger {
    private static final String LOG_FILE_NAME = "logs/wuidebook_debug.log";
    private static final String LOGGER = "gapi";

    private static Logger logger;

    public static void initializeLogger () {
        final LoggerContext context = (LoggerContext) LogManager.getContext(false);
        final Configuration configuration = context.getConfiguration();

        PatternLayout logPattern = PatternLayout.newBuilder().withPattern("[%d{DATE}] [%level]: %msg%n").build();
        Appender fileAppender = FileAppender.newBuilder()
                .withFileName(LOG_FILE_NAME)
                .withAppend(true)
                .setName(LOGGER)
                .setConfiguration(configuration)
                .setLayout(logPattern)
                .build();
        Appender consoleAppender = ConsoleAppender.newBuilder()
                .setLayout(logPattern)
                .setFilter(null)
                .setTarget(ConsoleAppender.Target.SYSTEM_OUT)
                .setName(LOGGER)
                .setFollow(false)
                .setIgnoreExceptions(false).build();
        consoleAppender.start();
        fileAppender.start();

        AppenderRef[] refs = new AppenderRef[] { AppenderRef.createAppenderRef(LOGGER, Level.DEBUG, null) };

        LoggerConfig loggerConfig = LoggerConfig.createLogger(false, Level.DEBUG, LOGGER,"true", refs, null, configuration, null);
        loggerConfig.addAppender(consoleAppender, Level.DEBUG, null);
        loggerConfig.addAppender(fileAppender, Level.DEBUG, null);
        configuration.addLogger(LOGGER, loggerConfig);
        context.updateLoggers();
        logger = LogManager.getLogger(LOGGER);
    }

    public static void log (String pMsg, Object... pArgs ) {
        if ( logger != null )
            logger.debug(pMsg, pArgs);
    }
}
