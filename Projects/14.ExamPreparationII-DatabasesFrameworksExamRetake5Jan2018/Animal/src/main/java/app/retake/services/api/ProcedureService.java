package app.retake.services.api;

import app.retake.domain.dto.view.ProcedureWrapperXMLExportDTO;
import app.retake.domain.dto.binding.xml.ProcedureXMLImportDTO;

import java.text.ParseException;

public interface ProcedureService {
    void create(ProcedureXMLImportDTO dto) throws ParseException;

    ProcedureWrapperXMLExportDTO exportProcedures();
}
