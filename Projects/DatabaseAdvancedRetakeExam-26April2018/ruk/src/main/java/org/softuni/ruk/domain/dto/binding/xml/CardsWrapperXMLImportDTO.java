package org.softuni.ruk.domain.dto.binding.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cards")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardsWrapperXMLImportDTO {

    @XmlElement(name = "card")
    private List<CardXMLImportDTO> cards;

    public CardsWrapperXMLImportDTO() {
    }

    public List<CardXMLImportDTO> getCards() {
        return this.cards;
    }

    public void setCards(List<CardXMLImportDTO> cards) {
        this.cards = cards;
    }
}
