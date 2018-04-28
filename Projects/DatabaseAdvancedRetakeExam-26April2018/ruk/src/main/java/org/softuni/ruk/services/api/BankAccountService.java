package org.softuni.ruk.services.api;

import org.softuni.ruk.domain.dto.binding.xml.BankAccountXMLImportDTO;
import org.softuni.ruk.domain.entities.BankAccount;

import java.util.List;

public interface BankAccountService {

    void create(BankAccountXMLImportDTO dto);

    BankAccount getByAccountNumber(String accountNumber);
}
