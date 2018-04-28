package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.domain.dto.binding.json.TownJSONImportDTO;
import org.softuni.mostwanted.domain.dto.view.json.TownJsonExportDTO;
import org.softuni.mostwanted.domain.models.Town;

import java.util.List;

public interface TownService {

    void create(TownJSONImportDTO dto);

    Town getByName(String name);

    List<TownJsonExportDTO> getRacingTowns();
}
