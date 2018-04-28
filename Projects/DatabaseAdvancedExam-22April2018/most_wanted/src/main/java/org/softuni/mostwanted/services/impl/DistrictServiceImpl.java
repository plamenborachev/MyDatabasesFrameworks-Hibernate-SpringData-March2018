package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.domain.dto.binding.json.DistrictJSONImportDTO;
import org.softuni.mostwanted.domain.models.District;
import org.softuni.mostwanted.domain.models.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.DistrictRepository;
import org.softuni.mostwanted.services.api.DistrictService;
import org.softuni.mostwanted.services.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {

    private final ModelParser modelParser;
    private final DistrictRepository districtRepository;
    private final TownService townService;

    @Autowired
    public DistrictServiceImpl(ModelParser modelParser, DistrictRepository districtRepository, TownService townService) {
        this.modelParser = modelParser;
        this.districtRepository = districtRepository;
        this.townService = townService;
    }

    @Override
    public void create(DistrictJSONImportDTO dto) {
        District district = this.modelParser.convert(dto, District.class);
        Town town = this.townService.getByName(dto.getTownName());
        district.setTown(town);
        this.districtRepository.save(district);
    }

    @Override
    public List<District> getByName(String name) {
        return this.districtRepository.findByName(name);
    }


}
