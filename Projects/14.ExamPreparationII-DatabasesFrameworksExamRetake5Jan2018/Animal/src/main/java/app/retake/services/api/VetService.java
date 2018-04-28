package app.retake.services.api;

import app.retake.domain.dto.binding.xml.VetXMLImportDTO;

public interface VetService {
    void create(VetXMLImportDTO dto);
}
