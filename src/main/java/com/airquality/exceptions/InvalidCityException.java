/**
 * InvalidCityException is an exception file that is thrown when an invalid city is encountered.
 */
package com.airquality.exceptions;

/**
 * InvalidCityException is an exception class that is thrown when an invalid city is encountered.
 * It extends the base Exception class.
 */
public class InvalidCityException extends Exception {

    /**
     * Constructs an InvalidCityException with a custom error message and error code.
     *
     * @param message   A custom error message describing the exception.
     * @param errorCode An integer error code associated with the exception.
     */
    public InvalidCityException(String message, int errorCode) {
        // Calling the base class constructor with a formatted error message
        super("Error " + errorCode + ": " + "InvalidCityException - " + message);
    }
}

