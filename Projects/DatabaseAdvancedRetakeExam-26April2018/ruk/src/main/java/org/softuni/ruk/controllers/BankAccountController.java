package org.softuni.ruk.controllers;

import org.softuni.ruk.Config;
import org.softuni.ruk.domain.dto.binding.json.ClientJSONImportDTO;
import org.softuni.ruk.domain.dto.binding.xml.BankAccountXMLImportDTO;
import org.softuni.ruk.domain.dto.binding.xml.BankAccountsWrapperXMLImportDTO;
import org.softuni.ruk.parser.ValidationUtil;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.services.api.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class BankAccountController {

    private final Parser parser;
    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(@Qualifier("XMLParser") Parser parser, BankAccountService bankAccountService) {
        this.parser = parser;
        this.bankAccountService = bankAccountService;
    }


    public String importDataFromXML(String xmlContent) {
        BankAccountsWrapperXMLImportDTO bankAccountsWrapperXMLImportDTO = null;
        try {
            bankAccountsWrapperXMLImportDTO = this.parser.read(BankAccountsWrapperXMLImportDTO.class, xmlContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        List<BankAccountXMLImportDTO> bankAccountXMLImportDTOS = bankAccountsWrapperXMLImportDTO.getBankAccounts();

        for (BankAccountXMLImportDTO bankAccountXMLImportDTO : bankAccountXMLImportDTOS) {
            try{
                boolean hasInvalidData = false;
                if (ValidationUtil.isValid(bankAccountXMLImportDTO)){
                    try{
                        this.bankAccountService.create(bankAccountXMLImportDTO);
                    } catch (IllegalArgumentException iae){
                        hasInvalidData = true;
                    }
                    if (hasInvalidData) {
                        sb.append(Config.ERROR_MESSAGE).append(System.lineSeparator());
                    } else {
                        sb.append(String.format(Config.SUCCESS_MESSAGE,
                                "Bank Account", bankAccountXMLImportDTO.getAccountNumber()))
                                .append(System.lineSeparator());
                    }
                } else {
                    sb.append(Config.ERROR_MESSAGE).append(System.lineSeparator());
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
