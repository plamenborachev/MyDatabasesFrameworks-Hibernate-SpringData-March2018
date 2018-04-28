package org.softuni.ruk.services.impl;

import org.softuni.ruk.domain.dto.binding.json.EmployeeJSONImportDTO;
import org.softuni.ruk.domain.dto.view.json.EmployeeJSONExportDTO;
import org.softuni.ruk.domain.entities.Branch;
import org.softuni.ruk.domain.entities.Employee;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.repositories.EmployeeRepository;
import org.softuni.ruk.services.api.BranchService;
import org.softuni.ruk.services.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final ModelParser modelParser;
    private final EmployeeRepository employeeRepository;
    private final BranchService branchService;

    @Autowired
    public EmployeeServiceImpl(ModelParser modelParser, EmployeeRepository employeeRepository, BranchService branchService) {
        this.modelParser = modelParser;
        this.employeeRepository = employeeRepository;
        this.branchService = branchService;
    }

    @Override
    public void create(EmployeeJSONImportDTO dto) {
        Employee employee = this.modelParser.convert(dto, Employee.class);
        employee.setFirstName(dto.getFullName().split("\\s+")[0]);
        employee.setLastName(dto.getFullName().split("\\s+")[1]);
        List<Branch> branchByName = this.branchService.getByName(dto.getBranchName());
        employee.setBranch(branchByName.get(0));
        this.employeeRepository.saveAndFlush(employee);
    }

    @Override
    public Employee getByName(String firstName, String lastName) {
        return this.employeeRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<EmployeeJSONExportDTO> getAllEmployeesWithClients() {
        List<Employee> employees = this.employeeRepository
                .findAllByClientsNotNullOrderByClientsDescIdAsc();
        List<EmployeeJSONExportDTO> employeeJSONExportDTOS = new LinkedList<>();
        for (Employee employee : employees) {
            EmployeeJSONExportDTO employeeJSONExportDTO = this.modelParser.convert(employee, EmployeeJSONExportDTO.class);
            employeeJSONExportDTOS.add(employeeJSONExportDTO);
        }
        return employeeJSONExportDTOS;
    }
}
