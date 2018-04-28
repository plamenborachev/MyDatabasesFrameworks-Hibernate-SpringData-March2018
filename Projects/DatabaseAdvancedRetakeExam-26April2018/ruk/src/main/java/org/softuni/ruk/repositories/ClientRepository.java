package org.softuni.ruk.repositories;

import org.softuni.ruk.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

    Client findByFullName(String fullName);

    Client findByFullNameAndAge(String fullName, Integer age);


}
