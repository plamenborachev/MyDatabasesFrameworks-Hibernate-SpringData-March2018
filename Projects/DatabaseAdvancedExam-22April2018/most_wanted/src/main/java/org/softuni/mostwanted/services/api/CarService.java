package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.domain.dto.binding.json.CarJSONImportDTO;
import org.softuni.mostwanted.domain.models.Car;

public interface CarService {

    void create(CarJSONImportDTO dto);

    Car getById(Integer id);

}
