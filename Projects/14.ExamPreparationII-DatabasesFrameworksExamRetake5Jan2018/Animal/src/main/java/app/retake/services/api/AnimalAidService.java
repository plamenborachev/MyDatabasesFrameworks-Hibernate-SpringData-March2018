package app.retake.services.api;

import app.retake.domain.dto.binding.json.AnimalAidJSONImportDTO;

public interface AnimalAidService {
    void create(AnimalAidJSONImportDTO dto);
}
