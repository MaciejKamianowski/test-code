package com.dsv.datafactory.file.extraction.processor.util;

// the utility class should have private no arguments constructor
public class ConfigurationLoader {
    public static String getOrDefault(String envName, String defaultValue) {
        return getOrDefault(envName, defaultValue, false);
    }

    // parameter isEnvValueSecretHidden should be primitive so boolean isEnvValueSecretHidden
    // it may lead to nullpinter execption
    public static String getOrDefault(String envName, String defaultValue, Boolean isEnvValueSecretHidden) {
        String value = System.getenv(envName);

        String envValueToPrint = (isEnvValueSecretHidden || envName.toLowerCase().contains("pass"))
                ? "Hidden:****"
                : value;

        if (value == null) {
            // risking exposure sensitive information
            // instead of sysout use ECSLogger for example
            // System.out.println("Using environment variable for " + envName);
            System.out.println("Using default value " + defaultValue + " for: " + envName);
            return defaultValue;
        } else {
            // :  unclear what "or Else" means in this context.
            // System.out.println("Environment variable " + envName + " not set, using default: " + defaultValue);
            System.out.println(envValueToPrint + " found for: " + envName + " or Else: " + defaultValue);
            return value;
        }
    }

    public static String getOrFail(String envName) {
        String value = System.getenv(envName);
        if (value == null) {
            throw new RuntimeException("Could not find env variable " + envName);
        } else {
            return value;
        }
    }

    // if the class will be used with more than strings we can introduce this method
/*    public static int getIntOrDefault(String envName, int defaultValue) {
        String val = System.getenv(envName);
        try {
            return val != null ? Integer.parseInt(val) : defaultValue;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid int value for " + envName, e);
        }
    }*/
}
