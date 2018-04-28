package car_dealer.services.impl;

import car_dealer.models.entity.Car;
import car_dealer.models.entity.Part;
import car_dealer.repositories.CarRepository;
import car_dealer.repositories.PartRepository;
import car_dealer.services.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void save(Car car) {
        this.carRepository.saveAndFlush(car);
    }
}
