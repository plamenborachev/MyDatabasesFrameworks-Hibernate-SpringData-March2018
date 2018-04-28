package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.domain.dto.binding.json.CarJSONImportDTO;
import org.softuni.mostwanted.domain.models.Car;
import org.softuni.mostwanted.domain.models.Racer;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.services.api.CarService;
import org.softuni.mostwanted.services.api.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelParser modelParser;
    private final RacerService racerService;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelParser modelParser, RacerService racerService) {
        this.carRepository = carRepository;
        this.modelParser = modelParser;
        this.racerService = racerService;
    }

    @Override
    public void create(CarJSONImportDTO dto) {
        Car car = this.modelParser.convert(dto, Car.class);
        Racer racer = this.racerService.getByName(dto.getRacerName());
        car.setRacer(racer);
        this.carRepository.save(car);
    }

    @Override
    public Car getById(Integer id) {
        return this.carRepository.findById(id);
    }
}
