package app.retake.controllers;

import app.retake.Config;
import app.retake.domain.dto.view.ProcedureWrapperXMLExportDTO;
import app.retake.domain.dto.binding.xml.ProcedureWrapperXMLImportDTO;
import app.retake.domain.dto.binding.xml.ProcedureXMLImportDTO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class ProcedureController {

    private final ProcedureService procedureService;
    private final Parser parser;

    @Autowired
    public ProcedureController(ProcedureService procedureService, @Qualifier("XMLParser") Parser parser) {
        this.procedureService = procedureService;
        this.parser = parser;
    }

    public String importDataFromXML(String xmlContent) {
        ProcedureWrapperXMLImportDTO procedureWrapperXMLImportDTO = null;
        try {
            procedureWrapperXMLImportDTO = this.parser.read(ProcedureWrapperXMLImportDTO.class, xmlContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        List<ProcedureXMLImportDTO> procedureXMLImportDTOS = procedureWrapperXMLImportDTO.getProcedures();

        StringBuilder sb = new StringBuilder();

        for (ProcedureXMLImportDTO procedureXMLImportDTO : procedureXMLImportDTOS) {
            try {
                this.procedureService.create(procedureXMLImportDTO);
                sb.append("Record successfully imported.");
                sb.append(System.lineSeparator());

            } catch (Exception e) {
//                e.printStackTrace();
                sb.append(Config.ERROR_MESSAGE);
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public String exportProcedures() throws IOException, JAXBException {
        ProcedureWrapperXMLExportDTO procedureWrapperXMLExportDTO = this.procedureService.exportProcedures();
        return this.parser.write(procedureWrapperXMLExportDTO);
    }
}
