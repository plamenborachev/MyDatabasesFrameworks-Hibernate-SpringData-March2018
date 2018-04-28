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

    List<Author> findAllByFirstNameEndsWith(String postfix);

    @Query(value = "SELECT CONCAT(a.first_name, ' ', a.last_name, ' - ', SUM(b.copies))\n"+
                  "FROM authors AS a\n"+
                  "JOIN books AS b ON b.author_id = a.author_id\n"+
                  "GROUP BY a.author_id\n"+
                  "ORDER BY SUM(b.copies) DESC", nativeQuery = true)
    String[] findAllByBooksCopies();
}
