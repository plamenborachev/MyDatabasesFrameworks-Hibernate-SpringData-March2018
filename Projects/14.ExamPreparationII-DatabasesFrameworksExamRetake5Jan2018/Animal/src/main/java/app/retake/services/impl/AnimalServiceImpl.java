package app.retake.services.impl;

import app.retake.domain.dto.binding.json.AnimalJSONImportDTO;
import app.retake.domain.dto.view.AnimalsJSONExportDTO;
import app.retake.domain.models.Animal;
import app.retake.domain.models.Passport;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalRepository;
import app.retake.repositories.PassportRepository;
import app.retake.services.api.AnimalService;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {

    private final ModelParser modelParser;
    private final AnimalRepository animalRepository;
    private final PassportRepository passportRepository;

    @Autowired
    public AnimalServiceImpl(ModelParser modelParser, AnimalRepository animalRepository, PassportRepository
            passportRepository) {
        this.modelParser = modelParser;
        this.animalRepository = animalRepository;
        this.passportRepository = passportRepository;
    }

    @Override
    public void create(AnimalJSONImportDTO dto) throws ParseException {
        Animal animal = this.modelParser.convert(dto, Animal.class);
        if (this.passportRepository.findOne(dto.getPassport().getSerialNumber()) == null){
            Passport passport = this.modelParser.convert(dto.getPassport(), Passport.class);
            animal.setPassport(passport);
            this.animalRepository.save(animal);
        }
    }

    @Override
    public List<AnimalsJSONExportDTO> findByOwnerPhoneNumber(String phoneNumber) {
        List<Animal> animals = this.animalRepository
                .findAllByPassportOwnerPhoneNumberOrderByAgeAndPassportSerialNumber(phoneNumber);
        List<AnimalsJSONExportDTO> animalsJSONExportDTOS = new LinkedList<>();
        PropertyMap propertyMap = new PropertyMap<Animal, AnimalsJSONExportDTO>() {
            @Override
            protected void configure() {
                map().setOwnerName(source.getPassport().getOwnerName());
                map().setSerialNumber(source.getPassport().getSerialNumber());
                map().setRegisteredOn(source.getPassport().getRegistrationDate());
            }
        };
        for (Animal animal : animals) {
            AnimalsJSONExportDTO animalsJSONExportDTO = this.modelParser.convert(animal, AnimalsJSONExportDTO.class, propertyMap);
            animalsJSONExportDTOS.add(animalsJSONExportDTO);
        }
        return animalsJSONExportDTOS;
    }
}
