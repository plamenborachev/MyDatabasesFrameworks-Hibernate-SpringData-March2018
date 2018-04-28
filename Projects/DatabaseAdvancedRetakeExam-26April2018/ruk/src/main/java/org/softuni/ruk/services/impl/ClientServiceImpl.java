package org.softuni.ruk.services.impl;

import org.softuni.ruk.domain.dto.binding.json.ClientJSONImportDTO;
import org.softuni.ruk.domain.entities.Client;
import org.softuni.ruk.domain.entities.Employee;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.repositories.ClientRepository;
import org.softuni.ruk.services.api.ClientService;
import org.softuni.ruk.services.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ModelParser modelParser;
    private final ClientRepository clientRepository;
    private final EmployeeService employeeService;

    @Autowired
    public ClientServiceImpl(ModelParser modelParser, ClientRepository clientRepository, EmployeeService employeeService) {
        this.modelParser = modelParser;
        this.clientRepository = clientRepository;
        this.employeeService = employeeService;
    }

    @Override
    public void create(ClientJSONImportDTO dto) {
        Client client = this.clientRepository.findByFullNameAndAge(dto.getFirstName() + " " + dto.getLastName(), dto.getAge());
        Employee employee = this.employeeService.getByName(dto.getAppointedEmployee().split("\\s+")[0], dto.getAppointedEmployee().split("\\s+")[1]);
        if (client != null) {
            client.addEmployee(employee);
        } else {
            client = this.modelParser.convert(dto, Client.class);
            client.setFullName(String.format("%s %s", dto.getFirstName(), dto.getLastName()));
            client.addEmployee(employee);
            this.clientRepository.saveAndFlush(client);
        }
    }

    @Override
    public Client getByFullName(String fullName) {
        return this.clientRepository.findByFullName(fullName);
    }

    @Override
    public Client getByFullNameAndAge(String fullName, Integer age) {
        return null;
    }
}
