/**
 * The Record file represents the collection of recorded air quality data.
 */
package com.airquality.record;

import com.airquality.locations.Country;

import java.util.ArrayList;

/**
 * The Record class represents the collection of recorded air quality data.
 * It follows the Singleton pattern to ensure a single instance of the record exists.
 */
public class Record {

    /** The single instance of the Record class. */
    private static Record instance;

    /** The list of countries containing air quality data records. */
    private ArrayList<Country> countryRecords = new ArrayList<>();

    /**
     * Private constructor to prevent direct instantiation.
     */
    private Record() {
        // Private constructor
    }

    /**
     * Gets the single instance of the Record class using the Singleton pattern.
     *
     * @return The singleton instance of the Record class.
     */
    public static Record getInstance() {
        if (instance == null) {
            synchronized (Record.class) {
                if (instance == null) {
                    instance = new Record();
                }
            }
        }
        return instance;
    }

    /**
     * Gets the list of countries containing air quality data records.
     *
     * @return The ArrayList of Country objects representing recorded air quality data.
     */
    public ArrayList<Country> getCountryRecords() {
        return countryRecords;
    }

    /**
     * Sets the list of countries containing air quality data records.
     *
     * @param countryRecords The ArrayList of Country objects representing recorded air quality data.
     */
    public void setCountryRecords(ArrayList<Country> countryRecords) {
        this.countryRecords = countryRecords;
    }

    public static void setInstance(Record instance) {
        Record.instance = instance;
    }
}

