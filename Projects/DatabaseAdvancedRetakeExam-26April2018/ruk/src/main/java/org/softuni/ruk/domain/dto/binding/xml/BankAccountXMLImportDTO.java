package org.softuni.ruk.domain.dto.binding.xml;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "bank-account")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountXMLImportDTO {

    @XmlAttribute
    private String client;

    @XmlElement(name = "account-number")
    private String accountNumber;

    @XmlElement
    private BigDecimal balance;

    public BankAccountXMLImportDTO() {
    }

    public String getClient() {
        return this.client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
