package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.domain.models.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {

    Town findByName(String name);

    @Query(value = "SELECT t.name, COUNT(r.id) AS racers\n" +
            "FROM towns AS t\n" +
            "JOIN racers AS r ON t.id = r.home_town_id\n" +
            "GROUP BY t.id\n" +
            "ORDER BY racers DESC, t.name", nativeQuery = true)
    String[] findAllByRacers();

}
