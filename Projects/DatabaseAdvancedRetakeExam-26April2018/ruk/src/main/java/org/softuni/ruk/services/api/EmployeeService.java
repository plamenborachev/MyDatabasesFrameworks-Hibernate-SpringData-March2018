package org.softuni.ruk.services.api;

import org.softuni.ruk.domain.dto.binding.json.EmployeeJSONImportDTO;
import org.softuni.ruk.domain.dto.view.json.EmployeeJSONExportDTO;
import org.softuni.ruk.domain.entities.Employee;

import java.util.List;

public interface EmployeeService {

    void create(EmployeeJSONImportDTO dto);

    Employee getByName(String firstName, String lastName);

    List<EmployeeJSONExportDTO> getAllEmployeesWithClients();

}
