package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.Config;
import org.softuni.mostwanted.domain.dto.binding.json.TownJSONImportDTO;
import org.softuni.mostwanted.domain.dto.view.json.TownJsonExportDTO;
import org.softuni.mostwanted.domain.models.Town;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class TownController {

    private final TownService townService;
    private final Parser parser;

    @Autowired
    public TownController(TownService townService, @Qualifier("JSONParser") Parser parser) {
        this.townService = townService;
        this.parser = parser;
    }

    public String importDataFromJSON(String jsonContent) {
        TownJSONImportDTO[] townJSONImportDTOS = null;
        try {
            townJSONImportDTOS = this.parser.read(TownJSONImportDTO[].class, jsonContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        for (TownJSONImportDTO townJSONImportDTO : townJSONImportDTOS) {
            try {
                if (ValidationUtil.isValid(townJSONImportDTO)){
                    this.townService.create(townJSONImportDTO);
                    sb.append(String.format(Config.SUCCESS_MESSAGE_TOWNS_DISTRICTS_RACERS,
                            "Town", townJSONImportDTO.getName()));
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

    public String exportRacingTownsJson() throws IOException, JAXBException {
        List<TownJsonExportDTO> townJsonExportDTOS = this.townService.getRacingTowns();
        return this.parser.write(townJsonExportDTOS);
    }
}
