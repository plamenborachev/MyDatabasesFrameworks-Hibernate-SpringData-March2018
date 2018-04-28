package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.domain.models.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    List<District> findByName(String name);

}
