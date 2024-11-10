/**
 * InvalidRecordException is an exception file that is thrown when an invalid record is encountered.
 */
package com.airquality.exceptions;

/**
 * InvalidRecordException is an exception class that is thrown when an invalid record is encountered.
 * It extends the base Exception class.
 */
public class InvalidRecordException extends Exception {

    /**
     * Constructs an InvalidRecordException with a custom error message and error code.
     *
     * @param message   A custom error message describing the exception.
     * @param errorCode An integer error code associated with the exception.
     */
    public InvalidRecordException(String message, int errorCode) {
        // Calling the base class constructor with a formatted error message
        super("Error " + errorCode + ": " + "InvalidRecordException - " + message);
    }
}
