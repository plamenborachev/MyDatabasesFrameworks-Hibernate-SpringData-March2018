package org.softuni.mostwanted.domain.dto.binding.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "race-entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryXMLImportDTO {

    @XmlAttribute(name = "has-finished")
    private boolean hasFinished;

    @XmlAttribute(name = "finish-time")
    private double finishTime;

    @XmlAttribute(name = "car-id")
    private Integer carId;

    @XmlElement
    private String racer;

    public RaceEntryXMLImportDTO() {
    }

    public boolean isHasFinished() {
        return this.hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public double getFinishTime() {
        return this.finishTime;
    }

    public void setFinishTime(double finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getCarId() {
        return this.carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getRacer() {
        return this.racer;
    }

    public void setRacer(String racer) {
        this.racer = racer;
    }
}
