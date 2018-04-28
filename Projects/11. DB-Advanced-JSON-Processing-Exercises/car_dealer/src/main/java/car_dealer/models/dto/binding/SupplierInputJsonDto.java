package car_dealer.models.dto.binding;

import com.google.gson.annotations.Expose;

public class SupplierInputJsonDto {

    @Expose
    private String name;

    @Expose
    private boolean isImporter;

    public SupplierInputJsonDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return this.isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
