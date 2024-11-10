/**
 * Utility file providing various helper methods for air quality application.
 */
package com.airquality.utility;

import com.airquality.airquality.AirQuality;
import com.airquality.exceptions.*;
import com.airquality.locations.City;
import com.airquality.constants.Constants;
import com.airquality.locations.Country;
import com.airquality.record.Record;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Utility class providing various helper methods for air quality application.
 */
public class Utility {

    private static Utility instance;

    private Utility() {
    }

    /**
     * Get the singleton instance of the Utility class.
     *
     * @return The singleton instance of the Utility class.
     */
    public static Utility getInstance() {
        if (instance == null) {
            synchronized (Utility.class) {
                if (instance == null) {
                    instance = new Utility();
                }
            }
        }
        return instance;
    }

    /**
     * Check if a country exists in the records.
     *
     * @param country The name of the country.
     * @return True if the country exists, false otherwise.
     */
    public boolean doesCountryExist(String country) {
        for (int i = 0; i < Record.getInstance().getCountryRecords().size(); i++) {
            if (Record.getInstance().getCountryRecords().get(i).getName().equalsIgnoreCase(country)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validate if a string represents a valid integer or a deleted character.
     *
     * @param value The string value to validate.
     * @return The input value if valid, otherwise throw InvalidRecordException.
     * @throws InvalidRecordException If the value is not a valid integer or a deleted character.
     */
    public String isInteger(String value) throws InvalidRecordException {
        if (value.matches(Constants.INTEGER_REGEX) || value.equals(Constants.DELETED_CHARACTER)) {
            return value;
        } else {
            throw new InvalidRecordException("Database record entry invalid", 403);
        }
    }

    /**
     * Validate if a string is a valid location name.
     *
     * @param value The string value to validate.
     * @return The input value if valid, otherwise throw InvalidRecordException.
     * @throws InvalidRecordException If the value is not a valid location name.
     */
    public String isString(String value) throws InvalidRecordException {
        if (value.matches(Constants.LOCATION_NAME_REGEX)) {
            throw new InvalidRecordException("Database record entry invalid", 403);
        } else {
            return value;
        }
    }

    /**
     * Validate if an AQI value is valid.
     *
     * @param aqiValue The AQI value to validate.
     * @return True if the AQI value is valid, otherwise throw InvalidAqiValueException.
     * @throws InvalidAqiValueException If the AQI value is not a valid integer or a deleted character.
     */
    public boolean isAqiValueValid(String aqiValue) throws InvalidAqiValueException {
        if ((aqiValue.matches(Constants.INTEGER_REGEX) && Integer.parseInt(aqiValue) >= 0) || aqiValue.equals(Constants.DELETED_CHARACTER)) {
            return true;
        } else {
            throw new InvalidAqiValueException("AQI value Invalid", 403);
        }
    }

    /**
     * Validate if an AQI category is valid.
     *
     * @param aqiCategory The AQI category to validate.
     * @return True if the AQI category is valid, otherwise throw InvalidAqiCategoryException.
     * @throws InvalidAqiCategoryException If the AQI category is not one of the predefined categories.
     */
    public boolean isAqiCategoryValid(String aqiCategory) throws InvalidAqiCategoryException {
        if (aqiCategory.equalsIgnoreCase(Constants.AQI_CATEGORY_HEALTHY) || aqiCategory.equalsIgnoreCase(Constants.AQI_CATEGORY_UNHEALTHY)
                || aqiCategory.equalsIgnoreCase(Constants.AQI_CATEGORY_MODERATE) || aqiCategory.equalsIgnoreCase(Constants.AQI_CATEGORY_UNHEALTHY_FOR_CERTAIN_GROUPS)
                || aqiCategory.equalsIgnoreCase(Constants.AQI_CATEGORY_VERY_UNHEALTHY) || aqiCategory.equalsIgnoreCase(Constants.AQI_CATEGORY_HAZARDOUS)) {
            return true;
        } else {
            throw new InvalidAqiCategoryException("AQI Category must be \"Good\", \"Moderate\", " + "\"Unhealthy for Sensitive Groups\", \"Unhealthy\", \"Very Unhealthy\", or \"N/A\"", 403);
        }
    }

    /**
     * Validate if an AirQuality object has valid AQI values and categories.
     *
     * @param airQuality The AirQuality object to validate.
     * @return True if the AirQuality is valid, otherwise throw InvalidAqiValueException or InvalidAqiCategoryException.
     * @throws InvalidAqiValueException    If any AQI value in the AirQuality object is not valid.
     * @throws InvalidAqiCategoryException If any AQI category in the AirQuality object is not valid.
     */
    public boolean isAirQualityValid(
            AirQuality airQuality) throws InvalidAqiValueException, InvalidAqiCategoryException {
        return isAqiValueValid(airQuality.getAqiValue()) && isAqiCategoryValid(airQuality.getAqiCategory()) && isAqiValueValid(airQuality.getCoAqiValue()) && isAqiCategoryValid(airQuality.getCoAqiCategory()) && isAqiValueValid(airQuality.getOzoneAqiValue()) && isAqiCategoryValid(airQuality.getOzoneAqiCategory()) && isAqiValueValid(airQuality.getNoAqiValue()) && isAqiCategoryValid(airQuality.getNoAqiCategory()) && isAqiValueValid(airQuality.getPmAqiValue()) && isAqiCategoryValid(airQuality.getPmAqiCategory());
    }

    /**
     * Check if an AQI value represents a deleted character.
     *
     * @param aqiValue The AQI value to check.
     * @return True if the AQI value is a deleted character, false otherwise.
     */
    public boolean isAqiStringDeletedValue(String aqiValue) {
        return aqiValue.equals(Constants.DELETED_CHARACTER);
    }

    /**
     * Get the city ID for a given country and city name.
     *
     * @param country The name of the country.
     * @param city    The name of the city.
     * @return The city ID if found, otherwise throw InvalidCityException or InvalidCountryException.
     * @throws InvalidCityException    If the city is not found in the database.
     * @throws InvalidCountryException If the country is not found in the database.
     */
    public int getCityId(String country, String city) throws InvalidCityException, InvalidCountryException {
        boolean targetCountryFound = false;
        for (int i = 0; i < Record.getInstance().getCountryRecords().size(); i++) {
            Country targetCountry = Record.getInstance().getCountryRecords().get(i);
            if (targetCountry.getName().equalsIgnoreCase(country)) {
                targetCountryFound = true;
                for (int y = 0; y < targetCountry.getCities().size(); y++) {
                    City targetCity = targetCountry.getCities().get(y);
                    if (targetCity.getName().equals(city)) {
                        return targetCity.getCityId();
                    }
                }
            }
        }
        if (!targetCountryFound) {
            throw new InvalidCountryException("Country not found in the database", 403);
        } else {
            throw new InvalidCityException("City not found in the database", 403);
        }
    }

    /**
     * Get the Country object by its name.
     *
     * @param country The name of the country.
     * @return The Country object if found, otherwise throw InvalidCountryException.
     * @throws InvalidCountryException If the country is not found in the database.
     */
    public Country getCountryByName(String country) throws InvalidCountryException {
        for (int i = 0; i < Record.getInstance().getCountryRecords().size(); i++) {
            Country targetCountry = Record.getInstance().getCountryRecords().get(i);
            if (targetCountry.getName().equalsIgnoreCase(country)) {
                return targetCountry;
            }
        }
        throw new InvalidCountryException("Country not found in the database", 403);
    }

    /**
     * Check if a city exists in the records.
     *
     * @param country The name of the country.
     * @param city    The name of the city.
     * @return True if the city exists, false otherwise.
     */
    public boolean doesCityExist(String country, String city) {
        for (int i = 0; i < Record.getInstance().getCountryRecords().size(); i++) {
            Country targetCountry = Record.getInstance().getCountryRecords().get(i);
            if (targetCountry.getName().equalsIgnoreCase(country)) {
                for (int y = 0; y < targetCountry.getCities().size(); y++) {
                    City targetCity = targetCountry.getCities().get(y);
                    if (targetCity.getName().equals(city)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Check if the provided authorization header is valid.
     *
     * @param authorizationHeader The authorization header to check.
     * @return True if the authorization is valid, false otherwise.
     */
    public boolean isAuthorised(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith(Constants.PASSWORD_PREFIX)) {
            return false;
        } else {
            String base64Credentials = authorizationHeader.substring(Constants.PASSWORD_PREFIX.length()).trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
            String[] values = credentials.split(":", 2);
            return Constants.USERNAME.equals(values[0]) && Constants.PASSWORD.equals(values[1]);
        }
    }
}
