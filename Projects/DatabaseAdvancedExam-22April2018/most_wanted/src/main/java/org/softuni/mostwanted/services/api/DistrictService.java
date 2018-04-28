package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.domain.dto.binding.json.DistrictJSONImportDTO;
import org.softuni.mostwanted.domain.models.District;

import java.util.List;

public interface DistrictService {

    void create(DistrictJSONImportDTO dto);

    List<District> getByName(String name);
}
