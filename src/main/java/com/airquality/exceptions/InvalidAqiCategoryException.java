/**
 * InvalidAqiCategoryException is an exception file that is thrown when an invalid Air Quality Index (AQI) category is encountered.
 */
package com.airquality.exceptions;

/**
 * InvalidAqiCategoryException is an exception class that is thrown when an invalid Air Quality Index (AQI) category is encountered.
 * It extends the base Exception class.
 */
public class InvalidAqiCategoryException extends Exception {

    /**
     * Constructs an InvalidAqiCategoryException with a custom error message and error code.
     *
     * @param message   A custom error message describing the exception.
     * @param errorCode An integer error code associated with the exception.
     */
    public InvalidAqiCategoryException(String message, int errorCode) {
        // Calling the base class constructor with a formatted error message
        super("Error " + errorCode + ": " + "InvalidAqiCategoryException - " + message);
    }
}

