package app.retake.controllers;

import app.retake.Config;
import app.retake.domain.dto.binding.json.AnimalAidJSONImportDTO;
import app.retake.parser.JSONParser;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class AnimalAidController {

    private final AnimalAidService animalAidService;
    private final Parser parser;

    @Autowired
    public AnimalAidController(AnimalAidService animalAidService, JSONParser jsonParser, @Qualifier("JSONParser") Parser parser) {
        this.animalAidService = animalAidService;
        this.parser = parser;
    }

    public String importDataFromJSON(String jsonContent) {
        AnimalAidJSONImportDTO[] animalAidJSONImportDTOS
                = new AnimalAidJSONImportDTO[0];

        try {
            animalAidJSONImportDTOS = this.parser.read(AnimalAidJSONImportDTO[].class, jsonContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        for (AnimalAidJSONImportDTO animalAidJSONImportDTO : animalAidJSONImportDTOS) {
            try{
                if (ValidationUtil.isValid(animalAidJSONImportDTO)){
                    this.animalAidService.create(animalAidJSONImportDTO);
                    sb.append(String.format("Record %s successfully imported.",
                            animalAidJSONImportDTO.getName()));
                    sb.append(System.lineSeparator());
                } else {
                    sb.append(Config.ERROR_MESSAGE);
                    sb.append(System.lineSeparator());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
