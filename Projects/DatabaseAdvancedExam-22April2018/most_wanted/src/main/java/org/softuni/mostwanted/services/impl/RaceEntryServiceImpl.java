package org.softuni.mostwanted.services.impl;

import org.modelmapper.PropertyMap;
import org.softuni.mostwanted.domain.dto.binding.xml.RaceEntryXMLImportDTO;
import org.softuni.mostwanted.domain.models.Car;
import org.softuni.mostwanted.domain.models.RaceEntry;
import org.softuni.mostwanted.domain.models.Racer;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.RaceEntryRepository;
import org.softuni.mostwanted.services.api.CarService;
import org.softuni.mostwanted.services.api.RaceEntryService;
import org.softuni.mostwanted.services.api.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;

@Service
@Transactional
public class RaceEntryServiceImpl implements RaceEntryService {

    private final ModelParser modelParser;
    private final RaceEntryRepository raceEntryRepository;
    private final RacerService racerService;
    private final CarService carService;

    @Autowired
    public RaceEntryServiceImpl(ModelParser modelParser, RaceEntryRepository raceEntryRepository, RacerService racerService, CarService carService) {
        this.modelParser = modelParser;
        this.raceEntryRepository = raceEntryRepository;
        this.racerService = racerService;
        this.carService = carService;
    }

    @Override
    public void create(RaceEntryXMLImportDTO dto) {
        RaceEntry raceEntry = new RaceEntry();
        raceEntry.setFinishTime(dto.getFinishTime());
        raceEntry.setHasFinished(dto.isHasFinished());
        Racer racer = this.racerService.getByName(dto.getRacer());
        raceEntry.setRacer(racer);
        Car car = this.carService.getById(dto.getCarId());
        raceEntry.setCar(car);
        this.raceEntryRepository.save(raceEntry);
    }

    @Override
    public Integer getLastEnteredId() {
        return this.raceEntryRepository.getLastId();
    }

    @Override
    public RaceEntry getById(Integer id) {
        return this.raceEntryRepository.findById(id);
    }
}
