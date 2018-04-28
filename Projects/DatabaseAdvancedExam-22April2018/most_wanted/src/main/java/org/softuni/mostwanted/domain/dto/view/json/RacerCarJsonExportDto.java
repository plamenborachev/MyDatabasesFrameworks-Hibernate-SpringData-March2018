package org.softuni.mostwanted.domain.dto.view.json;

import com.google.gson.annotations.Expose;

public class RacerCarJsonExportDto {

    @Expose
    private String brand;

    @Expose
    private String model;

    @Expose
    private Integer yearOfProduction;

    public RacerCarJsonExportDto() {
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYearOfProduction() {
        return this.yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d", this.brand, this.model, this.yearOfProduction);
    }
}
