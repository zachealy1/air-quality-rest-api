package com.airquality.locations;

import java.util.ArrayList;

/**
 * The Country class represents a country within the air quality monitoring system.
 * It extends the base Location class and includes information about cities in the country.
 */
public class Country extends Location {

    /** The list of cities within the country. */
    private ArrayList<City> cities = new ArrayList<>();

    /**
     * Constructs a Country object with the given name.
     *
     * @param name The name of the country.
     */
    public Country(String name) {
        super(name);
    }

    /**
     * Gets the list of cities within the country.
     *
     * @return The ArrayList of City objects representing cities in the country.
     */
    public ArrayList<City> getCities() {
        return cities;
    }

    /**
     * Sets the list of cities for the country.
     *
     * @param cities The ArrayList of City objects representing cities in the country.
     */
    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }
}

