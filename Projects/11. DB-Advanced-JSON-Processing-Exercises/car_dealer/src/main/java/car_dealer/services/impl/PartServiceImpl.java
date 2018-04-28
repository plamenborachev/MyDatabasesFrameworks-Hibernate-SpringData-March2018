package car_dealer.services.impl;

import car_dealer.models.entity.Part;
import car_dealer.repositories.PartRepository;
import car_dealer.services.api.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;

    @Autowired
    public PartServiceImpl(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public void save(Part part) {
        this.partRepository.saveAndFlush(part);
    }

    @Override
    public List<Part> getAll() {
        return this.partRepository.findAll();
    }
}
