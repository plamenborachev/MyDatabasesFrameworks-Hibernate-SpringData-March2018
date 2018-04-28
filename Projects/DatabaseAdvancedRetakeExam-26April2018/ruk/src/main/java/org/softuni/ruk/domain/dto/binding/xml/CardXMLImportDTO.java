package org.softuni.ruk.domain.dto.binding.xml;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "card")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardXMLImportDTO {

    @XmlAttribute
    private String status;

    @XmlAttribute(name = "account-number")
    private String accountNumber;

    @XmlElement(name = "card-number")
    private String cardNumber;

    public CardXMLImportDTO() {
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


}
