package org.softuni.mostwanted.domain.dto.view.json;

import com.google.gson.annotations.Expose;

import java.util.List;

public class RacerJsonExportDto {

    @Expose
    private String name;

    @Expose
    private List<RacerCarJsonExportDto> cars;

    public RacerJsonExportDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RacerCarJsonExportDto> getCars() {
        return this.cars;
    }

    public void setCars(List<RacerCarJsonExportDto> cars) {
        this.cars = cars;
    }
}
