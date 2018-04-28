package app.retake.services.impl;


import app.retake.domain.dto.binding.json.AnimalAidJSONImportDTO;
import app.retake.domain.models.AnimalAid;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalAidRepository;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;


@Service
@Transactional
public class AnimalAidServiceImpl implements AnimalAidService {

    private final ModelParser modelParser;
    private final AnimalAidRepository animalAidRepository;

    @Autowired
    public AnimalAidServiceImpl(ModelParser modelParser, AnimalAidRepository animalAidRepository) {
        this.modelParser = modelParser;
        this.animalAidRepository = animalAidRepository;
    }

    @Override
    public void create(AnimalAidJSONImportDTO dto) {
        AnimalAid animalAid = this.animalAidRepository.findByName(dto.getName());
        if (animalAid != null) {
            if (Objects.equals(animalAid.getPrice(), dto.getPrice())) {
                return;
            } else {
                animalAid.setPrice(dto.getPrice());
            }
        } else {
            animalAid = this.modelParser.convert(dto, AnimalAid.class);
        }
        this.animalAidRepository.save(animalAid);
    }
}
