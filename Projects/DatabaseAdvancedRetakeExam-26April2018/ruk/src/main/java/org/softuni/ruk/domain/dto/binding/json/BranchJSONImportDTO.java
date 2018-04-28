package org.softuni.ruk.domain.dto.binding.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class BranchJSONImportDTO {

    @Expose
    @NotNull
    private String name;

    public BranchJSONImportDTO() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
