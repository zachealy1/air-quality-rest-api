/**
 * This file represents air quality information including various AQI (Air Quality Index) values and categories.
 */
package com.airquality.airquality;

/**
 * Represents air quality information including various AQI (Air Quality Index) values and categories.
 */
public class AirQuality {

    // Fields to store AQI values and categories for different pollutants
    private String aqiValue;
    private String aqiCategory;
    private String coAqiValue;
    private String coAqiCategory;
    private String ozoneAqiValue;
    private String ozoneAqiCategory;
    private String noAqiValue;
    private String noAqiCategory;
    private String pmAqiValue;
    private String pmAqiCategory;

    /**
     * Default constructor for the AirQuality class.
     */
    public AirQuality() {
        // Empty constructor
    }

    /**
     * Parameterized constructor to initialize AirQuality object with specific AQI values and categories.
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
     */
    public AirQuality(String aqiValue, String aqiCategory, String coAqiValue, String coAqiCategory,
                      String ozoneAqiValue, String ozoneAqiCategory, String noAqiValue, String noAqiCategory,
                      String pmAqiValue, String pmAqiCategory) {
        this.setAqiValue(aqiValue);
        this.setAqiCategory(aqiCategory);
        this.setCoAqiValue(coAqiValue);
        this.setCoAqiCategory(coAqiCategory);
        this.setOzoneAqiValue(ozoneAqiValue);
        this.setOzoneAqiCategory(ozoneAqiCategory);
        this.setNoAqiValue(noAqiValue);
        this.setNoAqiCategory(noAqiCategory);
        this.setPmAqiValue(pmAqiValue);
        this.setPmAqiCategory(pmAqiCategory);
    }

    /**
     * Gets the AQI value.
     *
     * @return The AQI value.
     */
    public String getAqiValue() {
        return aqiValue;
    }

    /**
     * Sets the AQI value.
     *
     * @param aqiValue The AQI value to set.
     */
    public void setAqiValue(String aqiValue) {
        this.aqiValue = aqiValue;
    }

    /**
     * Gets the AQI category.
     *
     * @return The AQI category.
     */
    public String getAqiCategory() {
        return aqiCategory;
    }

    /**
     * Sets the AQI category.
     *
     * @param aqiCategory The AQI category to set.
     */
    public void setAqiCategory(String aqiCategory) {
        this.aqiCategory = aqiCategory;
    }

    /**
     * Gets the carbon monoxide AQI value.
     *
     * @return The carbon monoxide AQI value.
     */
    public String getCoAqiValue() {
        return coAqiValue;
    }

    /**
     * Sets the carbon monoxide AQI value.
     *
     * @param coAqiValue The carbon monoxide AQI value to set.
     */
    public void setCoAqiValue(String coAqiValue) {
        this.coAqiValue = coAqiValue;
    }

    /**
     * Gets the carbon monoxide AQI category.
     *
     * @return The carbon monoxide AQI category.
     */
    public String getCoAqiCategory() {
        return coAqiCategory;
    }

    /**
     * Sets the carbon monoxide AQI category.
     *
     * @param coAqiCategory The carbon monoxide AQI category to set.
     */
    public void setCoAqiCategory(String coAqiCategory) {
        this.coAqiCategory = coAqiCategory;
    }

    /**
     * Gets the ozone AQI value.
     *
     * @return The ozone AQI value.
     */
    public String getOzoneAqiValue() {
        return ozoneAqiValue;
    }

    /**
     * Sets the ozone AQI value.
     *
     * @param ozoneAqiValue The ozone AQI value to set.
     */
    public void setOzoneAqiValue(String ozoneAqiValue) {
        this.ozoneAqiValue = ozoneAqiValue;
    }

    /**
     * Gets the ozone AQI category.
     *
     * @return The ozone AQI category.
     */
    public String getOzoneAqiCategory() {
        return ozoneAqiCategory;
    }

    /**
     * Sets the ozone AQI category.
     *
     * @param ozoneAqiCategory The ozone AQI category to set.
     */
    public void setOzoneAqiCategory(String ozoneAqiCategory) {
        this.ozoneAqiCategory = ozoneAqiCategory;
    }

    /**
     * Gets the nitrogen dioxide AQI value.
     *
     * @return The nitrogen dioxide AQI value.
     */
    public String getNoAqiValue() {
        return noAqiValue;
    }

    /**
     * Sets the nitrogen dioxide AQI value.
     *
     * @param noAqiValue The nitrogen dioxide AQI value to set.
     */
    public void setNoAqiValue(String noAqiValue) {
        this.noAqiValue = noAqiValue;
    }

    /**
     * Gets the nitrogen dioxide AQI category.
     *
     * @return The nitrogen dioxide AQI category.
     */
    public String getNoAqiCategory() {
        return noAqiCategory;
    }

    /**
     * Sets the nitrogen dioxide AQI category.
     *
     * @param noAqiCategory The nitrogen dioxide AQI category to set.
     */
    public void setNoAqiCategory(String noAqiCategory) {
        this.noAqiCategory = noAqiCategory;
    }

    /**
     * Gets the particulate matter AQI value.
     *
     * @return The particulate matter AQI value.
     */
    public String getPmAqiValue() {
        return pmAqiValue;
    }

    /**
     * Sets the particulate matter AQI value.
     *
     * @param pmAqiValue The particulate matter AQI value to set.
     */
    public void setPmAqiValue(String pmAqiValue) {
        this.pmAqiValue = pmAqiValue;
    }

    /**
     * Gets the particulate matter AQI category.
     *
     * @return The particulate matter AQI category.
     */
    public String getPmAqiCategory() {
        return pmAqiCategory;
    }

    /**
     * Sets the particulate matter AQI category.
     *
     * @param pmAqiCategory The particulate matter AQI category to set.
     */
    public void setPmAqiCategory(String pmAqiCategory) {
        this.pmAqiCategory = pmAqiCategory;
    }

    /**
     * Returns a string representation of the AirQuality object.
     *
     * @return A comma-separated string of AQI values and categories.
     */
    @Override
    public String toString() {
        return aqiValue + "," + aqiCategory + "," + coAqiValue + "," + coAqiCategory + "," + ozoneAqiValue + "," + ozoneAqiCategory + "," + noAqiValue + "," + noAqiCategory + "," + pmAqiValue + "," + pmAqiCategory;
    }
}
