/**
 * The Location file is an abstract class representing a geographical location within the air quality monitoring system.
 */
package com.airquality.locations;

/**
 * The Location class is an abstract class representing a geographical location within the air quality monitoring system.
 * It provides a base class for specific location types like City and Country.
 */
public abstract class Location {

    /** The name of the location. */
    private String name;

    /**
     * Constructs a Location object with the given name.
     *
     * @param name The name of the location.
     */
    public Location(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the location.
     *
     * @return The name of the location.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the location.
     *
     * @param name The new name of the location.
     */
    public void setName(String name) {

        this.name = name;
    }
}

