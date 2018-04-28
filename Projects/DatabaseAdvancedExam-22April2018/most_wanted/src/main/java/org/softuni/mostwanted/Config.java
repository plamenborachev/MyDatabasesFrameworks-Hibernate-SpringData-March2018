package org.softuni.mostwanted;

public final class Config {

    public static final String TOWNS_IMPORT_JSON = "/files/json/input/towns.json";
    public static final String DISTRICTS_IMPORT_JSON = "/files/json/input/districts.json";
    public static final String RACERS_IMPORT_JSON = "/files/json/input/racers.json";
    public static final String CARS_IMPORT_JSON = "/files/json/input/cars.json";

    public static final String RACE_ENTRIES_IMPORT_XML = "/files/xml/input/race-entries.xml";
    public static final String RACES_IMPORT_XML = "/files/xml/input/races.xml";

    public static final String SUCCESS_MESSAGE_TOWNS_DISTRICTS_RACERS
            = "Succesfully imported %s – %s.";
    public static final String SUCCESS_MESSAGE_CARS = "Succesfully imported %s – %s %s @ %d.";
        public static final String SUCCESS_MESSAGE_RACE_RACE_ENTRY
                = "Succesfully imported %s – %d.";
    public static final String ERROR_MESSAGE = "Error: Invalid data.";
    public static final String DUPLICATE_DATA_MESSAGE = "Error: Duplicate Data!";

    public static final String  RACING_TOWNS_EXPORT_JSON = "src/main/resources/files/json/output/racingTowns.json";
    public static final String  RACERS_EXPORT_JSON = "src/main/resources/files/json/output/racingCars.json";
    public static final String  MOST_WANTED_EXPORT_XML = "src/main/resources/files/xml/output/most-wanted.xml";
}
