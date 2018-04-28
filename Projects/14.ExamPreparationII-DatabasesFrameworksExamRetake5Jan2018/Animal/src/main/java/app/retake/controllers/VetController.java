package app.retake.controllers;

import app.retake.Config;
import app.retake.domain.dto.binding.xml.VetWrapperXMLImportDTO;
import app.retake.domain.dto.binding.xml.VetXMLImportDTO;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class VetController {

    private final VetService vetService;
    private final Parser parser;

    @Autowired
    public VetController(VetService vetService, @Qualifier("XMLParser") Parser parser) {
        this.vetService = vetService;
        this.parser = parser;
    }

    public String importDataFromXML(String xmlContent) {
        VetWrapperXMLImportDTO vetWrapperXMLImportDTO = null;
        try {
            vetWrapperXMLImportDTO = this.parser.read(VetWrapperXMLImportDTO.class, xmlContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        List<VetXMLImportDTO> vetXMLImportDTOS = vetWrapperXMLImportDTO.getVets();

        StringBuilder sb = new StringBuilder();

        for (VetXMLImportDTO vetXMLImportDTO : vetXMLImportDTOS) {
            try {
                if (ValidationUtil.isValid(vetXMLImportDTO)){
                    this.vetService.create(vetXMLImportDTO);
                    sb.append(String.format("Record %s successfully imported.",
                            vetXMLImportDTO.getName()));
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
