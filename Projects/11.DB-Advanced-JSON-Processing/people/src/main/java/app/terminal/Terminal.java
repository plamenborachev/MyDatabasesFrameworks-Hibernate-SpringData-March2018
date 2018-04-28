package app.terminal;

import app.domain.dto.DTOConvertUtil;
import app.domain.dto.json.PersonJsonDto;
import app.domain.dto.json.PhoneJsonDto;
import app.domain.model.Person;
import app.domain.model.PhoneNumber;
import app.io.JsonParser;
import app.io.XmlParser;
import app.service.PersonService;
import app.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;

@Component
public class Terminal implements CommandLineRunner {

    private static final String PERSON_INPUT_JSON = "/files/input/json/person.json";
    private static final String PERSONS_INPUT_JSON = "/files/input/json/persons.json";
    private static final String PERSON_OUTPUT_JSON = "src/main/resources/files/output/json/person.json";
    private static final String PERSONS_OUTPUT_JSON = "src/main/resources/files/output/json/persons.json";

    private final PersonService personService;

    private final PhoneService phoneService;

    private final JsonParser jsonParser;

    private final XmlParser xmlParser;

    @Autowired
    public Terminal(PersonService personService, PhoneService phoneService, JsonParser jsonParser, XmlParser
            xmlParser) {
        this.personService = personService;
        this.phoneService = phoneService;
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
    }

    @Override
    public void run(String... strings) throws Exception {

        this.importJson();
        this.importJsons();
        this.exportJson();
        this.exportJsons();

//        this.exportXML();
    }

    private void exportXML() throws JAXBException, IOException {
        Person person = this.personService.findById(1);
        PersonJsonDto personJsonDto = DTOConvertUtil.convert(person, PersonJsonDto.class);

        long startTime = System.currentTimeMillis();
        this.xmlParser.writeXML(personJsonDto, "src/main/resources/files/output/xml/person.xml");
        long endTime = System.currentTimeMillis();
        double time = (endTime - startTime);
        System.out.println("XML " + time);
    }

    private void exportJsons() throws IOException {
        List<PersonJsonDto> personsJsonDto = this.personService.findByCountry("Bulgaria");
        this.jsonParser.exportJson(personsJsonDto, PERSONS_OUTPUT_JSON);
    }

    private void exportJson() throws IOException {
        Person person = this.personService.findById(2);
        PersonJsonDto personJsonDto = DTOConvertUtil.convert(person, PersonJsonDto.class);
        this.jsonParser.exportJson(personJsonDto, PERSON_OUTPUT_JSON);
    }

    private void importJsons() throws IOException {
        PersonJsonDto[] personJsonDtos
                = this.jsonParser.importJson(PersonJsonDto[].class, PERSONS_INPUT_JSON);

        for (PersonJsonDto personJsonDto : personJsonDtos) {
            storeDto(personJsonDto);
        }
    }

    private void importJson() throws IOException {
        PersonJsonDto personJsonDto = this.jsonParser.importJson(PersonJsonDto.class, PERSON_INPUT_JSON);
        storeDto(personJsonDto);
    }

    private void storeDto(PersonJsonDto personJsonDto) {
        Person person = DTOConvertUtil.convert(personJsonDto, Person.class);

        Set<PhoneNumber> phoneNumbers = new HashSet<>();
        for (PhoneJsonDto number : personJsonDto.getPhoneNumbers()) {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setNumber(number.getNumber());
            phoneNumber.setId(number.getPersonId());
            phoneNumbers.add(phoneNumber);
            this.phoneService.save(phoneNumber);
        }
        person.setPhoneNumbers(phoneNumbers);
        personService.create(person);
    }
}
