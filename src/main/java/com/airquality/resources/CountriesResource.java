/**
 * The CountriesResource file provides RESTful web services for retrieving information related to countries.
 */
package com.airquality.resources;

import com.airquality.constants.Constants;
import com.airquality.csv.CsvReadWrite;
import com.airquality.exceptions.*;
import com.airquality.record.Record;
import com.airquality.utility.Utility;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.CacheControl;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

/**
 * The CountriesResource class provides RESTful web services for retrieving information related to countries.
 */
@Path("/countries/v1")
public class CountriesResource {

    /**
     * Retrieves the names of all countries.
     *
     * @param authorisationHeader The authorization header for authentication.
     * @return The response containing the names of all countries in JSON format.
     * @throws NoCountryFoundException   If no countries are found.
     * @throws DatabaseNotFoundException If there is an issue connecting to the database.
     */
    @GET
    @Path("/names")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCountries(
            @HeaderParam("Authorization") String authorisationHeader) throws NoCountryFoundException, DatabaseNotFoundException {

        if (Utility.getInstance().isAuthorised(authorisationHeader)) {
            CsvReadWrite.readDataFromCSV(Constants.FILE_PATH);
            ArrayList<String> result = new ArrayList<>();

            for (int i = 0; i < Record.getInstance().getCountryRecords().size(); i++) {
                result.add(Record.getInstance().getCountryRecords().get(i).getName());
            }

            if (result.size() > 0) {
                CacheControl cacheControl = new CacheControl();
                cacheControl.setMaxAge(5);
                return Response.ok(result).cacheControl(cacheControl).build();
            } else {
                throw new NoCountryFoundException("No Countries Found", 403);
            }
        } else {
            return Response.status(401).entity("Error 401: Invalid Credentials").build();
        }
    }
}
