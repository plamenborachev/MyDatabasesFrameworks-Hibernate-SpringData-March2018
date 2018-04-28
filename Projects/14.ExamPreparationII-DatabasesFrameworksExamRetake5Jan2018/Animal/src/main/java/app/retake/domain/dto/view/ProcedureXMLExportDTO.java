package app.retake.domain.dto.view;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "procedure")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureXMLExportDTO {

    @XmlAttribute(name = "animal-passport")
    private String animalPassport;

    @XmlElement
    private String owner;

    @XmlElementWrapper(name = "animal-aids")
    @XmlElement(name = "animal-aid")
    private List<ProcedureAnimalAidXMLExportDTO> animalAids;

    public ProcedureXMLExportDTO() {
        this.animalAids = new ArrayList<>();
    }

    public ProcedureXMLExportDTO(String animalPassport, String owner, List<ProcedureAnimalAidXMLExportDTO> animalAids) {
        this.animalPassport = animalPassport;
        this.owner = owner;
        this.animalAids = animalAids;
    }

    public String getAnimalPassport() {
        return this.animalPassport;
    }

    public void setAnimalPassport(String animalPassport) {
        this.animalPassport = animalPassport;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<ProcedureAnimalAidXMLExportDTO> getAnimalAids() {
        return this.animalAids;
    }

    public void setAnimalAids(List<ProcedureAnimalAidXMLExportDTO> animalAids) {
        this.animalAids = animalAids;
    }
}
