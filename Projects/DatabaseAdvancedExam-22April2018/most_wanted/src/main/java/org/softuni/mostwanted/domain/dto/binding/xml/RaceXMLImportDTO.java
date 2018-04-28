package org.softuni.mostwanted.domain.dto.binding.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceXMLImportDTO {

    @XmlElement
    private int laps;

    @XmlElement(name = "district-name")
    private String districtName;

    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private List<RaceRaceEntryXMLImportDTO> entries;

    public RaceXMLImportDTO() {
    }

    public int getLaps() {
        return this.laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public String getDistrictName() {
        return this.districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<RaceRaceEntryXMLImportDTO> getEntries() {
        return this.entries;
    }

    public void setEntries(List<RaceRaceEntryXMLImportDTO> entries) {
        this.entries = entries;
    }
}
