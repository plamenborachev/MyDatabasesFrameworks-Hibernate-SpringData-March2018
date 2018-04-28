package car_dealer.services.api;

import car_dealer.models.dto.binding.SupplierInputJsonDto;
import car_dealer.models.entity.Supplier;

import java.util.List;

public interface SupplierService {

    void save(SupplierInputJsonDto supplierInputJsonDto);

    List<Supplier> getAll();

}
