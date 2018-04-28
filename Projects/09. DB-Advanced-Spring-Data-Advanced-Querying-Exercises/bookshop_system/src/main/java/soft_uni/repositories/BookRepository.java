package soft_uni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import soft_uni.enums.AgeRestriction;
import soft_uni.enums.EditionType;
import soft_uni.models.dto.books.ReducedBook;
import soft_uni.models.entity.Author;
import soft_uni.models.entity.Book;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesIsLessThan(EditionType editionType, int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerPrice, BigDecimal higherPrice);

    @Query(value = "SELECT *\n" +
            "FROM books AS b\n" +
            "WHERE YEAR(b.release_date) NOT LIKE :year", nativeQuery = true)
    List<Book> findAllByReleaseDateYearNot(@Param("year") int year);

    List<Book> findAllByReleaseDateBefore(Date date);

    List<Book> findAllByTitleContains(String str);

    @Query("SELECT b FROM Book b JOIN b.author a WHERE a.lastName LIKE CONCAT(:str, '%') ")
    List<Book> findAllByAuthorStartsWith(@Param("str") String str);

    @Query("SELECT COUNT(b) FROM Book b WHERE LENGTH(b.title) > :titleMinLength")
    int countAllByTitleGreaterThan(@Param("titleMinLength") int titleMinLength);

    @Modifying
    @Query("UPDATE Book AS b SET b.copies = b.copies + :copies WHERE b.releaseDate > :date")
    int updateCopiesOfBooksReleasedAfter(@Param("copies") int copies, @Param("date") Date date);

    @Modifying
    int deleteAllByCopiesLessThan(int number);

    @Procedure(name = "usp_select_total_number_of_books_by_author")
    int countAllByAuthorNames(@Param("first_name") String first_name, @Param("last_name") String last_name);

    Book getBookByTitleEquals(String title);

    @Procedure(name = "usp_select_total_number_of_books_by_author")
    int countOfBookCopiesByAuthorName(@Param("first_name") String first_name,
                                      @Param("last_name") String last_name,
                                      @Param("book_count") int book_count);
}
