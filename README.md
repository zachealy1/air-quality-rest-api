# Air Quality REST API
This RESTful web service provides information about air quality in different regions. The primary resources include countries, cities, and corresponding air quality index (AQI) data. The AQI values are categorized based on different pollutants such as CO (Carbon Monoxide), Ozone, NO2 (Nitrogen Dioxide), and PM2.5 (Particulate Matter).

**This work was completed as a part of my undergraduate degree in Applied Software Engineering at Swansea University, which CGI Inc. sponsored.**

## Project Structure

The project is organized as follows:

### Core Models
- **AirQuality.java**: Represents air quality data, including AQI (Air Quality Index) values.
- **City.java** and **Country.java**: Models representing city and country data, respectively, with air quality information.
- **Location.java**: Holds geographical data about a specific location.
- **Record.java**: Represents individual air quality records.

### Resources (API Endpoints)
- **AirQualityResource.java**: Main endpoint for retrieving air quality data.
- **CitiesResource.java**: Provides endpoints related to city-based air quality information.
- **CountriesResource.java**: Provides endpoints related to country-based air quality information.
- **CoAqiAirQualityResource.java**: Endpoint specifically for CO-related AQI data.
- **OzoneAqiAirQualityResource.java**: Endpoint specifically for Ozone-related AQI data.
- **RecordsResource.java**: Manages endpoints for interacting with individual air quality records.

### Builders
- **FluentCityBuilder.java** and **FluentCountryBuilder.java**: Builder classes for constructing `City` and `Country` objects, allowing for a clean, fluent API.

### Exception Handling
- **AirQualityExceptionMapper.java**: Maps exceptions related to air quality data to HTTP responses.
- **DatabaseNotFoundException.java**, **InvalidAqiCategoryException.java**, **InvalidAqiValueException.java**, **InvalidCityException.java**, **InvalidCountryException.java**, **InvalidRecordException.java**, **NoCityFoundException.java**, **NoCountryFoundException.java**: Custom exceptions to handle various data validation and retrieval issues.

### Utilities
- **Constants.java**: Defines constant values used throughout the API.
- **CsvReadWrite.java**: Provides methods for reading and writing air quality data to CSV files for data persistence.
- **Utility.java**: Contains helper functions used across the project.
- **Validators.java**: Includes validation methods for ensuring data integrity in records and user input.

## Features

- **Air Quality Data Retrieval**: Retrieve real-time and historical air quality data by city, country, and specific AQI categories.
- **Exception Mapping**: Custom exceptions for managing invalid data, including city, country, and AQI values.
- **Data Persistence**: Read from and write air quality records to CSV for persistent storage.
- **Fluent Builders**: Create instances of `City` and `Country` with a fluent, builder-pattern API.

## Getting Started

### Prerequisites

- Java 8 or later
- Maven for dependency management and building

### Setup

1. Clone the repository to your local machine.
2. Navigate to the project directory and build the project with Maven:
   ```shell
   mvn clean install
   ```
   
3. Deploy the application to a GlassFish 6.2.5 server.

## Endpoints Overview
The API provides various endpoints to interact with air quality data. Here’s a brief overview:

- **/airquality:** Main endpoint to get air quality data.
- **/cities:** Retrieve and manage data related to cities.
- **/countries:** Retrieve and manage data related to countries.
- **/records:** Access individual air quality records.
- **/airquality/co:** Get air quality data specifically related to carbon monoxide levels.
- **/airquality/ozone:** Get air quality data specifically related to ozone levels.

## Exception Handling
Custom exceptions are used to manage specific errors and invalid data cases:

- InvalidCityException: Thrown when an invalid or non-existent city is queried.
- InvalidAqiValueException: Thrown when an AQI value is outside acceptable ranges.
- DatabaseNotFoundException: Thrown when a database connection or resource is unavailable. 

## Future Enhancements 
Potential improvements to the API could include:

- Database Integration: Replace or complement CSV with a relational database for better scalability and query capabilities.
- Expanded AQI Data: Support for more pollutants or AQI categories.
- User Authentication: Implement user roles and permissions for accessing certain API endpoints.

## Root Path
`http://localhost:8080/Coursework2-1.0-SNAPSHOT/1908931`

## Resources and HTTP Methods

### Get All Records Resource

#### URI 
`http://localhost:8080/Coursework2-1.0-SNAPSHOT/1908931/records/v1/all-records`

#### Methods
GET: Retrieve a list of all records.

##### Example Request
`GET http://localhost:8080/Coursework2-1.0-SNAPSHOT/1908931/records/v1/all-records`

##### Example Response
[
    {
        "name": "Russian Federation",
        "cities": [
            {
                "name": "Praskoveya",
                "airQuality": {
                    "aqiCategory": "Moderate",
                    "aqiValue": "51",
                    "coAqiCategory": "Good",
                    "coAqiValue": "1",
                    "noAqiCategory": "Good",
                    "noAqiValue": "0",
                    "ozoneAqiCategory": "Good",
                    "ozoneAqiValue": "36",
                    "pmAqiCategory": "Moderate",
                    "pmAqiValue": "51"
                },
                "cityId": 1
            },
            {
                "name": "Pyatigorsk",
                "airQuality": {
                    "aqiCategory": "Moderate",
                    "aqiValue": "54",
                    "coAqiCategory": "Good",
                    "coAqiValue": "1",
                    "noAqiCategory": "Good",
                    "noAqiValue": "1",
                    "ozoneAqiCategory": "Good",
                    "ozoneAqiValue": "41",
                    "pmAqiCategory": "Moderate",
                    "pmAqiValue": "54"
                },
                "cityId": 9
            },
            {
                "name": "Polevskoy",
                "airQuality": {
                    "aqiCategory": "Good",
                    "aqiValue": "31",
                    "coAqiCategory": "Good",
                    "coAqiValue": "1",
                    "noAqiCategory": "Good",
                    "noAqiValue": "0",
                    "ozoneAqiCategory": "Good",
                    "ozoneAqiValue": "31",
                    "pmAqiCategory": "Good",
                    "pmAqiValue": "17"
                },
                "cityId": 29
            }
]    }   ]

### Get All Countries Resource

#### URI 
`http://localhost:8080/Coursework2-1.0-SNAPSHOT/1908931/countries/v1/names`

#### Methods
GET: Retrieve a list of all countries.

##### Example Request
`GET http://localhost:8080/Coursework2-1.0-SNAPSHOT/1908931/countries/v1/names`

##### Example Response
{
  [
    "Russian Federation",
    "Brazil",
    "Italy",
    "Poland",
    "France",
    "United States of America",
    "Germany",
    "Belgium",
    "Egypt",
    "China",
  ]
}   

### Get All Cities Resource

#### URI 
`http://localhost:8080/Coursework2-1.0-SNAPSHOT/1908931/cities/v1/names`

#### Methods
GET: Retrieve a list of all cities.

##### Example Request
`GET http://localhost:8080/Coursework2-1.0-SNAPSHOT/1908931/cities/v1/names`

##### Example Response
{
  [
    "Praskoveya",
    "Pyatigorsk",
    "Polevskoy",
    "Dalnegorsk",
    "Gukovo",
    "Izberbash",
    "Tyukalinsk",
  ]
}

### Get/Create/Update/Delete Air Quality for City Resource

#### URIs
`hhttp://localhost:8080/Coursework2-1.0-SNAPSHOT/1908931/air-quality/v1/{countryName}/{cityName}`

#### Method
GET: Retrieve air quality information for a specific city.
POST: Add new air quality data for a specific city.
PUT: Update air quality information for a specific city.
DELETE: Delete air quality data for a specific city.

##### Example Request
`GET http://localhost:8080/Coursework2-1.0-SNAPSHOT/1908931/air-quality/v1/Russian Federation/Praskoveya`

##### Example Response
{
    "aqiCategory": "Unhealthy for Sensitive Groups",
    "aqiValue": "100",
    "coAqiCategory": "Good",
    "coAqiValue": "1",
    "noAqiCategory": "Good",
    "noAqiValue": "2",
    "ozoneAqiCategory": "Good",
    "ozoneAqiValue": "39",
    "pmAqiCategory": "Moderate",
    "pmAqiValue": "10"
}

##### Example Request
`POST http://localhost:8080/Coursework2-1.0-SNAPSHOT/1908931/air-quality/v1/Spain/Barcelona`

##### Request Body
{
    "aqiCategory": "Moderate",
    "aqiValue": "66",
    "coAqiCategory": "Good",
    "coAqiValue": "1",
    "noAqiCategory": "Good",
    "noAqiValue": "2",
    "ozoneAqiCategory": "Good",
    "ozoneAqiValue": "39",
    "pmAqiCategory": "Moderate",
    "pmAqiValue": "66"
}

##### Example Response
{
  Air Quality Record Created Successfully For Barcelona, Spain
}

##### Example Request
`PUT http://localhost:8080/Coursework2-1.0-SNAPSHOT/1908931/air-quality/v1/Russian Federation/Praskoveya`

##### Request Body
{
    "aqiCategory": "Unhealthy for Sensitive Groups",
    "aqiValue": "100",
    "coAqiCategory": "Good",
    "coAqiValue": "1",
    "noAqiCategory": "Good",
    "noAqiValue": "2",
    "ozoneAqiCategory": "Good",
    "ozoneAqiValue": "39",
    "pmAqiCategory": "Moderate",
    "pmAqiValue": "10"
}

##### Example Response
{
  Air Quality Record Updated Successfully For Praskoveya, Russian Federation
}

##### Example Request
`DELETE http://localhost:8080/Coursework2-1.0-SNAPSHOT/1908931/air-quality/v1/Spain/Barcelona`

##### Example Response
{
  Air Quality Record Deleted Successfully For Barcelona, Spain
}

### Get Cities with Unhealthy CO AQI Category Air Quality Resource

#### URI
`http://localhost:8080/Coursework2-1.0-SNAPSHOT/1908931/co/v1/co-unhealthy-cities`

#### Method
GET: Retrieve a list of cities with unhealthy air quality.

##### Example Request
`GET http://localhost:8080/Coursework2-1.0-SNAPSHOT/1908931/co/v1/co-unhealthy-cities`

##### Example Response
[
    {
        "name": "Durango",
        "airQuality": {
            "aqiCategory": "Hazardous",
            "aqiValue": "500",
            "coAqiCategory": "Unhealthy for Sensitive Groups",
            "coAqiValue": "133",
            "noAqiCategory": "Moderate",
            "noAqiValue": "53",
            "ozoneAqiCategory": "Good",
            "ozoneAqiValue": "0",
            "pmAqiCategory": "Hazardous",
            "pmAqiValue": "500"
        },
        "cityId": 5066
    }
]

### Get Cities with Healthy CO AQI Category Air Quality Resource

#### URI
`http://localhost:8080/Coursework2-1.0-SNAPSHOT/1908931/co/v1/co-healthy-cities`

#### Method
GET: Retrieve a list of cities with healthy air quality.

##### Example Request
`GET http://localhost:8080/Coursework2-1.0-SNAPSHOT/1908931/co/v1/co-healthy-cities`

##### Example Response
[
    {
        "name": "Praskoveya",
        "airQuality": {
            "aqiCategory": "Moderate",
            "aqiValue": "51",
            "coAqiCategory": "Good",
            "coAqiValue": "1",
            "noAqiCategory": "Good",
            "noAqiValue": "0",
            "ozoneAqiCategory": "Good",
            "ozoneAqiValue": "36",
            "pmAqiCategory": "Moderate",
            "pmAqiValue": "51"
        },
        "cityId": 1
    }
]

### Get Cities within Ozone AQI Category Air Quality Resource

#### URI
`http://127.0.0.1:9090/1908031/ozone-aqi-category/{starting_value}/{ending_value}`

#### Method
GET: Retrieve a list of countries with unhealthy air quality.

##### Example Request
`GET http://localhost:8080/Coursework2-1.0-SNAPSHOT/1908931/ozone/v1/ozone-aqi-value/?startingValue=0&endingValue=10`

##### Example Response
[
    {
        "name": "Balashikha",
        "airQuality": {
            "aqiCategory": "Unhealthy for Sensitive Groups",
            "aqiValue": "128",
            "coAqiCategory": "Good",
            "coAqiValue": "4",
            "noAqiCategory": "Good",
            "noAqiValue": "36",
            "ozoneAqiCategory": "Good",
            "ozoneAqiValue": "9",
            "pmAqiCategory": "Unhealthy for Sensitive Groups",
            "pmAqiValue": "128"
        },
        "cityId": 4057
    }
]