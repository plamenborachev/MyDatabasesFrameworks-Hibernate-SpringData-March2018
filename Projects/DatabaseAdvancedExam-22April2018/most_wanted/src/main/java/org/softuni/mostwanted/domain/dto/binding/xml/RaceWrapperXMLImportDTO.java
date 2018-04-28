package org.softuni.mostwanted.domain.dto.binding.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "races")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceWrapperXMLImportDTO {

    @XmlElement(name = "race")
    private List<RaceXMLImportDTO> races;

    public RaceWrapperXMLImportDTO() {
    }

    public List<RaceXMLImportDTO> getRaces() {
        return this.races;
    }

    public void setRaces(List<RaceXMLImportDTO> races) {
        this.races = races;
    }
}
