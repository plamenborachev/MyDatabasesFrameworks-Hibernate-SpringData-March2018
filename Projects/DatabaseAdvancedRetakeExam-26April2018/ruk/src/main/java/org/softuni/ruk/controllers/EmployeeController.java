package org.softuni.ruk.controllers;

import org.softuni.ruk.Config;
import org.softuni.ruk.domain.dto.binding.json.BranchJSONImportDTO;
import org.softuni.ruk.domain.dto.binding.json.EmployeeJSONImportDTO;
import org.softuni.ruk.domain.dto.view.json.EmployeeJSONExportDTO;
import org.softuni.ruk.parser.ValidationUtil;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.services.api.EmployeeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    private final Parser parser;

    public EmployeeController(EmployeeService employeeService, @Qualifier("JSONParser") Parser parser) {
        this.employeeService = employeeService;
        this.parser = parser;
    }

    public String importDataFromJSON(String jsonContent) {
        EmployeeJSONImportDTO[] employeeJSONImportDTOS = null;
        try {
            employeeJSONImportDTOS = this.parser.read(EmployeeJSONImportDTO[].class, jsonContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        for (EmployeeJSONImportDTO employeeJSONImportDTO : employeeJSONImportDTOS) {
            try{
                if (ValidationUtil.isValid(employeeJSONImportDTO)){
                    this.employeeService.create(employeeJSONImportDTO);
                    sb.append(String.format(Config.SUCCESS_MESSAGE,
                            "Employee", employeeJSONImportDTO.getFullName()));
                    sb.append(System.lineSeparator());
                } else {
                    sb.append(Config.ERROR_MESSAGE);
                    sb.append(System.lineSeparator());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public String exportDataToJson() {
        List<EmployeeJSONExportDTO> allEmployeesWithClients = this.employeeService.getAllEmployeesWithClients();
        String write = null;
        try {
            write = this.parser.write(allEmployeesWithClients);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return write;
    }
}
