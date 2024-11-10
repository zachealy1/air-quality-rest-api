/**
 * InvalidAqiValueException is an exception file that is thrown when an invalid Air Quality Index (AQI) value is encountered.
 */
package com.airquality.exceptions;

/**
 * InvalidAqiValueException is an exception class that is thrown when an invalid Air Quality Index (AQI) value is encountered.
 * It extends the base Exception class.
 */
public class InvalidAqiValueException extends Exception {

    /**
     * Constructs an InvalidAqiValueException with a custom error message and error code.
     *
     * @param message   A custom error message describing the exception.
     * @param errorCode An integer error code associated with the exception.
     */
    public InvalidAqiValueException(String message, int errorCode) {
        // Calling the base class constructor with a formatted error message
        super("Error " + errorCode + ": " + "InvalidAqiValueException - " + message);
    }
}

