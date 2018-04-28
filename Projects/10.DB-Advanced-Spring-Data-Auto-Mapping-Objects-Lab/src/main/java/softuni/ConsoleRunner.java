package softuni;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.dto.EmployeeDto;
import softuni.dto.ManagerDto;
import softuni.entities.Address;
import softuni.entities.Employee;
import softuni.repositories.AddressRepository;
import softuni.repositories.EmployeeRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final AddressRepository addressRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public ConsoleRunner(AddressRepository addressRepository, EmployeeRepository employeeRepository) {
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
//        initAddresses();
//        initEmployees();

//        1. Simple Mapping
//        simpleMapping();

//        2. Advanced Mapping
//        advancedMapping();

//        3. Projection
//        projection();
    }

    private void projection() {
        List<Employee> employees = this.employeeRepository
                .findAllByBirthdayBeforeOrderBySalaryDesc(LocalDate.parse("1990-01-01"));
        ModelMapper modelMapper = new ModelMapper();
        List<EmployeeDto> employeeDtos = new LinkedList<>();
        for (Employee employee : employees) {
            EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
            employeeDtos.add(employeeDto);
        }
        for (EmployeeDto employeeDto : employeeDtos) {
            System.out.println(String.format("%s %s %.2f – Manager: %s",
                    employeeDto.getFirstName(),
                    employeeDto.getLastName(),
                    employeeDto.getSalary(),
                    employeeDto.getManager() == null ? "[no manager]" : employeeDto.getManager().getLastName()));
        }

    }

    private void advancedMapping() {
        List<Employee> employees = this.employeeRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<ManagerDto> managerDtos = new LinkedList<>();
        for (Employee employee : employees) {
            ManagerDto managerDto = modelMapper.map(employee, ManagerDto.class);
            managerDtos.add(managerDto);
        }
        managerDtos.forEach(mdto -> {
            System.out.println(String.format("%s %s | Employees: %d",
                                            mdto.getFirstName(),
                                            mdto.getLastName(),
                                            mdto.getServants().size()));
            mdto.getServants().forEach(s -> System.out.println(String.format("\t- %s %s %.2f",
                                        s.getFirstName(), s.getLastName(), s.getSalary())));
        });

    }

    private void simpleMapping() {
        Employee employee = this.employeeRepository.getOne(1L);
        ModelMapper modelMapper = new ModelMapper();
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        System.out.println(employeeDto.getFirstName());
    }

    private void initEmployees() {
        Employee employee = new Employee();
        employee.setAddress(this.addressRepository.getOne(1L));
        employee.setBirthday(LocalDate.parse("1980-01-01"));
        employee.setFirstName("Steve");
        employee.setLastName("Jobbsen");
        employee.setSalary(BigDecimal.valueOf(1234.56));
        this.employeeRepository.saveAndFlush(employee);

        employee = new Employee();
        employee.setAddress(this.addressRepository.getOne(1L));
        employee.setBirthday(LocalDate.parse("1970-01-01"));
        employee.setFirstName("Stephen");
        employee.setLastName("Bjorn");
        employee.setSalary(BigDecimal.valueOf(4300));
        employee.setManager(this.employeeRepository.getOne(1L));
        this.employeeRepository.saveAndFlush(employee);

        employee = new Employee();
        employee.setAddress(this.addressRepository.getOne(1L));
        employee.setBirthday(LocalDate.parse("1960-01-01"));
        employee.setFirstName("Kirilyc");
        employee.setLastName("Lefi");
        employee.setSalary(BigDecimal.valueOf(4400));
        employee.setManager(this.employeeRepository.getOne(1L));
        this.employeeRepository.saveAndFlush(employee);

        employee = new Employee();
        employee.setAddress(this.addressRepository.getOne(1L));
        employee.setBirthday(LocalDate.parse("1950-01-01"));
        employee.setFirstName("Carl");
        employee.setLastName("Kormac");
        employee.setSalary(BigDecimal.valueOf(4400));
        this.employeeRepository.saveAndFlush(employee);

        employee = new Employee();
        employee.setAddress(this.addressRepository.getOne(1L));
        employee.setBirthday(LocalDate.parse("1940-01-01"));
        employee.setFirstName("Jurgen");
        employee.setLastName("Straus");
        employee.setSalary(BigDecimal.valueOf(1000.45));
        employee.setManager(this.employeeRepository.getOne(4L));
        this.employeeRepository.saveAndFlush(employee);

        employee = new Employee();
        employee.setAddress(this.addressRepository.getOne(1L));
        employee.setBirthday(LocalDate.parse("1930-01-01"));
        employee.setFirstName("Moni");
        employee.setLastName("Kozinac");
        employee.setSalary(BigDecimal.valueOf(2030.99));
        employee.setManager(this.employeeRepository.getOne(4L));
        this.employeeRepository.saveAndFlush(employee);

        employee = new Employee();
        employee.setAddress(this.addressRepository.getOne(1L));
        employee.setBirthday(LocalDate.parse("1920-01-01"));
        employee.setFirstName("Kopp");
        employee.setLastName("Spidok");
        employee.setSalary(BigDecimal.valueOf(2000.21));
        employee.setManager(this.employeeRepository.getOne(4L));
        this.employeeRepository.saveAndFlush(employee);
    }

    private void initAddresses() {
        Address address = new Address();
        address.setCity("Sofia");
        address.setCountry("Bulgaria");
        this.addressRepository.saveAndFlush(address);
    }
}

