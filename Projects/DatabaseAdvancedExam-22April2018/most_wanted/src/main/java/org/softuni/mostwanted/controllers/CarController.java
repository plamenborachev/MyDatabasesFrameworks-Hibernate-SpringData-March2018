package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.Config;
import org.softuni.mostwanted.domain.dto.binding.json.CarJSONImportDTO;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class CarController {

    private final Parser parser;
    private final CarService carService;

    @Autowired
    public CarController(@Qualifier("JSONParser") Parser parser, CarService carService) {
        this.parser = parser;
        this.carService = carService;
    }

    public String importDataFromJSON(String jsonContent) {
        CarJSONImportDTO[] carJSONImportDTOS = new CarJSONImportDTO[0];
        try {
            carJSONImportDTOS = this.parser.read(CarJSONImportDTO[].class, jsonContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (CarJSONImportDTO carJSONImportDTO : carJSONImportDTOS) {
            try {
                if (ValidationUtil.isValid(carJSONImportDTO)){
                    this.carService.create(carJSONImportDTO);
                    sb.append(String.format(Config.SUCCESS_MESSAGE_CARS,
                            "Car", carJSONImportDTO.getBrand(), carJSONImportDTO.getModel(), carJSONImportDTO.getYearOfProduction()));
                    sb.append(System.lineSeparator());
                } else {
                    sb.append(Config.DUPLICATE_DATA_MESSAGE);
                    sb.append(System.lineSeparator());
                }
            } catch (Exception e) {
//                e.printStackTrace();
                sb.append(Config.ERROR_MESSAGE);
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
