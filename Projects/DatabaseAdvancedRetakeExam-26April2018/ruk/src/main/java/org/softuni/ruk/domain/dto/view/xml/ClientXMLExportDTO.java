package org.softuni.ruk.domain.dto.view.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "family-guy")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClientXMLExportDTO {

    @XmlAttribute(name = "full-name")
    private String fullName;

    @XmlAttribute
    private Integer age;

    @XmlAttribute(name = "bank-account")
    private String bankAccount;

    @XmlElementWrapper(name = "cards")
    @XmlElement(name = "card")
    private List<ClientCardsXMLExportDTO> cards;

    public ClientXMLExportDTO() {
    }

    public String getBankAccount() {
        return this.bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public List<ClientCardsXMLExportDTO> getCards() {
        return this.cards;
    }

    public void setCards(List<ClientCardsXMLExportDTO> cards) {
        this.cards = cards;
    }
}
