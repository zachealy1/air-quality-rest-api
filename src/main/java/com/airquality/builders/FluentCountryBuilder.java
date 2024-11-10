/**
 * FluentCountryBuilder is a file that facilitates the construction of Country objects with associated City and AirQuality
 * information in a fluent manner, allowing for a more readable and expressive syntax.
 */
package com.airquality.builders;

import com.airquality.airquality.AirQuality;
import com.airquality.exceptions.InvalidAqiCategoryException;
import com.airquality.exceptions.InvalidAqiValueException;
import com.airquality.exceptions.InvalidCountryException;
import com.airquality.exceptions.InvalidRecordException;
import com.airquality.locations.City;
import com.airquality.locations.Country;

/**
 * FluentCountryBuilder is a class that facilitates the construction of Country objects with associated City and AirQuality
 * information in a fluent manner, allowing for a more readable and expressive syntax.
 */
public class FluentCountryBuilder {

    // The Country object being constructed
    private Country currentCountry;

    // The current City being constructed
    private City currentCity;

    /**
     * Constructs a FluentCountryBuilder with the specified country name.
     *
     * @param name The name of the country.
     * @throws InvalidCountryException If the country name is invalid.
     * @throws InvalidRecordException  If there is an issue with the record.
     */
    public FluentCountryBuilder(String name) throws InvalidCountryException, InvalidRecordException {
        currentCountry = new Country(name);
    }

    /**
     * Starts the construction of a new city within the current country.
     *
     * @param name The name of the city.
     * @return The FluentCountryBuilder instance for method chaining.
     * @throws InvalidRecordException If there is an issue with the record.
     */
    public FluentCountryBuilder startCity(String name) throws InvalidRecordException {
        currentCity = new City(name);
        return this;
    }

    /**
     * Ends the construction of the current city and adds it to the current country.
     *
     * @return The FluentCountryBuilder instance for method chaining.
     */
    public FluentCountryBuilder endCity() {
        if (currentCity != null) {
            currentCountry.getCities().add(currentCity);
            currentCity = null;
        }
        return this;
    }

    /**
     * Adds air quality information to the current city being built.
     *
     * @param aqiValue         AQI value
     * @param aqiCategory      AQI category
     * @param coAqiValue       Carbon monoxide AQI value
     * @param coAqiCategory    Carbon monoxide AQI category
     * @param ozoneAqiValue    Ozone AQI value
     * @param ozoneAqiCategory Ozone AQI category
     * @param noAqiValue       Nitrogen dioxide AQI value
     * @param noAqiCategory    Nitrogen dioxide AQI category
     * @param pmAqiValue       Particulate matter AQI value
     * @param pmAqiCategory    Particulate matter AQI category
     * @return The FluentCountryBuilder instance for method chaining.
     * @throws InvalidAqiCategoryException If an invalid AQI category is provided.
     * @throws InvalidAqiValueException    If an invalid AQI value is provided.
     */
    public FluentCountryBuilder addAirQuality(String aqiValue, String aqiCategory, String coAqiValue,
                                              String coAqiCategory, String ozoneAqiValue, String ozoneAqiCategory,
                                              String noAqiValue, String noAqiCategory, String pmAqiValue,
                                              String pmAqiCategory) throws InvalidAqiCategoryException, InvalidAqiValueException {
        if (currentCity != null) {
            currentCity.setAirQuality(new AirQuality(aqiValue, aqiCategory, coAqiValue, coAqiCategory, ozoneAqiValue, ozoneAqiCategory, noAqiValue, noAqiCategory, pmAqiValue, pmAqiCategory));
        } else {
            throw new IllegalArgumentException("No City Started");
        }
        return this;
    }

    /**
     * Builds and returns the Country object with the provided information.
     *
     * @return The constructed Country object.
     */
    public Country build() {
        endCity();
        return currentCountry;
    }
}




