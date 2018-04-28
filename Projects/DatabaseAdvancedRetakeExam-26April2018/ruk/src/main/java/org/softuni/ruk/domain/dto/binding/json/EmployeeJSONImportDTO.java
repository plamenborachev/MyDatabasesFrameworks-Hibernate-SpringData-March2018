package org.softuni.ruk.domain.dto.binding.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class EmployeeJSONImportDTO {

    @Expose
    @SerializedName("full_name")
    private String fullName;

    @Expose
    private BigDecimal salary;

    @Expose
    @SerializedName("started_on")
    private Date startedOn;

    @Expose
    @SerializedName("branch_name")
    private String branchName;

    public EmployeeJSONImportDTO() {
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

    public String getBranchName() {
        return this.branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
