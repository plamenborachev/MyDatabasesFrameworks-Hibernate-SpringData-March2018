package app.retake.controllers;

import app.retake.Config;
import app.retake.domain.dto.binding.json.AnimalJSONImportDTO;
import app.retake.domain.dto.view.AnimalsJSONExportDTO;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Controller
public class AnimalController {

    private final AnimalService animalService;
    private final Parser parser;

    @Autowired
    public AnimalController(AnimalService animalService, @Qualifier("JSONParser") Parser parser) {
        this.animalService = animalService;
        this.parser = parser;
    }

    public String importDataFromJSON(String jsonContent) {
        AnimalJSONImportDTO[] animalJSONImportDTOS = new AnimalJSONImportDTO[0];
        try {
            animalJSONImportDTOS = this.parser.read(AnimalJSONImportDTO[].class, jsonContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        for (AnimalJSONImportDTO animalJSONImportDTO : animalJSONImportDTOS) {
            try {
                if (ValidationUtil.isValid(animalJSONImportDTO)) {
                    this.animalService.create(animalJSONImportDTO);
                    sb.append(String.format("Record %s Passport №: %s successfully imported.",
                            animalJSONImportDTO.getName(),
                            animalJSONImportDTO.getPassport().getSerialNumber()));
                    sb.append(System.lineSeparator());
                } else {
                    sb.append(Config.ERROR_MESSAGE);
                    sb.append(System.lineSeparator());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public String exportAnimalsByOwnerPhoneNumber(String phoneNumber) {
        List<AnimalsJSONExportDTO> animalsJSONExportDTOS = this.animalService.findByOwnerPhoneNumber(phoneNumber);
        String write = null;
        try {
            write = this.parser.write(animalsJSONExportDTOS);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return write;
    }
}
