package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.Config;
import org.softuni.mostwanted.domain.dto.binding.json.RacerJSONImportDTO;
import org.softuni.mostwanted.domain.models.Racer;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.RacerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class RacerController {

    private final RacerService racerService;
    private final Parser parser;

    public RacerController(RacerService racerService, @Qualifier("JSONParser")Parser parser) {
        this.racerService = racerService;
        this.parser = parser;
    }

    public String importDataFromJSON(String jsonContent) {
        RacerJSONImportDTO[] racerJSONImportDTOS
                = new RacerJSONImportDTO[0];
        try {
            racerJSONImportDTOS = this.parser.read(RacerJSONImportDTO[].class, jsonContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (RacerJSONImportDTO racerJSONImportDTO : racerJSONImportDTOS) {
            try {
                if (ValidationUtil.isValid(racerJSONImportDTO)){
                    this.racerService.create(racerJSONImportDTO);
                    sb.append(String.format(Config.SUCCESS_MESSAGE_TOWNS_DISTRICTS_RACERS,
                            "Racer", racerJSONImportDTO.getName()));
                    sb.append(System.lineSeparator());
                } else {
                    sb.append(Config.DUPLICATE_DATA_MESSAGE);
                    sb.append(System.lineSeparator());
                }
            } catch (Exception e) {
//                e.printStackTrace();
                sb.append(Config.ERROR_MESSAGE);
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public String exportDataToJson() {
        List<Racer> racersWithCars = this.racerService.getRacersWithCars();
        return null;
    }

    public String exportMostWanted() {



        return null;
    }
}
