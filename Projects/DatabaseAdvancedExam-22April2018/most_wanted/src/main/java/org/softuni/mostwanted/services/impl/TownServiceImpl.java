package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.domain.dto.binding.json.TownJSONImportDTO;
import org.softuni.mostwanted.domain.dto.view.json.TownJsonExportDTO;
import org.softuni.mostwanted.domain.models.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class TownServiceImpl implements TownService {

    private final ModelParser modelParser;
    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(ModelParser modelParser, TownRepository townRepository) {
        this.modelParser = modelParser;
        this.townRepository = townRepository;
    }

    @Override
    public void create(TownJSONImportDTO dto) {
        Town town = this.modelParser.convert(dto, Town.class);
        this.townRepository.save(town);
    }

    @Override
    public Town getByName(String name) {
        return this.townRepository.findByName(name);
    }

    @Override
    public List<TownJsonExportDTO> getRacingTowns() {
        String[] townsString = this.townRepository.findAllByRacers();
        List<TownJsonExportDTO> townJsonExportDTOS = new LinkedList<>();
        for (String s : townsString) {
            String[] townTokens = s.split(",");
            TownJsonExportDTO dto = new TownJsonExportDTO();
            dto.setName(townTokens[0]);
            dto.setRacers(Integer.parseInt(townTokens[1]));
            townJsonExportDTOS.add(dto);
        }
        return townJsonExportDTOS;
    }
}
