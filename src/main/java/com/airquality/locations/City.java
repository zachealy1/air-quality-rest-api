/**
 * The City file represents a city within the air quality monitoring system.
 */
package com.airquality.locations;

import com.airquality.airquality.AirQuality;

/**
 * The City class represents a city within the air quality monitoring system.
 * It extends the base Location class and includes information about air quality.
 */
public class City extends Location {

    /** The next available ID for a city. */
    public static int nextId = 1;

    // The unique identifier for the city.
    private int cityId;

    // The air quality information associated with the city.
    private AirQuality airQuality;

    /**
     * Constructs a City object with the given name.
     *
     * @param name The name of the city.
     */
    public City(String name) {
        super(name);
        this.cityId = nextId;
        nextId++;
    }

    /**
     * Gets the air quality information associated with the city.
     *
     * @return The AirQuality object representing the air quality of the city.
     */
    public AirQuality getAirQuality() {
        return airQuality;
    }

    /**
     * Sets the air quality information for the city.
     *
     * @param airQuality The AirQuality object representing the air quality of the city.
     */
    public void setAirQuality(AirQuality airQuality) {
        this.airQuality = airQuality;
    }

    /**
     * Gets the unique identifier of the city.
     *
     * @return The unique identifier for the city.
     */
    public int getCityId() {
        return cityId;
    }

    /**
     * Sets the unique identifier of the city.
     *
     * @param cityId The unique identifier for the city.
     */
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}

