package org.softuni.ruk.services.impl;

import org.softuni.ruk.domain.dto.binding.xml.CardXMLImportDTO;
import org.softuni.ruk.domain.entities.BankAccount;
import org.softuni.ruk.domain.entities.Card;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.repositories.CardRepository;
import org.softuni.ruk.services.api.BankAccountService;
import org.softuni.ruk.services.api.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(noRollbackFor = IllegalArgumentException.class)
public class CardServiceImpl implements CardService {

    private final ModelParser modelParser;
    private final CardRepository cardRepository;
    private final BankAccountService bankAccountService;

    @Autowired
    public CardServiceImpl(ModelParser modelParser, CardRepository cardRepository, BankAccountService bankAccountService) {
        this.modelParser = modelParser;
        this.cardRepository = cardRepository;
        this.bankAccountService = bankAccountService;
    }

    @Override
    public void create(CardXMLImportDTO dto) {
        Card card = this.modelParser.convert(dto, Card.class);
        card.setCardNumber(dto.getCardNumber());
        BankAccount bankAccount = this.bankAccountService.getByAccountNumber(dto.getAccountNumber());
        if (bankAccount == null){
            throw new IllegalArgumentException();
        }
        card.setBankAccount(bankAccount);
        this.cardRepository.saveAndFlush(card);
    }
}
