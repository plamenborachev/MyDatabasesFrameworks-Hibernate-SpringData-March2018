package org.softuni.ruk.domain.dto.binding.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "bank-accounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountsWrapperXMLImportDTO {

    @XmlElement(name = "bank-account")
    private List<BankAccountXMLImportDTO> bankAccounts;

    public BankAccountsWrapperXMLImportDTO() {
    }

    public List<BankAccountXMLImportDTO> getBankAccounts() {
        return this.bankAccounts;
    }

    public void setBankAccounts(List<BankAccountXMLImportDTO> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
