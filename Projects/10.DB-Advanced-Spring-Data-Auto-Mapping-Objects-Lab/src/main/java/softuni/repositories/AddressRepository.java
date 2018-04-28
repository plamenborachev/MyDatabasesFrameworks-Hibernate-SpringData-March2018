package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import softuni.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
