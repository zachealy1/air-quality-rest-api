/**
 * NoCountryFoundException is an exception file that is thrown when no country is found.
 */
package com.airquality.exceptions;

/**
 * NoCountryFoundException is an exception class that is thrown when no country is found.
 * It extends the base Exception class.
 */
public class NoCountryFoundException extends Exception {

    /**
     * Constructs a NoCountryFoundException with a custom error message and error code.
     *
     * @param message   A custom error message describing the exception.
     * @param errorCode An integer error code associated with the exception.
     */
    public NoCountryFoundException(String message, int errorCode) {
        // Calling the base class constructor with a formatted error message
        super("Error " + errorCode + ": " + "NoCountryFoundException - " + message);
    }
}

