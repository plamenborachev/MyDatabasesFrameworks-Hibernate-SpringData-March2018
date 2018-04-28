package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.Config;
import org.softuni.mostwanted.domain.dto.binding.json.DistrictJSONImportDTO;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class DistrictController {

    private final DistrictService districtService;
    private final Parser parser;

    @Autowired
    public DistrictController(DistrictService districtService, @Qualifier("JSONParser") Parser parser) {
        this.districtService = districtService;
        this.parser = parser;
    }

    public String importDataFromJSON(String jsonContent) throws IOException, JAXBException {
        DistrictJSONImportDTO[] districtJSONImportDTOS
                = this.parser.read(DistrictJSONImportDTO[].class, jsonContent);
        StringBuilder sb = new StringBuilder();
        for (DistrictJSONImportDTO districtJSONImportDTO : districtJSONImportDTOS) {
            try {
                if (ValidationUtil.isValid(districtJSONImportDTO)){
                    this.districtService.create(districtJSONImportDTO);
                    sb.append(String.format(Config.SUCCESS_MESSAGE_TOWNS_DISTRICTS_RACERS,
                            "District", districtJSONImportDTO.getName()));
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
}
