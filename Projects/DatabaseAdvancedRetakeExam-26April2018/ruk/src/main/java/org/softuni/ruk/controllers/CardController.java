package org.softuni.ruk.controllers;

import org.softuni.ruk.Config;
import org.softuni.ruk.domain.dto.binding.xml.BankAccountXMLImportDTO;
import org.softuni.ruk.domain.dto.binding.xml.BankAccountsWrapperXMLImportDTO;
import org.softuni.ruk.domain.dto.binding.xml.CardXMLImportDTO;
import org.softuni.ruk.domain.dto.binding.xml.CardsWrapperXMLImportDTO;
import org.softuni.ruk.parser.ValidationUtil;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.services.api.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class CardController {

    private final Parser parser;
    private final CardService cardService;

    @Autowired
    public CardController(@Qualifier("XMLParser") Parser parser, CardService cardService) {
        this.parser = parser;
        this.cardService = cardService;
    }

    public String importDataFromXML(String xmlContent) {
        CardsWrapperXMLImportDTO cardsWrapperXMLImportDTO = null;
        try {
            cardsWrapperXMLImportDTO = this.parser.read(CardsWrapperXMLImportDTO.class, xmlContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        List<CardXMLImportDTO> cardXMLImportDTOS = cardsWrapperXMLImportDTO.getCards();

        for (CardXMLImportDTO cardXMLImportDTO : cardXMLImportDTOS) {
            try{
                boolean hasInvalidData = false;
                if (ValidationUtil.isValid(cardXMLImportDTO)){
                    try{
                        this.cardService.create(cardXMLImportDTO);
                    } catch (IllegalArgumentException iae){
                        hasInvalidData = true;
                    }
                    if (hasInvalidData) {
                        sb.append(Config.ERROR_MESSAGE).append(System.lineSeparator());
                    } else {
                        sb.append(String.format(Config.SUCCESS_MESSAGE,
                                "Card", cardXMLImportDTO.getCardNumber()))
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
