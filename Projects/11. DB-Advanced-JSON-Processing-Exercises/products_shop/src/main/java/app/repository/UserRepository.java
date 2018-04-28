package app.repository;

import app.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getById(Long id);

    @Query("SELECT u FROM User u JOIN u.productsSold ps GROUP BY u.id ORDER BY u.lastName, u.firstName")
    List<User> getAllByProductsSoldWithBuyer();

    @Query("SELECT DISTINCT u FROM User AS u " +
            "JOIN u.productsSold AS ps " +
            "WHERE ps.buyer IS NOT NULL " +
            "ORDER BY ps, u.lastName")
    List<User> getAllByProductsSoldAfterOrderByProductsSoldDescLastName();

}
