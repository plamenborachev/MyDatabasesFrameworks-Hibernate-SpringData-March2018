package org.softuni.mostwanted.domain.dto.binding.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryWrapperXMLImportDTO {

    @XmlElement(name = "race-entry")
    private List<RaceEntryXMLImportDTO> raceEntries;

    public RaceEntryWrapperXMLImportDTO() {
    }

    public List<RaceEntryXMLImportDTO> getRaceEntries() {
        return this.raceEntries;
    }

    public void setRaceEntries(List<RaceEntryXMLImportDTO> raceEntries) {
        this.raceEntries = raceEntries;
    }


}
