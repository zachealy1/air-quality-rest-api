/**
 * FluentCityBuilder is a file that facilitates the construction of City objects with associated AirQuality information
 * in a fluent manner, allowing for a more readable and expressive syntax.
 */
package com.airquality.builders;

import com.airquality.airquality.AirQuality;
import com.airquality.exceptions.InvalidAqiCategoryException;
import com.airquality.exceptions.InvalidAqiValueException;
import com.airquality.exceptions.InvalidRecordException;
import com.airquality.locations.City;

/**
 * FluentCityBuilder is a class that facilitates the construction of City objects with associated AirQuality information
 * in a fluent manner, allowing for a more readable and expressive syntax.
 */
public class FluentCityBuilder {

    // The City object being constructed
    private City currentCity;

    /**
     * Constructs a FluentCityBuilder with the specified city name.
     *
     * @param name The name of the city.
     * @throws InvalidRecordException If the city name is invalid.
     */
    public FluentCityBuilder(String name) throws InvalidRecordException {
        currentCity = new City(name);
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
     * @return The FluentCityBuilder instance for method chaining.
     * @throws InvalidAqiCategoryException If an invalid AQI category is provided.
     * @throws InvalidAqiValueException    If an invalid AQI value is provided.
     */
    public FluentCityBuilder addAirQuality(String aqiValue, String aqiCategory, String coAqiValue, String coAqiCategory,
                                           String ozoneAqiValue, String ozoneAqiCategory, String noAqiValue,
                                           String noAqiCategory, String pmAqiValue,
                                           String pmAqiCategory) throws InvalidAqiCategoryException, InvalidAqiValueException {
        if (currentCity != null) {
            currentCity.setAirQuality(new AirQuality(aqiValue, aqiCategory, coAqiValue, coAqiCategory, ozoneAqiValue, ozoneAqiCategory, noAqiValue, noAqiCategory, pmAqiValue, pmAqiCategory));
        }
        return this;
    }

    /**
     * Builds and returns the City object with the provided information.
     *
     * @return The constructed City object.
     */
    public City build() {
        return currentCity;
    }
}




