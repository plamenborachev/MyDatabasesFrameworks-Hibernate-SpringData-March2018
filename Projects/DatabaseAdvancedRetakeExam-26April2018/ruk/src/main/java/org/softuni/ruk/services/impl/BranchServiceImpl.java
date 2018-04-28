package org.softuni.ruk.services.impl;

import org.softuni.ruk.domain.dto.binding.json.BranchJSONImportDTO;
import org.softuni.ruk.domain.entities.Branch;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.repositories.BranchRepository;
import org.softuni.ruk.services.api.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

    private final ModelParser modelParser;
    private final BranchRepository branchRepository;

    @Autowired
    public BranchServiceImpl(ModelParser modelParser, BranchRepository branchRepository) {
        this.modelParser = modelParser;
        this.branchRepository = branchRepository;
    }

    @Override
    public void create(BranchJSONImportDTO dto) {
        Branch branch = this.modelParser.convert(dto, Branch.class);
        this.branchRepository.saveAndFlush(branch);
    }

    @Override
    public List<Branch> getByName(String name) {
        return this.branchRepository.findByName(name);
    }
}
