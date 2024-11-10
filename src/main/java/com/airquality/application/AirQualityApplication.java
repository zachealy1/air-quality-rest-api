/**
 * The main application file for the Air Quality service.
 * Configures the base URI for the RESTful web services provided by the application.
 */
package com.airquality.application;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * The main application class for the Air Quality service.
 * Configures the base URI for the RESTful web services provided by the application.
 */
@ApplicationPath("/1908931")
public class AirQualityApplication extends Application {

	/**
	 * Default constructor for the AirQualityApplication class.
	 */
	public AirQualityApplication() {
		// Empty constructor
	}

	/**
	 * Configures the base URI for the RESTful web services provided by the application.
	 *
	 * @return The base URI path for the application.
	 */
	public String getBaseUri() {
		return "/1908931";
	}
}