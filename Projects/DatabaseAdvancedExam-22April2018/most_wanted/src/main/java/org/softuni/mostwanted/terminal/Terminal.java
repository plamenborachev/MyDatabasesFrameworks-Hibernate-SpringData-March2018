package org.softuni.mostwanted.terminal;

import org.softuni.mostwanted.Config;
import org.softuni.mostwanted.controllers.*;
import org.softuni.mostwanted.io.interfaces.ConsoleIO;
import org.softuni.mostwanted.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private final ConsoleIO consoleIO;
    private final FileIO fileIO;
    private final TownController townController;
    private final DistrictController districtController;
    private final RacerController racerController;
    private final CarController carController;
    private final RaceEntryController raceEntryController;
    private final RaceController raceController;

    @Autowired
    public Terminal(ConsoleIO consoleIO, FileIO fileIO, TownController townController, DistrictController districtController, RacerController racerController, CarController carController, RaceEntryController raceEntryController, RaceController raceController) {
        this.consoleIO = consoleIO;
        this.fileIO = fileIO;
        this.townController = townController;
        this.districtController = districtController;
        this.racerController = racerController;
        this.carController = carController;
        this.raceEntryController = raceEntryController;
        this.raceController = raceController;
    }

    @Override
    public void run(String... args) throws Exception {
        importTownsJson();
        importDistrictsJson();
        importRacersJson();
        importCarsJson();
        importRaceEntriesXml();
        importRacesXml();

        exportRacingTownsJson();
//        exportRacingCarsJson();
        exportMostWantedXml();

    }

    private void exportMostWantedXml() throws IOException {
        this.fileIO.write(this.racerController.exportMostWanted(), Config.MOST_WANTED_EXPORT_XML);
    }

    private void exportRacingCarsJson() throws IOException {
        this.fileIO.write(this.racerController.exportDataToJson(), Config.RACERS_EXPORT_JSON);
    }

    private void exportRacingTownsJson() throws IOException, JAXBException {
        this.fileIO.write(this.townController.exportRacingTownsJson(), Config.RACING_TOWNS_EXPORT_JSON);
    }

    private void importRacesXml() throws IOException {
        this.consoleIO.write(this.raceController.importDataFromXML(this.fileIO.read(Config.RACES_IMPORT_XML)));
    }

    private void importRaceEntriesXml() throws IOException {
        this.consoleIO.write(this.raceEntryController.importDataFromXML(this.fileIO.read(Config.RACE_ENTRIES_IMPORT_XML)));
    }

    private void importCarsJson() throws IOException {
        this.consoleIO.write(this.carController.importDataFromJSON(this.fileIO.read(Config.CARS_IMPORT_JSON)));
    }

    private void importRacersJson() throws IOException {
        this.consoleIO.write(this.racerController.importDataFromJSON(this.fileIO.read(Config.RACERS_IMPORT_JSON)));
    }

    private void importDistrictsJson() throws IOException, JAXBException {
        this.consoleIO.write(this.districtController.importDataFromJSON(this.fileIO.read(Config.DISTRICTS_IMPORT_JSON)));
    }

    private void importTownsJson() throws IOException {
        this.consoleIO.write(this.townController.importDataFromJSON(this.fileIO.read(Config.TOWNS_IMPORT_JSON)));
    }
}
