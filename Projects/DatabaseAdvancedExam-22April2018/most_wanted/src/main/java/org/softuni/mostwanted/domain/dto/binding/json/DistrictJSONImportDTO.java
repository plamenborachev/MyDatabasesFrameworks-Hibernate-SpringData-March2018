package org.softuni.mostwanted.domain.dto.binding.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class DistrictJSONImportDTO implements Serializable{

    @Expose
    private String name;

    @Expose
    private String townName;

    public DistrictJSONImportDTO() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTownName() {
        return this.townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
