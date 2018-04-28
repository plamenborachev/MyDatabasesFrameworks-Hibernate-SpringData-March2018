package app.domain.dto.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonJsonDto {

    @Expose
    private long id;

    @XmlElement
    @Expose
    private String firstName;

    @XmlElement
    @Expose
    private String lastName;

    @XmlElement
    @Expose
    @SerializedName(value = "addressImportDto")
    private AddressJsonDto address;

    @XmlElementWrapper
    @XmlElement
    @Expose
    @SerializedName(value = "phoneJsonDtos")
    private Set<PhoneJsonDto> phoneNumbers;

    public PersonJsonDto() {
        this.setPhoneNumbers(new HashSet<>());
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AddressJsonDto getAddress() {
        return this.address;
    }

    public void setAddress(AddressJsonDto address) {
        this.address = address;
    }

    public Set<PhoneJsonDto> getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public void setPhoneNumbers(Set<PhoneJsonDto> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
