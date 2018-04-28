package app.terminal;

import app.domain.dto.*;
import app.domain.model.Address;
import app.domain.model.Person;
import app.domain.validation.DTOValidator;
import app.serialize.JsonParser;
import app.serialize.Serializer;
import app.serialize.XmlParser;
import app.service.AddressesService;
import app.service.PersonService;
import app.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;

@Component
public class Terminal implements CommandLineRunner {

    private final PersonService personService;
    private final AddressesService addressesService;
    private final Serializer jsonSerializer;
    private final Serializer xmlSerializer;
    private final DTOValidator dtoValidator;

    @Autowired
    public Terminal(PersonService personService, AddressesService addressesService, DTOValidator dtoValidator) {
        this.personService = personService;
        this.addressesService = addressesService;
        this.jsonSerializer = new JsonParser();
        this.xmlSerializer = new XmlParser();
        this.dtoValidator = dtoValidator;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.importJson();
        this.importJsons();
        this.exportJson();
        this.exportJsons();

        importPersonXML();
        importPersonsXML();
        this.exportXML();
        exportAddressesXML();
    }

    private void importPersonsXML() {
        PersonsDto personsDto = this.xmlSerializer.deserialize(PersonsDto.class, "/files/input/xml/persons.xml");
        List<Person> persons = DTOConvertUtil.convert(personsDto.getPersonDtos(), Person.class);
        for (Person person : persons) {
            personService.store(person);
        }
    }

    private void importPersonXML() {
        PersonDto personDto = xmlSerializer.deserialize(PersonDto.class, "/files/input/xml/person.xml");
        Person person = DTOConvertUtil.convert(personDto, Person.class);
        personService.store(person);

    }

    private void exportAddressesXML() {
        List<Address> allAdresses = addressesService.findAllAdresses();
        List<AddressDto> addressDtos = DTOConvertUtil.convert(allAdresses, AddressDto.class);
        AddressesDto addressesDto = new AddressesDto();
        addressesDto.setAddressDtos(addressDtos);
        xmlSerializer.serialize(addressesDto, "src/main/resources/files/output/xml/addresses.xml");
    }

    private void exportXML() {
        Person person = this.personService.findById(1);
        PersonDto personDto = DTOConvertUtil.convert(person, PersonDto.class);
        this.xmlSerializer.serialize(personDto, "src/main/resources/files/output/xml/person.xml");
    }

    private void exportJson() {
        Person person = this.personService.findById(1);
        PersonDto personDto = DTOConvertUtil.convert(person, PersonDto.class);

        long startTime = System.currentTimeMillis();
        this.jsonSerializer.serialize(personDto, "src/main/resources/files/output/json/person.json");
        long endTime = System.currentTimeMillis();
        double time = (endTime - startTime);
        System.out.println("JSON " + time);

    }

    private void exportJsons() {
        List<Person> persons = this.personService.findByCountry("Bulgaria");
        List<PersonDto> personDto = DTOConvertUtil.convert(persons, PersonDto.class);
        this.jsonSerializer.serialize(personDto, "src/main/resources/files/output/json/persons.json");
    }

    private void importJson() {
        PersonDto personDto = this.jsonSerializer.deserialize(PersonDto.class, "/files/input/json/person.json");

        if (dtoValidator.isValid(personDto)) {
            Person person = DTOConvertUtil.convert(personDto, Person.class);
            personService.store(person);
        }
    }

    private void importJsons() {
        PersonDto[] personDtos = this.jsonSerializer.deserialize(PersonDto[].class, "/files/input/json/persons.json");

        for (PersonDto personDto : personDtos) {
            Person person = DTOConvertUtil.convert(personDto, Person.class);
            personService.store(person);
        }
    }
}
