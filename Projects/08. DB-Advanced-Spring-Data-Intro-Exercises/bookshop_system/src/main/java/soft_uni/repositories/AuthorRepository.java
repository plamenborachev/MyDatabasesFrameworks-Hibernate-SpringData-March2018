package soft_uni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import soft_uni.models.entity.Author;

import java.util.Date;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "SELECT *\n" +
            "FROM authors AS a\n" +
            "JOIN books AS b ON a.author_id = b.author_id\n" +
            "WHERE YEAR(b.release_date) < :year", nativeQuery = true)
    List<Author> findAllByBooksBefore(@Param("year") int year);

}
