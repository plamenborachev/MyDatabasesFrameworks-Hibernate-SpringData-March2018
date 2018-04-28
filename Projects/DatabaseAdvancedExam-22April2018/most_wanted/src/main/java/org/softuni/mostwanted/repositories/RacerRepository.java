package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.domain.models.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RacerRepository extends JpaRepository<Racer, Integer> {

    Racer findByName(String name);

    @Query("SELECT DISTINCT r FROM Racer r JOIN r.cars c")
    List<Racer> findAllByCarsNotNullOrderByCarsDescNameAsc();

}
