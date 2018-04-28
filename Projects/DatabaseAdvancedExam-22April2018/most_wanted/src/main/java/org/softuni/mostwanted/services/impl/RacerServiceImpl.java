package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.domain.dto.binding.json.RacerJSONImportDTO;
import org.softuni.mostwanted.domain.models.Racer;
import org.softuni.mostwanted.domain.models.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.services.api.RacerService;
import org.softuni.mostwanted.services.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RacerServiceImpl implements RacerService {

    private final ModelParser modelParser;
    private final RacerRepository racerRepository;
    private final TownService townService;

    @Autowired
    public RacerServiceImpl(ModelParser modelParser, RacerRepository racerRepository, TownService townService) {
        this.modelParser = modelParser;
        this.racerRepository = racerRepository;
        this.townService = townService;
    }

    @Override
    public void create(RacerJSONImportDTO dto) {
        Racer racer = this.modelParser.convert(dto, Racer.class);
        Town town = this.townService.getByName(dto.getHomeTown());
        racer.setHomeTown(town);
        this.racerRepository.save(racer);
    }

    @Override
    public Racer getByName(String name) {
        return this.racerRepository.findByName(name);
    }

    @Override
    public List<Racer> getRacersWithCars() {
        List<Racer> racers = this.racerRepository
                .findAllByCarsNotNullOrderByCarsDescNameAsc();


        return null;
    }
}
