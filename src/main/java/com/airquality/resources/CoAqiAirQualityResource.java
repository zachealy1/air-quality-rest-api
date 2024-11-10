/**
 * The CoAqiAirQualityResource file provides RESTful web services for retrieving air quality information related to CO (Carbon Monoxide).
 */
package com.airquality.resources;

import com.airquality.exceptions.*;
import com.airquality.locations.City;
import com.airquality.constants.Constants;
import com.airquality.locations.Country;
import com.airquality.csv.CsvReadWrite;
import com.airquality.record.Record;
import com.airquality.utility.Utility;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.CacheControl;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

/**
 * The CoAqiAirQualityResource class provides RESTful web services for retrieving air quality information related to CO (Carbon Monoxide).
 */
@Path("/co/v1")
public class CoAqiAirQualityResource {

    /**
     * Retrieves cities with healthy CO air quality.
     *
     * @param authorisationHeader The authorization header for authentication.
     * @return The response containing cities with healthy CO air quality in JSON format.
     * @throws NoCityFoundException      If no cities with healthy CO air quality are found.
     * @throws DatabaseNotFoundException If there is an issue connecting to the database.
     */
    @GET
    @Path("/co-healthy-cities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoHealthyCities(
            @HeaderParam("Authorization") String authorisationHeader) throws NoCityFoundException, DatabaseNotFoundException {

        if (Utility.getInstance().isAuthorised(authorisationHeader)) {
            CsvReadWrite.readDataFromCSV(Constants.FILE_PATH);
            ArrayList<City> result = new ArrayList<>();

            for (int i = 0; i < Record.getInstance().getCountryRecords().size(); i++) {
                Country targetCountry = Record.getInstance().getCountryRecords().get(i);

                for (int y = 0; y < targetCountry.getCities().size(); y++) {
                    City targetCity = targetCountry.getCities().get(y);

                    if (targetCity.getAirQuality().getCoAqiCategory().equalsIgnoreCase(Constants.AQI_CATEGORY_HEALTHY)) {
                        result.add(targetCity);
                    }
                }
            }

            if (result.size() > 0) {
                CacheControl cacheControl = new CacheControl();
                cacheControl.setMaxAge(5);
                return Response.ok(result).cacheControl(cacheControl).build();
            } else {
                throw new NoCityFoundException("No City Found", 403);
            }
        } else {
            return Response.status(401).entity("Error 401: Invalid Credentials").build();
        }
    }

    /**
     * Retrieves cities with unhealthy CO air quality.
     *
     * @param authorisationHeader The authorization header for authentication.
     * @return The response containing cities with unhealthy CO air quality in JSON format.
     * @throws NoCityFoundException      If no cities with unhealthy CO air quality are found.
     * @throws DatabaseNotFoundException If there is an issue connecting to the database.
     */
    @GET
    @Path("/co-unhealthy-cities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoUnhealthyCities(
            @HeaderParam("Authorization") String authorisationHeader) throws NoCityFoundException, DatabaseNotFoundException {

        if (Utility.getInstance().isAuthorised(authorisationHeader)) {
            CsvReadWrite.readDataFromCSV(Constants.FILE_PATH);
            ArrayList<City> result = new ArrayList<>();

            for (int i = 0; i < Record.getInstance().getCountryRecords().size(); i++) {
                Country targetCountry = Record.getInstance().getCountryRecords().get(i);

                for (int y = 0; y < targetCountry.getCities().size(); y++) {
                    City targetCity = targetCountry.getCities().get(y);

                    if (targetCity.getAirQuality().getCoAqiCategory().equalsIgnoreCase(Constants.AQI_CATEGORY_UNHEALTHY) || targetCity.getAirQuality().getCoAqiCategory().equalsIgnoreCase(Constants.AQI_CATEGORY_UNHEALTHY_FOR_CERTAIN_GROUPS)) {
                        result.add(targetCity);
                    }
                }
            }

            if (result.size() > 0) {
                CacheControl cacheControl = new CacheControl();
                cacheControl.setMaxAge(5);
                return Response.ok(result).cacheControl(cacheControl).build();
            } else {
                throw new NoCityFoundException("No City Found", 403);
            }
        } else {
            return Response.status(401).entity("Error 401: Invalid Credentials").build();
        }
    }
}

