package org.softuni.ruk.repositories;

import org.softuni.ruk.domain.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer>{
}
