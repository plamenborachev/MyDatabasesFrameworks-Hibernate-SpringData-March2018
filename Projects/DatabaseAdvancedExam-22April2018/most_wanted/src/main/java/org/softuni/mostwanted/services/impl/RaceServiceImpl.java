package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.domain.dto.binding.xml.RaceRaceEntryXMLImportDTO;
import org.softuni.mostwanted.domain.dto.binding.xml.RaceXMLImportDTO;
import org.softuni.mostwanted.domain.models.District;
import org.softuni.mostwanted.domain.models.Race;
import org.softuni.mostwanted.domain.models.RaceEntry;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.RaceRepository;
import org.softuni.mostwanted.services.api.DistrictService;
import org.softuni.mostwanted.services.api.RaceEntryService;
import org.softuni.mostwanted.services.api.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RaceServiceImpl implements RaceService {

    private final ModelParser modelParser;
    private final RaceRepository raceRepository;
    private final DistrictService districtService;
    private final RaceEntryService raceEntryService;

    @Autowired
    public RaceServiceImpl(ModelParser modelParser, RaceRepository raceRepository, DistrictService districtService, RaceEntryService raceEntryService) {
        this.modelParser = modelParser;
        this.raceRepository = raceRepository;
        this.districtService = districtService;
        this.raceEntryService = raceEntryService;
    }

    @Override
    public void create(RaceXMLImportDTO dto) {
        Race race = this.modelParser.convert(dto, Race.class);
        List<District> districts = this.districtService.getByName(dto.getDistrictName());
        List<RaceRaceEntryXMLImportDTO> raceRaceEntryXMLImportDTOS = dto.getEntries();
        Set<RaceEntry> raceEntries = new HashSet<>();
        for (RaceRaceEntryXMLImportDTO raceRaceEntryXMLImportDTO : raceRaceEntryXMLImportDTOS) {
            RaceEntry raceEntry = this.raceEntryService.getById(raceRaceEntryXMLImportDTO.getId());
//            raceEntry.setRace(race);
            raceEntries.add(raceEntry);
        }
        race.setDistrict(districts.get(0));
        race.setEntries(raceEntries);
        this.raceRepository.saveAndFlush(race);
    }

    @Override
    public Integer getLastId() {
        return this.raceRepository.getLastId();
    }
}
