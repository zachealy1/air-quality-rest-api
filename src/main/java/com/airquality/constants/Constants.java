/**
 * Constants file holds various constant values used across the application.
 */
package com.airquality.constants;

/**
 * Constants class holds various constant values used across the application.
 */
public class Constants {

    /**
     * Delimiter used in data processing
     */
    public static final String DELIMITER = ",";

    /**
     * Blank string constant
     */
    public static final String BLANK = "";

    /**
     * Placeholder for deleted data
     */
    public static final String DELETED_CHARACTER = "N/A";

    /**
     * Prefix for Basic Authentication password
     */
    public static final String PASSWORD_PREFIX = "Basic ";

    /**
     * Username key for authentication
     */
    public static final String USERNAME = "username";

    /**
     * Password key for authentication
     */
    public static final String PASSWORD = "password";

    /**
     * File path for the global air pollution dataset
     */
    public static final String FILE_PATH = "data/global-air-pollution-dataset.csv";

    /**
     * Regular expression for validating integers
     */
    public static final String INTEGER_REGEX = "^[0-9]*$";

    /**
     * AQI category for healthy air
     */
    public static final String AQI_CATEGORY_HEALTHY = "Good";

    /**
     * AQI category for moderate air
     */
    public static final String AQI_CATEGORY_MODERATE = "Moderate";

    /**
     * AQI category for unhealthy air
     */
    public static final String AQI_CATEGORY_UNHEALTHY = "Unhealthy";

    /**
     * AQI category for air that is unhealthy for sensitive groups
     */
    public static final String AQI_CATEGORY_UNHEALTHY_FOR_CERTAIN_GROUPS = "Unhealthy for Sensitive Groups";

    /**
     * AQI category for very unhealthy air
     */
    public static final String AQI_CATEGORY_VERY_UNHEALTHY = "Very Unhealthy";

    /**
     * AQI category for hazardous air
     */
    public static final String AQI_CATEGORY_HAZARDOUS = "Hazardous";

    /**
     * Regular expression for validating location names
     */
    public static final String LOCATION_NAME_REGEX = "^[a-zA-Z]\\s+$";

    /**
     * CSV file header
     */
    public static final String CSV_HEADER = "Country,City,AQI Value,AQI Category,CO AQI Value,CO AQI Category,Ozone AQI Value,Ozone AQI Category,NO2 AQI Value,NO2 AQI Category,PM2.5 AQI Value,PM2.5 AQI Category";

    /**
     * Private constructor to prevent instantiation of the Constants class.
     */
    private Constants() {
        // Private constructor to prevent instantiation
    }
}
