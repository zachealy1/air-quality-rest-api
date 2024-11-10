/**
 * CsvReadWrite file provides methods for reading and writing data to a CSV file.
 * It also includes methods for appending, updating, and deleting data in the CSV file.
 */
package com.airquality.csv;

import com.airquality.airquality.AirQuality;
import com.airquality.builders.FluentCityBuilder;
import com.airquality.builders.FluentCountryBuilder;
import com.airquality.exceptions.*;
import com.airquality.locations.City;
import com.airquality.constants.Constants;
import com.airquality.locations.Country;
import com.airquality.record.Record;
import com.airquality.utility.Utility;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * CsvReadWrite class provides methods for reading and writing data to a CSV file.
 * It also includes methods for appending, updating, and deleting data in the CSV file.
 */
public class CsvReadWrite {

	/**
	 * Appends data to the CSV file.
	 *
	 * @param fileName   The name of the CSV file.
	 * @param country    The country name.
	 * @param city       The city name.
	 * @param airQuality The AirQuality object.
	 * @throws DatabaseNotFoundException If there is an issue with the database.
	 */
	public static void appendDataToCSV(String fileName, String country, String city,
									   AirQuality airQuality) throws DatabaseNotFoundException {
		try (FileWriter fileWriter = new FileWriter(fileName, true)) {
			fileWriter.append(country).append(Constants.DELIMITER);
			fileWriter.append(city).append(Constants.DELIMITER);
			fileWriter.append(String.valueOf(airQuality.getAqiValue())).append(Constants.DELIMITER);
			fileWriter.append(airQuality.getAqiCategory()).append(Constants.DELIMITER);

			fileWriter.append(String.valueOf(airQuality.getCoAqiValue())).append(Constants.DELIMITER);
			fileWriter.append(airQuality.getCoAqiCategory()).append(Constants.DELIMITER);

			fileWriter.append(String.valueOf(airQuality.getOzoneAqiValue())).append(Constants.DELIMITER);
			fileWriter.append(airQuality.getOzoneAqiCategory()).append(Constants.DELIMITER);

			fileWriter.append(String.valueOf(airQuality.getNoAqiValue())).append(Constants.DELIMITER);
			fileWriter.append(airQuality.getNoAqiCategory()).append(Constants.DELIMITER);

			fileWriter.append(String.valueOf(airQuality.getPmAqiValue())).append(Constants.DELIMITER);
			fileWriter.append(airQuality.getPmAqiCategory()).append(Constants.DELIMITER);
			fileWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
		CsvReadWrite.readDataFromCSV(Constants.FILE_PATH);
	}

	/**
	 * Updates the specified CSV file with the air quality information for a specific city in a given country.
	 * The file is cleared, and all data is written again with the updated information.
	 *
	 * @param fileName   The name of the CSV file to be updated.
	 * @param country    The name of the country.
	 * @param cityId     The ID of the city to be updated.
	 * @param airQuality The new air quality information for the city.
	 * @throws InvalidCountryException   If the specified country is not found.
	 * @throws DatabaseNotFoundException If the air quality database is not found.
	 */
	public static void updateCsvLine(String fileName, String country, int cityId,
									 AirQuality airQuality) throws InvalidCountryException, DatabaseNotFoundException {
		Country targetCountry = Utility.getInstance().getCountryByName(country);
		for (int i = 0; i < targetCountry.getCities().size(); i++) {
			if (targetCountry.getCities().get(i).getCityId() == cityId) {
				targetCountry.getCities().get(i).setAirQuality(airQuality);
			}
		}
		clearFile(fileName);

		try (FileWriter fileWriter = new FileWriter(fileName, true)) {
			fileWriter.append(Constants.CSV_HEADER);
			fileWriter.append("\n");
			for (int i = 0; i < Record.getInstance().getCountryRecords().size(); i++) {
				Country printingCountry = Record.getInstance().getCountryRecords().get(i);
				for (int y = 0; y < printingCountry.getCities().size(); y++) {
					City printingCity = printingCountry.getCities().get(y);
					fileWriter.append(printingCountry.getName()).append(Constants.DELIMITER);
					fileWriter.append(printingCity.getName()).append(Constants.DELIMITER);
					fileWriter.append(String.valueOf(printingCity.getAirQuality().getAqiValue())).append(Constants.DELIMITER);
					fileWriter.append(printingCity.getAirQuality().getAqiCategory()).append(Constants.DELIMITER);

					fileWriter.append(String.valueOf(printingCity.getAirQuality().getCoAqiValue())).append(Constants.DELIMITER);
					fileWriter.append(printingCity.getAirQuality().getCoAqiCategory()).append(Constants.DELIMITER);

					fileWriter.append(String.valueOf(printingCity.getAirQuality().getOzoneAqiValue())).append(Constants.DELIMITER);
					fileWriter.append(printingCity.getAirQuality().getOzoneAqiCategory()).append(Constants.DELIMITER);

					fileWriter.append(String.valueOf(printingCity.getAirQuality().getNoAqiValue())).append(Constants.DELIMITER);
					fileWriter.append(printingCity.getAirQuality().getNoAqiCategory()).append(Constants.DELIMITER);

					fileWriter.append(String.valueOf(printingCity.getAirQuality().getPmAqiValue())).append(Constants.DELIMITER);
					fileWriter.append(printingCity.getAirQuality().getPmAqiCategory()).append(Constants.DELIMITER);
					fileWriter.append("\n");
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		CsvReadWrite.readDataFromCSV(Constants.FILE_PATH);
	}

	/**
	 * Deletes the specified CSV file entry related to air quality data for a specific city in a given country.
	 * The file is cleared, and all data is written again with the updated information (with the specified entry
	 * marked as deleted).
	 *
	 * @param fileName The name of the CSV file to be updated.
	 * @param country  The name of the country.
	 * @param city     The name of the city to be deleted.
	 * @param cityId   The ID of the city to be deleted.
	 * @throws InvalidCountryException   If the specified country is not found.
	 * @throws DatabaseNotFoundException If the air quality database is not found.
	 */
	public static void deleteCsvLine(String fileName, String country, String city,
									 int cityId) throws DatabaseNotFoundException, InvalidCountryException {
		Country targetCountry = Utility.getInstance().getCountryByName(country);
		for (int i = 0; i < targetCountry.getCities().size(); i++) {
			if (targetCountry.getCities().get(i).getCityId() == cityId) {
				targetCountry.getCities().get(i).setAirQuality(new AirQuality(Constants.DELETED_CHARACTER, Constants.DELETED_CHARACTER, Constants.DELETED_CHARACTER, Constants.DELETED_CHARACTER, Constants.DELETED_CHARACTER, Constants.DELETED_CHARACTER, Constants.DELETED_CHARACTER, Constants.DELETED_CHARACTER, Constants.DELETED_CHARACTER, Constants.DELETED_CHARACTER));
			}
		}
		clearFile(fileName);

		try (FileWriter fileWriter = new FileWriter(fileName, true)) {
			fileWriter.append(Constants.CSV_HEADER);
			fileWriter.append("\n");
			for (int i = 0; i < Record.getInstance().getCountryRecords().size(); i++) {
				Country printingCountry = Record.getInstance().getCountryRecords().get(i);
				for (int y = 0; y < printingCountry.getCities().size(); y++) {
					City printingCity = printingCountry.getCities().get(y);
					fileWriter.append(printingCountry.getName()).append(Constants.DELIMITER);
					fileWriter.append(printingCity.getName()).append(Constants.DELIMITER);
					fileWriter.append(String.valueOf(printingCity.getAirQuality().getAqiValue())).append(Constants.DELIMITER);
					fileWriter.append(printingCity.getAirQuality().getAqiCategory()).append(Constants.DELIMITER);

					fileWriter.append(String.valueOf(printingCity.getAirQuality().getCoAqiValue())).append(Constants.DELIMITER);
					fileWriter.append(printingCity.getAirQuality().getCoAqiCategory()).append(Constants.DELIMITER);

					fileWriter.append(String.valueOf(printingCity.getAirQuality().getOzoneAqiValue())).append(Constants.DELIMITER);
					fileWriter.append(printingCity.getAirQuality().getOzoneAqiCategory()).append(Constants.DELIMITER);

					fileWriter.append(String.valueOf(printingCity.getAirQuality().getNoAqiValue())).append(Constants.DELIMITER);
					fileWriter.append(printingCity.getAirQuality().getNoAqiCategory()).append(Constants.DELIMITER);

					fileWriter.append(String.valueOf(printingCity.getAirQuality().getPmAqiValue())).append(Constants.DELIMITER);
					fileWriter.append(printingCity.getAirQuality().getPmAqiCategory()).append(Constants.DELIMITER);
					fileWriter.append("\n");
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		CsvReadWrite.readDataFromCSV(Constants.FILE_PATH);
	}

	/**
	 * Reads data from the CSV file and populates the Record singleton instance.
	 *
	 * @param fileName The name of the CSV file.
	 * @throws DatabaseNotFoundException If there is an issue with the database.
	 */
	public static void readDataFromCSV(String fileName) throws DatabaseNotFoundException {
		Record.getInstance().setCountryRecords(new ArrayList<>());
		City.nextId = 1;
		File file = new File(fileName);
		try (Scanner fileReader = new Scanner(file)) {

			fileReader.useDelimiter(",");
			fileReader.nextLine();
			while (fileReader.hasNext()) {
				try {
					String country = Utility.getInstance().isString(fileReader.next());
					String city = Utility.getInstance().isString(fileReader.next());
					String aqiValue = Utility.getInstance().isInteger(fileReader.next());
					String aqiCategory = Utility.getInstance().isString(fileReader.next());
					String coAqiValue = Utility.getInstance().isInteger(fileReader.next());
					String coAqiCategory = Utility.getInstance().isString(fileReader.next());
					String ozoneAqiValue = Utility.getInstance().isInteger(fileReader.next());
					String ozoneAqiCategory = Utility.getInstance().isString(fileReader.next());
					String noAqiValue = Utility.getInstance().isInteger(fileReader.next());
					String noAqiCategory = Utility.getInstance().isString(fileReader.next());
					String pmAqiValue = Utility.getInstance().isInteger(fileReader.next());
					String pmAqiCategory = Utility.getInstance().isString(fileReader.nextLine().replaceAll(Constants.DELIMITER, Constants.BLANK).trim());

					if (!Utility.getInstance().doesCountryExist(country)) {
						Country newCountry = new FluentCountryBuilder(country).startCity(city).addAirQuality(aqiValue, aqiCategory, coAqiValue, coAqiCategory, ozoneAqiValue, ozoneAqiCategory, noAqiValue, noAqiCategory, pmAqiValue, pmAqiCategory).endCity().build();
						Record.getInstance().getCountryRecords().add(newCountry);
					} else {
						City newCity = new FluentCityBuilder(city).addAirQuality(aqiValue, aqiCategory, coAqiValue, coAqiCategory, ozoneAqiValue, ozoneAqiCategory, noAqiValue, noAqiCategory, pmAqiValue, pmAqiCategory).build();
						Utility.getInstance().getCountryByName(country).getCities().add(newCity);
					}
				} catch (InvalidRecordException | InvalidCountryException e) {
					fileReader.nextLine();
					System.err.println(e.getLocalizedMessage());
				} catch (InvalidAqiCategoryException | InvalidAqiValueException e) {
					throw new RuntimeException(e);
				}
			}
		} catch (FileNotFoundException e) {
			throw new DatabaseNotFoundException("Cannot Connect to Database", 403);
		}
	}

	/**
	 * Clears the content of the specified file.
	 * This method opens the file for writing and immediately closes it, effectively clearing its content.
	 *
	 * @param fileName The name of the file to be cleared.
	 */
	private static void clearFile(String fileName) {
		// Clear the CSV file
		try (FileWriter fileWriter = new FileWriter(fileName)) {
			// Do nothing here, just close the file to clear its content
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
