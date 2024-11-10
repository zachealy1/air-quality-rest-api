/**
 * The OzoneAqiAirQualityResource file provides RESTful web services for retrieving information related to ozone air quality.
 */
package com.airquality.resources;

import com.airquality.airquality.AirQuality;
import com.airquality.exceptions.*;
import com.airquality.locations.City;
import com.airquality.constants.Constants;
import com.airquality.locations.Country;
import com.airquality.csv.CsvReadWrite;
import com.airquality.record.Record;
import com.airquality.utility.Utility;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

/**
 * The OzoneAqiAirQualityResource class provides RESTful web services for retrieving information related to ozone air quality.
 */
@Path("/ozone/v1")
public class OzoneAqiAirQualityResource {

    /**
     * Retrieves cities within a specified ozone AQI value range.
     *
     * @param startingValue       The starting value of the ozone AQI range.
     * @param endingValue         The ending value of the ozone AQI range.
     * @param authorisationHeader The authorization header for authentication.
     * @return The response containing the cities within the specified ozone AQI value range in JSON format.
     * @throws InvalidAqiValueException  If there is an issue with the AQI value.
     * @throws NoCityFoundException      If no cities are found.
     * @throws DatabaseNotFoundException If there is an issue connecting to the database.
     */
    @GET
    @Path("/ozone-aqi-value")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCityWithinOzoneAqiValue(@QueryParam("startingValue") int startingValue,
                                               @QueryParam("endingValue") int endingValue,
                                               @HeaderParam("Authorization") String authorisationHeader) throws InvalidAqiValueException, NoCityFoundException, DatabaseNotFoundException {

        if (Utility.getInstance().isAuthorised(authorisationHeader)) {
            CsvReadWrite.readDataFromCSV(Constants.FILE_PATH);
            ArrayList<City> result = new ArrayList<>();

            Utility.getInstance().isAqiValueValid(String.valueOf(startingValue));
            Utility.getInstance().isAqiValueValid(String.valueOf(endingValue));

            for (int i = 0; i < Record.getInstance().getCountryRecords().size(); i++) {
                Country targetCountry = Record.getInstance().getCountryRecords().get(i);

                for (int y = 0; y < targetCountry.getCities().size(); y++) {
                    City targetCity = targetCountry.getCities().get(y);
                    AirQuality targetAirQuality = targetCity.getAirQuality();

                    if (!targetAirQuality.getOzoneAqiValue().equalsIgnoreCase(Constants.DELETED_CHARACTER)) {
                        if (startingValue <= Integer.parseInt(targetAirQuality.getOzoneAqiValue()) && endingValue >= Integer.parseInt(targetAirQuality.getOzoneAqiValue())) {
                            result.add(targetCity);
                        }
                    }
                }
            }

            if (result.size() > 0) {
                return Response.ok(result).build();
            } else {
                throw new NoCityFoundException("No City Found", 403);
            }
        } else {
            return Response.status(401).entity("Error 401: Invalid Credentials").build();
        }
    }
}

