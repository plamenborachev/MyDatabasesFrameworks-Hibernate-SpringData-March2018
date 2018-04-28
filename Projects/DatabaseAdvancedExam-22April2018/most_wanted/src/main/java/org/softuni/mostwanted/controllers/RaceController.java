package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.Config;
import org.softuni.mostwanted.domain.dto.binding.xml.RaceWrapperXMLImportDTO;
import org.softuni.mostwanted.domain.dto.binding.xml.RaceXMLImportDTO;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class RaceController {

    private final Parser parser;
    private final RaceService raceService;

    @Autowired
    public RaceController(@Qualifier("XMLParser") Parser parser, RaceService raceService) {
        this.parser = parser;
        this.raceService = raceService;
    }

    public String importDataFromXML(String xmlContent) {
        RaceWrapperXMLImportDTO raceWrapperXMLImportDTO = null;
        try {
            raceWrapperXMLImportDTO = this.parser.read(RaceWrapperXMLImportDTO.class, xmlContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        List<RaceXMLImportDTO> raceXMLImportDTOS = raceWrapperXMLImportDTO.getRaces();
        StringBuilder sb = new StringBuilder();
        for (RaceXMLImportDTO raceXMLImportDTO : raceXMLImportDTOS) {
            try {
                this.raceService.create(raceXMLImportDTO);
                sb.append(String.format(Config.SUCCESS_MESSAGE_RACE_RACE_ENTRY,
                        "Race", this.raceService.getLastId()));
                sb.append(System.lineSeparator());
            } catch (Exception e) {
//                e.printStackTrace();
                sb.append(Config.ERROR_MESSAGE);
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
