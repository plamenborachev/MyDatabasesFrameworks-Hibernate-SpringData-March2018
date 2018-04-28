package app.retake.domain.dto.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "procedures")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureWrapperXMLExportDTO {

    @XmlElement(name = "procedure")
    private List<ProcedureXMLExportDTO> procedures;

    public ProcedureWrapperXMLExportDTO() {
        this.procedures = new ArrayList<>();
    }

    public ProcedureWrapperXMLExportDTO(List<ProcedureXMLExportDTO> procedures) {
        this.procedures = procedures;
    }

    public List<ProcedureXMLExportDTO> getProcedures() {
        return this.procedures;
    }

    public void setProcedures(List<ProcedureXMLExportDTO> procedures) {
        this.procedures = procedures;
    }
}
