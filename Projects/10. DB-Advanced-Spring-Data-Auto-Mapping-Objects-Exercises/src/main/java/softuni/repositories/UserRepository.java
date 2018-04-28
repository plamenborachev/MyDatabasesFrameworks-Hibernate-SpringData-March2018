package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.models.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User getById(Long id);

    User getByEmail(String email);

    List<User> getAllByLoggedInIsTrue();

}
