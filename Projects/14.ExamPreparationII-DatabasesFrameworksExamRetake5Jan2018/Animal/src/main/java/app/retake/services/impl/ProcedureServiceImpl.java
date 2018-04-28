package app.retake.services.impl;

import app.retake.domain.dto.binding.xml.ProcedureAnimalAidXMLImportDTO;
import app.retake.domain.dto.binding.xml.ProcedureXMLImportDTO;
import app.retake.domain.dto.view.ProcedureAnimalAidXMLExportDTO;
import app.retake.domain.dto.view.ProcedureWrapperXMLExportDTO;
import app.retake.domain.dto.view.ProcedureXMLExportDTO;
import app.retake.domain.models.*;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.*;
import app.retake.services.api.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProcedureServiceImpl implements ProcedureService {

    private final ModelParser modelParser;
    private final ProcedureRepository procedureRepository;
    private final VetRepository vetRepository;
    private final AnimalRepository animalRepository;
    private final AnimalAidRepository animalAidRepository;

    @Autowired
    public ProcedureServiceImpl(ProcedureRepository procedureRepository, ModelParser modelParser, VetRepository
            vetRepository, AnimalRepository animalRepository, AnimalAidRepository animalAidRepository) {
        this.procedureRepository = procedureRepository;
        this.modelParser = modelParser;
        this.vetRepository = vetRepository;
        this.animalRepository = animalRepository;
        this.animalAidRepository = animalAidRepository;
    }

    @Override
    public void create(ProcedureXMLImportDTO dto) throws ParseException {
        Vet vet = this.vetRepository.findByName(dto.getVet());
        Animal animal = this.animalRepository.findByPassportSerialNumber(dto.getAnimal());
        List<ProcedureAnimalAidXMLImportDTO> procedureAnimalAidXMLImportDTOS = dto.getAnimalAids();
        Set<AnimalAid> animalAids = new HashSet<>();
        for (ProcedureAnimalAidXMLImportDTO procedureAnimalAidXMLImportDTO : procedureAnimalAidXMLImportDTOS) {
            AnimalAid aid = this.animalAidRepository
                    .findByName(procedureAnimalAidXMLImportDTO.getName());
            animalAids.add(aid);
        }
        if (vet == null || animal == null || procedureAnimalAidXMLImportDTOS.size() != animalAids.size()) {
            throw new IllegalArgumentException();
        }
        Procedure procedure = new Procedure();
        procedure.setVet(vet);
        procedure.setAnimal(animal);
        procedure.setAnimalAids(animalAids);
        procedure.setDate(dto.getDate());
        this.procedureRepository.save(procedure);
    }

    @Override
    public ProcedureWrapperXMLExportDTO exportProcedures() {

        List<Procedure> procedures = this.procedureRepository.findAll();
        List<ProcedureXMLExportDTO> export = procedures.stream()
                .map(p ->{
                    Passport passport = p.getAnimal().getPassport();

                    List<ProcedureAnimalAidXMLExportDTO> animalAids = p.getAnimalAids()
                            .stream()
                            .map(ai -> new ProcedureAnimalAidXMLExportDTO(ai.getName(), ai.getPrice()))
                            .collect(Collectors.toList());

                    return new ProcedureXMLExportDTO(
                            passport.getSerialNumber(),
                            passport.getOwnerPhoneNumber(),
                            animalAids);
                })
                .collect(Collectors.toList());
        return new ProcedureWrapperXMLExportDTO(export);

//        PropertyMap propertyMap = new PropertyMap<Procedure, ProcedureXMLExportDTO>() {
//            @Override
//            protected void configure() {
//                map().setAnimalPassport(source.getAnimal().getPassport().getSerialNumber());
//                map().setOwner(source.getAnimal().getPassport().getOwnerPhoneNumber());
//                map().setAnimalAids(source.getAnimalAids().stream()
//                        .map(aa -> new ProcedureAnimalAidXMLExportDTO(aa.getName(), aa.getPrice()))
//                        .collect(Collectors.toList()));
//            }
//        };

//        List<Procedure> procedures = this.procedureRepository.findAll();
//
//        List<ProcedureXMLExportDTO> procedureXMLExportDTOS = new LinkedList<>();
//
//        for (Procedure procedure : procedures) {
//            List<ProcedureAnimalAidXMLExportDTO> animalAids = new ArrayList<>();
//            for (AnimalAid animalAid : procedure.getAnimalAids()) {
//                ProcedureAnimalAidXMLExportDTO dto = this.modelParser.convert(animalAid,
//                        ProcedureAnimalAidXMLExportDTO.class);
//                animalAids.add(dto);
//            }
//            ProcedureXMLExportDTO procedureXMLExportDTO = new ProcedureXMLExportDTO(procedure.getAnimal().getPassport().getSerialNumber(), procedure.getAnimal().getPassport().getOwnerPhoneNumber(), animalAids);
//            procedureXMLExportDTOS.add(procedureXMLExportDTO);
//        }
//
//        ProcedureWrapperXMLExportDTO procedureWrapperXMLExportDTO
//                = new ProcedureWrapperXMLExportDTO();
//
//        procedureWrapperXMLExportDTO.setProcedures(procedureXMLExportDTOS);
//
//        return procedureWrapperXMLExportDTO;
    }
}

