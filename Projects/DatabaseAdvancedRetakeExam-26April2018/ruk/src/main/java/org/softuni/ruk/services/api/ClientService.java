package org.softuni.ruk.services.api;

import org.softuni.ruk.domain.dto.binding.json.ClientJSONImportDTO;
import org.softuni.ruk.domain.entities.Client;

public interface ClientService {

    void create(ClientJSONImportDTO dto);

    Client getByFullName(String fullName);

    Client getByFullNameAndAge(String fullName, Integer age);
}
