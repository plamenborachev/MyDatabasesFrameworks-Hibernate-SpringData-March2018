package org.softuni.ruk.services.api;

import org.softuni.ruk.domain.dto.binding.json.BranchJSONImportDTO;
import org.softuni.ruk.domain.entities.Branch;

import java.util.List;

public interface BranchService {

    void create(BranchJSONImportDTO dto);

    List<Branch> getByName(String name);
}
