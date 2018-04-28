package org.softuni.ruk.repositories;

import org.softuni.ruk.domain.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer>{

    List<Branch> findByName(String name);

}
