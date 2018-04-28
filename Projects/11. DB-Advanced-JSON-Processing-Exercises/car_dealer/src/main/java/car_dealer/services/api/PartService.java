package car_dealer.services.api;

import car_dealer.models.entity.Part;

import java.util.List;

public interface PartService {

    void save(Part part);

    List<Part> getAll();

}
