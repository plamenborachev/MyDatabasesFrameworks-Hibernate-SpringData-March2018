package app.retake.domain.dto.binding.json;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AnimalJSONImportDTO implements Serializable {

    @Expose
    @Length(min = 3, max = 20)
    @NotNull
    private String name;

    @Expose
    @Length(min = 3, max = 20)
    private String type;

    @Expose
    @Min(1)
    private int age;

    @Expose
    @Valid
    private PassportJSONImportDTO passport;

    public AnimalJSONImportDTO() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PassportJSONImportDTO getPassport() {
        return this.passport;
    }

    public void setPassport(PassportJSONImportDTO passport) {
        this.passport = passport;
    }
}
