package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.domain.dto.binding.json.RacerJSONImportDTO;
import org.softuni.mostwanted.domain.models.Racer;

import java.util.List;

public interface RacerService {


    void create(RacerJSONImportDTO dto);

    Racer getByName(String name);

    List<Racer> getRacersWithCars();
}
