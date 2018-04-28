package car_dealer.terminal;

import car_dealer.io.DTOConvertUtil;
import car_dealer.io.JsonParser;
import car_dealer.models.dto.binding.CarInputJsonDto;
import car_dealer.models.dto.binding.PartInputJsonDto;
import car_dealer.models.dto.binding.SupplierInputJsonDto;
import car_dealer.models.entity.Car;
import car_dealer.models.entity.Part;
import car_dealer.models.entity.Supplier;
import car_dealer.services.api.CarService;
import car_dealer.services.api.PartService;
import car_dealer.services.api.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private static final String FILE_INPUT_SUPPLIERS_JSON = "/files/input/suppliers.json";
    private static final String FILE_INPUT_PARTS_JSON = "/files/input/parts.json";
    private static final String FILE_INPUT_CARS_JSON = "/files/input/cars.json";

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final JsonParser jsonParser;
    private Random random;

    @Autowired
    public Terminal(SupplierService supplierService, PartService partService, CarService carService, JsonParser jsonParser) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.jsonParser = jsonParser;
        this.random = new Random();
    }

    @Override
    public void run(String... strings) throws Exception {
        importSuppliers();
        importParts();
        importCars();
    }

    private void importCars() throws IOException {
        List<Part> parts = this.partService.getAll();
        CarInputJsonDto[] carInputJsonDtos
                = this.jsonParser.importJson(CarInputJsonDto[].class, FILE_INPUT_CARS_JSON);
        for (CarInputJsonDto carInputJsonDto : carInputJsonDtos) {
            Car car = DTOConvertUtil.convert(carInputJsonDto, Car.class);
            Set<Part> partSet = new HashSet<>();
            for (int i = 0; i < (10 + this.random.nextInt(11)); i++) {
                partSet.add(parts.get(this.random.nextInt(parts.size())));
            }
            car.setParts(partSet);
            this.carService.save(car);
        }
    }

    private void importParts() throws IOException {
        PartInputJsonDto[] parts
                = this.jsonParser.importJson(PartInputJsonDto[].class, FILE_INPUT_PARTS_JSON);
        List<Supplier> suppliers = this.supplierService.getAll();
        for (PartInputJsonDto partInputJsonDto : parts) {
            Part part = DTOConvertUtil.convert(partInputJsonDto, Part.class);
            part.setSupplier(suppliers.get(random.nextInt(suppliers.size())));
            this.partService.save(part);
        }
    }

    private void importSuppliers() throws IOException {
        SupplierInputJsonDto[] suppliers
                = this.jsonParser.importJson(SupplierInputJsonDto[].class, FILE_INPUT_SUPPLIERS_JSON);
        for (SupplierInputJsonDto supplier : suppliers) {
            this.supplierService.save(supplier);
        }
    }
}
