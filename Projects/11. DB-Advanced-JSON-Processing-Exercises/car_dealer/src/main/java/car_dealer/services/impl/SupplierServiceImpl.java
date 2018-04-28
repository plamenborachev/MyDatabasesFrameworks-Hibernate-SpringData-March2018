package car_dealer.services.impl;

import car_dealer.io.DTOConvertUtil;
import car_dealer.models.dto.binding.SupplierInputJsonDto;
import car_dealer.models.entity.Supplier;
import car_dealer.repositories.SupplierRepository;
import car_dealer.services.api.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void save(SupplierInputJsonDto supplierInputJsonDto) {
        Supplier supplier = DTOConvertUtil.convert(supplierInputJsonDto, Supplier.class);
        this.supplierRepository.saveAndFlush(supplier);
    }

    @Override
    public List<Supplier> getAll() {
        return this.supplierRepository.findAll();
    }
}
