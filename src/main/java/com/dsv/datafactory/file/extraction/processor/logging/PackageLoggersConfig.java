package com.dsv.datafactory.file.extraction.processor.logging;

import com.dsv.logger.ECSLogger;

import static com.dsv.datafactory.file.extraction.processor.util.ConfigurationLoader.getOrDefault;

public class PackageLoggersConfig {
    // extract environment variable names as constants
    // private static final String ENV_KAFKA_LOG_LEVEL = "KAFKA_LOG_LEVEL";
    // private static final String ENV_LOG_LEVEL = "LOG_LEVEL";
    public static void configure(){
        configureKafkaLogger();
        configurePdfBoxLogger();
    }

    private static void configureKafkaLogger() {
        ECSLogger.Level kafkaLogLevel = ECSLogger.Level.valueOf(getOrDefault("KAFKA_LOG_LEVEL", ECSLogger.Level.WARN.name()));
        String KAFKA_LOGGER_NAME = "org.apache.kafka";

        configurePackageLogger(KAFKA_LOGGER_NAME, kafkaLogLevel);
    }

    private static void configurePdfBoxLogger() {
        // Uses constants for package names.
        String PDFBOX_LOGGER_NAME = "org.apache.pdfbox.pdmodel.font";
        ECSLogger.Level logLevel = ECSLogger.Level.valueOf(getOrDefault("LOG_LEVEL", ECSLogger.Level.WARN.name()));

        configurePackageLogger(PDFBOX_LOGGER_NAME, logLevel);
    }

    private static void configurePackageLogger(String packageName, ECSLogger.Level logLevel){
        ECSLogger logger = ECSLoggerProvider.getLogger(PackageLoggersConfig.class.getName());
        logger.setLevel(packageName, logLevel);

        // Consider logging configuration at DEBUG level
        // if (logger.isDebugEnabled()) {
        //    logger.debug("Package {} log level set to {}", packageName, logLevel.name());
        //}
        logger.info("Package " + packageName + " log level set to " + logLevel.name());
    }
}
