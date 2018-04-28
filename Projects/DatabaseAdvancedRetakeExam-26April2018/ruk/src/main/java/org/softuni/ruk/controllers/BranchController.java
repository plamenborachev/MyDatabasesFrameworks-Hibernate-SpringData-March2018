package org.softuni.ruk.controllers;

import org.softuni.ruk.Config;
import org.softuni.ruk.domain.dto.binding.json.BranchJSONImportDTO;
import org.softuni.ruk.parser.ValidationUtil;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.services.api.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class BranchController {

    private final BranchService branchService;
    private final Parser parser;

    @Autowired
    public BranchController(BranchService branchService, @Qualifier("JSONParser") Parser parser) {
        this.branchService = branchService;
        this.parser = parser;
    }

    public String importDataFromJSON(String jsonContent) {
       BranchJSONImportDTO[] branchJSONImportDTOS = null;
        try {
            branchJSONImportDTOS = this.parser.read(BranchJSONImportDTO[].class, jsonContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        for (BranchJSONImportDTO branchJSONImportDTO : branchJSONImportDTOS) {
            try{
                if (ValidationUtil.isValid(branchJSONImportDTO)){
                    this.branchService.create(branchJSONImportDTO);
                    sb.append(String.format(Config.SUCCESS_MESSAGE,
                            "Branch", branchJSONImportDTO.getName()));
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
}
