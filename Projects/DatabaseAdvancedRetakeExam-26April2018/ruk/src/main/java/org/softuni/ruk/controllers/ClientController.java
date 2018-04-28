package org.softuni.ruk.controllers;

import org.softuni.ruk.Config;
import org.softuni.ruk.domain.dto.binding.json.ClientJSONImportDTO;
import org.softuni.ruk.parser.ValidationUtil;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.services.api.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class ClientController {

    private final Parser parser;
    private final ClientService clientService;

    @Autowired
    public ClientController(@Qualifier("JSONParser") Parser parser, ClientService clientService) {
        this.parser = parser;
        this.clientService = clientService;
    }

    public String importDataFromJSON(String jsonContent) {
        ClientJSONImportDTO[] clientJSONImportDTOS = null;
        try {
            clientJSONImportDTOS = this.parser.read(ClientJSONImportDTO[].class, jsonContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        for (ClientJSONImportDTO clientJSONImportDTO : clientJSONImportDTOS) {
            try{
                if (ValidationUtil.isValid(clientJSONImportDTO)){
                    this.clientService.create(clientJSONImportDTO);
                    sb.append(String.format(Config.SUCCESS_MESSAGE,
                            "Client", String.format("%s %s",
                                    clientJSONImportDTO.getFirstName(),
                                    clientJSONImportDTO.getLastName())));
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

    public String exportDataToXml() {
        return null;
    }
}
