package soft_uni.services;

import soft_uni.enums.AgeRestriction;
import soft_uni.enums.EditionType;
import soft_uni.models.dto.books.ReducedBook;
import soft_uni.models.dto.books.ReducedBookImpl;
import soft_uni.models.entity.Author;
import soft_uni.models.entity.Book;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface BookService {

    void saveBooks(List<Book> books);

    List<Book> getAll();

    List<Book> getAllByAgeRestriction(String ageRestriction);

    List<Book> getAllOfTheGoldenEditionBooksWithLessThan5000Copies();

    List<Book> getAllWithPriceLowerThan5AndHigherThan40();

    List<Book> getAllBooksNotReleasedOnGivenYear(int year);

    List<Book> getAllReleasedBeforeDate(String date) throws ParseException;

    List<Book> getAllWithTitlesContainsGivenString(String str);

    List<Book> getAllWrittenByAuthorsWithLastNameStartsWithGivenString(String str);

    int getCountOfBooksWhoseTitleIsLongerThanGivenNumber(int titleMinLength);

    ReducedBookImpl getReducedBook(String title);

    int increasesCopiesOfAllBooksReleasedAfterGivenDateWithGivenNumber(int number, String inputDate) throws ParseException;

    int removeAllBooksWithCopiesLowerThan(int number);

    int getTotalNumberOfBooksAuthorHasWritten(String first_name, String last_name);

}

