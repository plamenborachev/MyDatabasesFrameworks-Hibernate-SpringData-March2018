package org.softuni.mostwanted.domain.dto.view.json;

import com.google.gson.annotations.Expose;

public class TownJsonExportDTO {

    @Expose
    private String name;

    @Expose
    private Integer racers;

    public TownJsonExportDTO() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRacers() {
        return this.racers;
    }

    public void setRacers(Integer racers) {
        this.racers = racers;
    }
}
