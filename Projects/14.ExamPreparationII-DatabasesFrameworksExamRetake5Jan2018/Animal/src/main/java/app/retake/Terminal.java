package app.retake;

import app.retake.controllers.AnimalAidController;
import app.retake.controllers.AnimalController;
import app.retake.controllers.ProcedureController;
import app.retake.controllers.VetController;
import app.retake.io.api.ConsoleIO;
import app.retake.io.api.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Component
public class Terminal implements CommandLineRunner {

    private final FileIO fileIO;
    private final ConsoleIO consoleIO;
    private final AnimalAidController animalAidController;
    private final AnimalController animalController;
    private final VetController vetController;
    private final ProcedureController procedureController;

    @Autowired
    public Terminal(FileIO fileIO, ConsoleIO consoleIO, AnimalAidController animalAidController, AnimalController
            animalController, VetController vetController, ProcedureController procedureController) {
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
        this.animalAidController = animalAidController;
        this.animalController = animalController;
        this.vetController = vetController;
        this.procedureController = procedureController;
    }

    @Override
    public void run(String... strings) throws Exception {

        importAnimalAidsJson();
        importAnimalsJson();
        importVetsXml();
        importProceduressXml();

        exportJson();
        exportXml();
    }

    private void exportXml() throws IOException, JAXBException {
        this.consoleIO.write(this.procedureController.exportProcedures());
    }

    private void exportJson() {
        this.consoleIO.write(this.animalController.exportAnimalsByOwnerPhoneNumber("0887446123"));
    }

    private void importProceduressXml() throws IOException {
        this.consoleIO.write(this.procedureController.importDataFromXML(this.fileIO.read(Config.PROCEDURES_IMPORT_XML)));
    }

    private void importVetsXml() throws IOException {
        this.consoleIO.write(this.vetController.importDataFromXML(this.fileIO.read(Config.VETS_IMPORT_XML)));
    }

    private void importAnimalsJson() throws IOException {
        this.consoleIO.write(this.animalController.importDataFromJSON(this.fileIO.read(Config.ANIMALS_IMPORT_JSON)));
    }

    private void importAnimalAidsJson() throws IOException {
        this.consoleIO.write(this.animalAidController.importDataFromJSON(this.fileIO.read(Config.ANIMAL_AIDS_IMPORT_JSON)));
    }
}
