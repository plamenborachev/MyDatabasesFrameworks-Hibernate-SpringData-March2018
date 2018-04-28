package org.softuni.ruk.domain.dto.view.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "card")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClientCardsXMLExportDTO {

    @XmlElement(name = "card-number")
    private String cardNumber;

    @XmlElement(name = "card-status")
    private String cardStatus;

    public ClientCardsXMLExportDTO() {
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardStatus() {
        return this.cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }
}
