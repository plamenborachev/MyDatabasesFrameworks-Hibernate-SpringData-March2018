package org.softuni.ruk.repositories;

import org.softuni.ruk.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

    Employee findByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT e FROM Employee e WHERE e.clients.size > 0 ORDER BY e.clients.size DESC, e.id")
    List<Employee> findAllByClientsNotNullOrderByClientsDescIdAsc();

}
