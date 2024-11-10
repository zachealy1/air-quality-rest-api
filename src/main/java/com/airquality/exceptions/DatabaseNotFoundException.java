/**
 * DatabaseNotFoundException is an exception file that is thrown when there is an issue with the database.
 */
package com.airquality.exceptions;

/**
 * DatabaseNotFoundException is an exception class that is thrown when there is an issue with the database.
 * It extends the base Exception class.
 */
public class DatabaseNotFoundException extends Exception {

    /**
     * Constructs a DatabaseNotFoundException with a custom error message and error code.
     *
     * @param message   A custom error message describing the exception.
     * @param errorCode An integer error code associated with the exception.
     */
    public DatabaseNotFoundException(String message, int errorCode) {
        // Calling the base class constructor with a formatted error message
        super("Error " + errorCode + ": " + "DatabaseNotFoundException - " + message);
    }
}
