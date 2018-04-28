package app.retake.services.api;

import app.retake.domain.dto.binding.json.AnimalJSONImportDTO;
import app.retake.domain.dto.view.AnimalsJSONExportDTO;

import java.text.ParseException;
import java.util.List;

public interface AnimalService {
    void create(AnimalJSONImportDTO dto) throws ParseException;
    List<AnimalsJSONExportDTO> findByOwnerPhoneNumber(String phoneNumber);
}
