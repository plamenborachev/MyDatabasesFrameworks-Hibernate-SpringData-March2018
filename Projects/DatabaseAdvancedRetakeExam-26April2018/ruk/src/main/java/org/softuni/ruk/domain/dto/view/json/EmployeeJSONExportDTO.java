package org.softuni.ruk.domain.dto.view.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class EmployeeJSONExportDTO {

    @Expose
    @SerializedName("full_name")
    private String fullName;

    private BigDecimal salary;

    @Expose
    @SerializedName("started_on")
    private Date startedOn;

    @Expose
    private List<EmployeeClientsJSONExportDTO> clients;

    public EmployeeJSONExportDTO() {
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getStartedOn() {
        return this.startedOn;
    }

    public void setStartedOn(Date startedOn) {
        this.startedOn = startedOn;
    }

    public List<EmployeeClientsJSONExportDTO> getClients() {
        return this.clients;
    }

    public void setClients(List<EmployeeClientsJSONExportDTO> clients) {
        this.clients = clients;
    }
}
