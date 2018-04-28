package org.softuni.ruk.services.impl;

import org.softuni.ruk.domain.dto.binding.xml.BankAccountXMLImportDTO;
import org.softuni.ruk.domain.entities.BankAccount;
import org.softuni.ruk.domain.entities.Client;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.repositories.BankAccountRepository;
import org.softuni.ruk.services.api.BankAccountService;
import org.softuni.ruk.services.api.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(noRollbackFor = IllegalArgumentException.class)
public class BankAccountServiceImpl implements BankAccountService {

    private final ModelParser modelParser;
    private final BankAccountRepository bankAccountRepository;
    private final ClientService clientService;

    @Autowired
    public BankAccountServiceImpl(ModelParser modelParser, BankAccountRepository bankAccountRepository, ClientService
            clientService) {
        this.modelParser = modelParser;
        this.bankAccountRepository = bankAccountRepository;
        this.clientService = clientService;
    }

    @Override
    public void create(BankAccountXMLImportDTO dto) {
        BankAccount bankAccount = this.modelParser.convert(dto, BankAccount.class);
        Client client = this.clientService.getByFullName(dto.getClient());
        if (client == null){
           throw new IllegalArgumentException();
        }
        bankAccount.setClient(client);
        this.bankAccountRepository.saveAndFlush(bankAccount);
    }

    @Override
    public BankAccount getByAccountNumber(String accountNumber) {
        return this.bankAccountRepository.findByAccountNumber(accountNumber);
    }
}
