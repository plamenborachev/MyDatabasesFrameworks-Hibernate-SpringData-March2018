package org.softuni.mostwanted.domain.dto.binding.json;

import com.google.gson.annotations.Expose;

import javax.persistence.UniqueConstraint;
import java.io.Serializable;

public class TownJSONImportDTO implements Serializable{

    @Expose
    private String name;

    public TownJSONImportDTO() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
