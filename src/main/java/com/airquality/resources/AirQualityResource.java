/**
 * The AirQualityResource file defines RESTful web services for managing air quality data.
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
import jakarta.ws.rs.core.CacheControl;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * The AirQualityResource class defines RESTful web services for managing air quality data.
 * It provides endpoints for retrieving, creating, updating, and deleting air quality records.
 */
@Path("/air-quality/v1")
public class AirQualityResource {

    /**
     * Retrieves air quality data for a specific city in a given country.
     *
     * @param countryName         The name of the country.
     * @param cityName            The name of the city.
     * @param authorisationHeader The authorization header for authentication.
     * @return The response containing the air quality data in JSON format.
     * @throws NoCityFoundException      If the specified city is not found.
     * @throws NoCountryFoundException   If the specified country is not found.
     * @throws DatabaseNotFoundException If there is an issue connecting to the database.
     */
    @GET
    @Path("/{countryName}/{cityName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCityAirQuality(@PathParam("countryName") String countryName,
                                      @PathParam("cityName") String cityName,
                                      @HeaderParam("Authorization") String authorisationHeader) throws NoCityFoundException, NoCountryFoundException, DatabaseNotFoundException {

        if (Utility.getInstance().isAuthorised(authorisationHeader)) {
            CsvReadWrite.readDataFromCSV(Constants.FILE_PATH);
            AirQuality result = null;

            for (int i = 0; i < Record.getInstance().getCountryRecords().size(); i++) {
                if (Record.getInstance().getCountryRecords().get(i).getName().equalsIgnoreCase(countryName)) {
                    Country targetCountry = Record.getInstance().getCountryRecords().get(i);
                    for (int y = 0; y < targetCountry.getCities().size(); y++) {
                        if (targetCountry.getCities().get(y).getName().equalsIgnoreCase(cityName)) {
                            City targetCity = targetCountry.getCities().get(y);
                            result = targetCity.getAirQuality();
                        }
                    }
                }
            }

            if (result != null) {
                CacheControl cacheControl = new CacheControl();
                cacheControl.setMaxAge(5);
                return Response.ok(result).cacheControl(cacheControl).build();
            }

            if (!Utility.getInstance().doesCountryExist(countryName)) {
                throw new NoCountryFoundException("Country not found in the database", 403);
            } else {
                throw new NoCityFoundException("City not found in the database", 403);
            }
        } else {
            return Response.status(401).entity("Error 401: Invalid Credentials").build();
        }
    }

    /**
     * Creates a new air quality record for a specific city in a given country.
     *
     * @param countryName         The name of the country.
     * @param cityName            The name of the city.
     * @param airQualityRecord    The air quality data to be recorded.
     * @param authorisationHeader The authorization header for authentication.
     * @return The response indicating the success of the operation.
     * @throws InvalidCityException        If the specified city is invalid.
     * @throws DatabaseNotFoundException   If there is an issue connecting to the database.
     * @throws InvalidAqiValueException    If there is an issue with the air quality value.
     * @throws InvalidAqiCategoryException If there is an issue with the air quality category.
     */
    @POST
    @Path("/{countryName}/{cityName}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCityAirQuality(@PathParam("countryName") String countryName,
                                         @PathParam("cityName") String cityName, AirQuality airQualityRecord,
                                         @HeaderParam("Authorization") String authorisationHeader) throws InvalidCityException, DatabaseNotFoundException, InvalidAqiValueException, InvalidAqiCategoryException {

        CsvReadWrite.readDataFromCSV(Constants.FILE_PATH);

        if (Utility.getInstance().isAuthorised(authorisationHeader) && Utility.getInstance().isAirQualityValid(airQualityRecord)) {
            if (Utility.getInstance().doesCityExist(countryName, cityName)) {
                throw new InvalidCityException("City already exists", 403);
            } else {
                CsvReadWrite.appendDataToCSV(Constants.FILE_PATH, countryName, cityName, airQualityRecord);
                CacheControl cacheControl = new CacheControl();
                cacheControl.setMaxAge(5);
                return Response.ok("Air Quality Record Created Successfully For " + cityName + ", " + countryName).cacheControl(cacheControl).build();
            }
        } else {
            return Response.status(401).entity("Error 401: Invalid Credentials").build();
        }
    }

    /**
     * Updates the air quality record for a specific city in a given country.
     *
     * @param countryName         The name of the country.
     * @param cityName            The name of the city.
     * @param airQualityRecord    The updated air quality data.
     * @param authorisationHeader The authorization header for authentication.
     * @return The response indicating the success of the operation.
     * @throws InvalidCountryException     If the specified country is invalid.
     * @throws InvalidCityException        If the specified city is invalid.
     * @throws DatabaseNotFoundException   If there is an issue connecting to the database.
     * @throws InvalidAqiValueException    If there is an issue with the air quality value.
     * @throws InvalidAqiCategoryException If there is an issue with the air quality category.
     */
    @PUT
    @Path("/{countryName}/{cityName}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCityAirQuality(@PathParam("countryName") String countryName,
                                         @PathParam("cityName") String cityName, AirQuality airQualityRecord,
                                         @HeaderParam("Authorization") String authorisationHeader) throws InvalidCountryException, InvalidCityException, DatabaseNotFoundException, InvalidAqiValueException, InvalidAqiCategoryException {

        if (Utility.getInstance().isAuthorised(authorisationHeader) && Utility.getInstance().isAirQualityValid(airQualityRecord)) {
            CsvReadWrite.readDataFromCSV(Constants.FILE_PATH);
            int cityId = Utility.getInstance().getCityId(countryName, cityName);
            CsvReadWrite.updateCsvLine(Constants.FILE_PATH, countryName, cityId, airQualityRecord);
            CacheControl cacheControl = new CacheControl();
            cacheControl.setMaxAge(5);
            return Response.ok("Air Quality Record Updated Successfully For " + cityName + ", " + countryName).cacheControl(cacheControl).build();
        } else {
            return Response.status(401).entity("Error 401: Invalid Credentials").build();
        }
    }

    /**
     * Deletes the air quality record for a specific city in a given country.
     *
     * @param countryName         The name of the country.
     * @param cityName            The name of the city.
     * @param authorisationHeader The authorization header for authentication.
     * @return The response indicating the success of the operation.
     * @throws InvalidCountryException   If the specified country is invalid.
     * @throws InvalidCityException      If the specified city is invalid.
     * @throws DatabaseNotFoundException If there is an issue connecting to the database.
     */
    @DELETE
    @Path("/{countryName}/{cityName}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteCityAirQuality(@PathParam("countryName") String countryName,
                                         @PathParam("cityName") String cityName,
                                         @HeaderParam("Authorization") String authorisationHeader) throws InvalidCountryException, InvalidCityException, DatabaseNotFoundException {

        if (Utility.getInstance().isAuthorised(authorisationHeader)) {
            CsvReadWrite.readDataFromCSV(Constants.FILE_PATH);
            int lineNumber = Utility.getInstance().getCityId(countryName, cityName);
            CsvReadWrite.deleteCsvLine(Constants.FILE_PATH, countryName, cityName, lineNumber);
            CacheControl cacheControl = new CacheControl();
            cacheControl.setMaxAge(5);
            return Response.ok("Air Quality Record Deleted Successfully For " + cityName + ", " + countryName).cacheControl(cacheControl).build();
        } else {
            return Response.status(401).entity("Error 401: Invalid Credentials").build();
        }
    }
}

