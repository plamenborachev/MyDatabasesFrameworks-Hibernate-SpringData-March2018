package app.retake.repositories;

import app.retake.domain.models.Animal;
import app.retake.domain.models.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer>{

    Animal findByPassportSerialNumber(String passport_serialNumber);

    @Query("SELECT a FROM Animal a " +
            "WHERE a.passport.ownerPhoneNumber = :ownerPhoneNumber " +
            "ORDER BY a.age, a.passport.serialNumber")
    List<Animal> findAllByPassportOwnerPhoneNumberOrderByAgeAndPassportSerialNumber(@Param("ownerPhoneNumber") String passport_ownerPhoneNumber);

}
