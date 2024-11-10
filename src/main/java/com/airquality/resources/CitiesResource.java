/**
 * The CitiesResource file defines RESTful web services for managing city data.
 */
package com.airquality.resources;

import com.airquality.constants.Constants;
import com.airquality.csv.CsvReadWrite;
import com.airquality.exceptions.*;
import com.airquality.locations.Country;
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
 * The CitiesResource class defines RESTful web services for managing city data.
 * It provides an endpoint for retrieving the names of all cities.
 */
@Path("/cities/v1")
public class CitiesResource {

    /**
     * Retrieves the names of all cities.
     *
     * @param authorisationHeader The authorization header for authentication.
     * @return The response containing the names of all cities in JSON format.
     * @throws NoCityFoundException      If no cities are found.
     * @throws DatabaseNotFoundException If there is an issue connecting to the database.
     */
    @GET
    @Path("/names")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCities(
            @HeaderParam("Authorization") String authorisationHeader) throws NoCityFoundException, DatabaseNotFoundException {

        if (Utility.getInstance().isAuthorised(authorisationHeader)) {
            CsvReadWrite.readDataFromCSV(Constants.FILE_PATH);
            ArrayList<String> result = new ArrayList<>();

            for (int i = 0; i < Record.getInstance().getCountryRecords().size(); i++) {
                Country targetCountry = Record.getInstance().getCountryRecords().get(i);

                for (int y = 0; y < targetCountry.getCities().size(); y++) {
                    result.add(targetCountry.getCities().get(y).getName());
                }
            }

            if (result.size() > 0) {
                CacheControl cacheControl = new CacheControl();
                cacheControl.setMaxAge(5);
                return Response.ok(result).cacheControl(cacheControl).build();
            } else {
                throw new NoCityFoundException("No Cities Found", 403);
            }
        } else {
            return Response.status(401).entity("Error 401: Invalid Credentials").build();
        }
    }
}

