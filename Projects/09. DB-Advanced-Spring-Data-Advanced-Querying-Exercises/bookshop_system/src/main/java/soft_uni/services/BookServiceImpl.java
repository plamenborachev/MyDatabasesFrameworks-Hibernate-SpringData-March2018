package soft_uni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soft_uni.enums.AgeRestriction;
import soft_uni.enums.EditionType;
import soft_uni.models.dto.books.ReducedBook;
import soft_uni.models.dto.books.ReducedBookImpl;
import soft_uni.models.entity.Book;
import soft_uni.repositories.BookRepository;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static soft_uni.enums.EditionType.GOLD;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void saveBooks(List<Book> books) {
        this.bookRepository.saveAll(books);
    }

    @Override
    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public List<Book> getAllByAgeRestriction(String input) {
        AgeRestriction ageRestriction = AgeRestriction.valueOf(input.toUpperCase());
        return this.bookRepository.findAllByAgeRestriction(ageRestriction);
    }

    @Override
    public List<Book> getAllOfTheGoldenEditionBooksWithLessThan5000Copies() {
        return this.bookRepository.findAllByEditionTypeAndCopiesIsLessThan(EditionType.GOLD, 5000);
    }

    @Override
    public List<Book> getAllWithPriceLowerThan5AndHigherThan40() {
        return this.bookRepository
                .findAllByPriceLessThanOrPriceGreaterThan(BigDecimal.valueOf(5), BigDecimal.valueOf(40));
    }

    @Override
    public List<Book> getAllBooksNotReleasedOnGivenYear(int year) {
        return this.bookRepository.findAllByReleaseDateYearNot(year);
    }

    @Override
    public List<Book> getAllReleasedBeforeDate(String inputDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter.parse(inputDate);
        return this.bookRepository.findAllByReleaseDateBefore(date);
    }

    @Override
    public List<Book> getAllWithTitlesContainsGivenString(String str) {
        return this.bookRepository.findAllByTitleContains(str);
    }

    @Override
    public List<Book> getAllWrittenByAuthorsWithLastNameStartsWithGivenString(String str) {
        return this.bookRepository.findAllByAuthorStartsWith(str);
    }

    @Override
    public int getCountOfBooksWhoseTitleIsLongerThanGivenNumber(int titleMinLength) {
        return this.bookRepository.countAllByTitleGreaterThan(titleMinLength);
    }

    @Override
    public ReducedBookImpl getReducedBook(String title){
        Book book = this.bookRepository.getBookByTitleEquals(title);
        return new ReducedBookImpl(book.getTitle(), book.getEditionType(),
                book.getAgeRestriction(), book.getPrice());
    }

    @Override
    public int increasesCopiesOfAllBooksReleasedAfterGivenDateWithGivenNumber(int number, String inputDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
        Date date = formatter.parse(inputDate);
        return this.bookRepository.updateCopiesOfBooksReleasedAfter(number, date);
    }

    @Override
    public int removeAllBooksWithCopiesLowerThan(int number) {
        return this.bookRepository.deleteAllByCopiesLessThan(number);
    }

    @Override
    public int getTotalNumberOfBooksAuthorHasWritten(String first_name, String last_name) {
        return this.bookRepository.countAllByAuthorNames(first_name, last_name);
    }

}
