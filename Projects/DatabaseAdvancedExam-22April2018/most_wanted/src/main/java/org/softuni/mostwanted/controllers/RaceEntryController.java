package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.Config;
import org.softuni.mostwanted.domain.dto.binding.xml.RaceEntryWrapperXMLImportDTO;
import org.softuni.mostwanted.domain.dto.binding.xml.RaceEntryXMLImportDTO;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.RaceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class RaceEntryController {

    private final RaceEntryService raceEntryService;
    private final Parser parser;

    @Autowired
    public RaceEntryController(RaceEntryService raceEntryService, @Qualifier("XMLParser") Parser parser) {
        this.raceEntryService = raceEntryService;
        this.parser = parser;
    }

    public String importDataFromXML(String xmlContent) {
        RaceEntryWrapperXMLImportDTO raceEntryWrapperXMLImportDTO = null;
        try {
            raceEntryWrapperXMLImportDTO = this.parser.read(RaceEntryWrapperXMLImportDTO.class, xmlContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        List<RaceEntryXMLImportDTO> raceEntryXMLImportDTOS = raceEntryWrapperXMLImportDTO.getRaceEntries();
        StringBuilder sb = new StringBuilder();
        for (RaceEntryXMLImportDTO raceEntryXMLImportDTO : raceEntryXMLImportDTOS) {
            try {
                if (ValidationUtil.isValid(raceEntryXMLImportDTO)){
                    this.raceEntryService.create(raceEntryXMLImportDTO);
                    sb.append(String.format(Config.SUCCESS_MESSAGE_RACE_RACE_ENTRY,
                            "RaceEntry", this.raceEntryService.getLastEnteredId()));
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
