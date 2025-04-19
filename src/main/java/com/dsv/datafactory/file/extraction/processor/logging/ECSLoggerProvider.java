package com.dsv.datafactory.file.extraction.processor.logging;

import com.dsv.logger.ECSLogger;

import static com.dsv.datafactory.file.extraction.processor.util.ConfigurationLoader.getOrDefault;

// for utility class add private constructor
public class ECSLoggerProvider {
    // private ECSLoggerProvider() {
    //    throw new UnsupportedOperationException("Utility class");
    //}

    // unless you are using those constants outside this class make them private
    public static final String LOG_APP_NAME_SDD_ENV_VAR = "LOG_APP_NAME_SDD";
    public static final String LOG_APP_ID_SDD_ENV_VAR = "LOG_APP_ID_SDD";
    public static final String LOG_LEVEL_ENV_VAR = "LOG_LEVEL";
    // private static final String DEFAULT_APP_NAME = "aifactory";
    //private static final String DEFAULT_APP_ID = "5389";
    //private static final String DEFAULT_LOG_LEVEL = "WARN";

    public static ECSLogger getLogger(String className) {
        return getLogger(className, ECSLogger.Level.valueOf(getOrDefault(LOG_LEVEL_ENV_VAR, "WARN")));
    }
    public static ECSLogger getLogger(String className, ECSLogger.Level level) {
        ECSLogger logger = new ECSLogger(
                getOrDefault(LOG_APP_NAME_SDD_ENV_VAR, "aifactory"),    // extract to constant
                getOrDefault(LOG_APP_ID_SDD_ENV_VAR, "5389"),       // extract to constant
                className);
        logger.setLevel(className, level);
        return  logger;
    }
}