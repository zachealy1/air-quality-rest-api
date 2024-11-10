/**
 * The AirQualityExceptionMapper file is responsible for mapping exceptions to HTTP responses.
 */
package com.airquality.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * The AirQualityExceptionMapper class is responsible for mapping exceptions to HTTP responses.
 * It implements the ExceptionMapper interface to handle exceptions thrown in the application.
 */
@Provider
public class AirQualityExceptionMapper implements ExceptionMapper<Exception> {

	/**
	 * Constructs an AirQualityExceptionMapper.
	 */
	public AirQualityExceptionMapper() {
		// Default constructor
	}

	/**
	 * Converts an exception into an HTTP response.
	 *
	 * @param exception The exception to be mapped.
	 * @return A Response object with the appropriate status code and error message.
	 */
	@Override
	public Response toResponse(Exception exception) {
		ResponseBuilder builder = Response.status(403); // 403 Forbidden status code
		return builder.entity(exception.getMessage()).build();
	}
}