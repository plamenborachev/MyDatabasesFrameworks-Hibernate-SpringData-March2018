package soft_uni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import soft_uni.enums.AgeRestriction;
import soft_uni.enums.EditionType;
import soft_uni.models.dto.books.ReducedBook;
import soft_uni.models.dto.books.ReducedBookImpl;
import soft_uni.models.entity.Author;
import soft_uni.models.entity.Book;
import soft_uni.models.entity.Category;
import soft_uni.services.AuthorService;
import soft_uni.services.BookService;
import soft_uni.services.CategoryService;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private static final String AUTHORS_RESOURCE_FILE =
            System.getProperty("user.dir") + "/src/main/resources/authors.txt";

    private static final String CATEGORIES_RESOURCE_FILE =
            System.getProperty("user.dir") + "/src/main/resources/categories.txt";

    private static final String BOOKS_RESOURCE_FILE =
            System.getProperty("user.dir") + "/src/main/resources/books.txt";

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;
    private BufferedReader reader;

    @Autowired
    public ConsoleRunner(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... strings) throws Exception {

        initAuthors();
        initCategories();
        initBooks();

//        Problem 1. Books Titles by Age Restriction
//        booksTitlesByAgeRestriction(reader.readLine());

//        Problem 2. Golden Books
//        goldenBooks();

//        Problem 3. Books by Price
//        booksByPrice();

//        Problem 4. Not Released Books
//        notReleasedBooks(Integer.parseInt(reader.readLine()));

//        Problem 5. Books Released Before Date
//        booksReleasedBeforeDate(reader.readLine());

//        Problem 6. Authors Search
//        authorsSearch(reader.readLine());

//        Problem 7. Books Search
//        booksSearch(reader.readLine());

//        Problem 8. Book Titles Search
//        bookTitlesSearch(reader.readLine());

//        Problem 9. Count Books
//        countBooks(Integer.parseInt(reader.readLine()));

//        Problem 10. Total Book Copies
//        totalBookCopies();

//        Problem 11. Reduced Book
//        reducedBook(reader.readLine());

//        Problem 12. * Increase Book Copies
//        increaseBookCopies(reader.readLine(), Integer.parseInt(reader.readLine()));

//        Problem 13. * Remove Books
//        removeBooks(Integer.parseInt(reader.readLine()));

//        Problem 14. * Stored Procedure
//        storedProcedure();

    }

    private void storedProcedure() throws IOException {
        String[] names = reader.readLine().split("\\s+");
        int totalNumberOfBooksAuthorHasWritten = this.bookService.getTotalNumberOfBooksAuthorHasWritten(names[0], names[1]);
        System.out.println(totalNumberOfBooksAuthorHasWritten);

    }

    private void removeBooks(int number) throws IOException {
        int booksRemoved = this.bookService.removeAllBooksWithCopiesLowerThan(number);
        System.out.println(String.format("%d books were deleted", booksRemoved));
    }

    private void increaseBookCopies(String date, int copies) throws IOException, ParseException {
        int numberOfBooksWithAddedCopies = this.bookService
                .increasesCopiesOfAllBooksReleasedAfterGivenDateWithGivenNumber(copies, date);
        System.out.println(numberOfBooksWithAddedCopies * copies);
    }

    private void reducedBook(String title) throws IOException {
        ReducedBook bookByTitle = this.bookService.getReducedBook(title);
        System.out.println(String.format("%s %s %s %s", bookByTitle.getTitle(),
                                                        bookByTitle.getEditionType(),
                                                        bookByTitle.getAgeRestriction(),
                                                        bookByTitle.getPrice()));
    }

    private void totalBookCopies() {
        Arrays.stream(this.authorService.totalNumberOfBookCopiesByAuthor())
        .forEach(System.out::println);
    }

    private void countBooks(int length) throws IOException {
        int count = this.bookService
                .getCountOfBooksWhoseTitleIsLongerThanGivenNumber(length);
        System.out.println(count);
    }

    private void bookTitlesSearch(String prefix) throws IOException {
        List<Book> books = this.bookService
                .getAllWrittenByAuthorsWithLastNameStartsWithGivenString(prefix);
        books.forEach(b -> System.out.println(String.format("%s (%s %s)",
                b.getTitle(), b.getAuthor().getFirstName(), b.getAuthor().getLastName())));
    }

    private void booksSearch(String substr) throws IOException {
        List<Book> books = this.bookService.getAllWithTitlesContainsGivenString(substr);
        books.forEach(b -> System.out.println(b.getTitle()));
    }

    private void authorsSearch(String postfix) throws IOException {
        List<Author> authors = this.authorService
                .getAllWithFirstNameEndWithGivenString(postfix);
        authors.forEach(a -> System.out.println(String.format("%s %s",
                a.getFirstName(), a.getLastName())));
    }

    private void booksReleasedBeforeDate(String date) throws IOException, ParseException {
        List<Book> books = this.bookService.getAllReleasedBeforeDate(date);
        books.forEach(b -> System.out.println(String.format("%s - %s - %s$",
                b.getTitle(), b.getEditionType(), b.getPrice())));
    }

    private void notReleasedBooks(int year) {
        List<Book> books = this.bookService.getAllBooksNotReleasedOnGivenYear(year);
        books.forEach(b -> System.out.println(b.getTitle()));
    }

    private void booksByPrice() {
        List<Book> books = this.bookService.getAllWithPriceLowerThan5AndHigherThan40();
        books.forEach(b -> System.out.println(String.format("%s - %s$", b.getTitle(), b.getPrice())));
    }

    private void goldenBooks() {
        List<Book> books = this.bookService
                .getAllOfTheGoldenEditionBooksWithLessThan5000Copies();
        books.forEach(b -> System.out.println(b.getTitle()));
    }

    private void booksTitlesByAgeRestriction(String input) throws IOException {
        List<Book> allByAgeRestriction = this.bookService.getAllByAgeRestriction(input);
        allByAgeRestriction.forEach(b -> System.out.println(b.getTitle()));
    }

    private void initAuthors() throws IOException {
        List<String> allAuthors = Files.readAllLines(Paths.get(AUTHORS_RESOURCE_FILE));

        List<Author> authors = allAuthors.stream()
                .map(s -> {
                    String[] authorNames = s.split("\\s+");
                    return new Author(authorNames[0], authorNames[1]);
                })
                .collect(Collectors.toList());
        this.authorService.saveAuthor(authors);
    }

    private void initCategories() throws IOException {
        List<String> allCategories = Files.readAllLines(Paths.get(CATEGORIES_RESOURCE_FILE));
        List<Category> categories = allCategories.stream()
                .filter(s -> !s.isEmpty())
                .map(Category::new)
                .collect(Collectors.toList());
        this.categoryService.saveCategories(categories);
    }

    private void initBooks() throws IOException, ParseException {
        List<String> allBooks = Files.readAllLines(Paths.get(BOOKS_RESOURCE_FILE));
        List<Author> authors = this.authorService.getAll();
        List<Category> categories = this.categoryService.getAll();

        Random random = new Random();

        List<Book> books = new LinkedList<>();

        for (String line : allBooks) {
            String[] data = line.split("\\s+");
            int authorIndex = random.nextInt(authors.size());
            Author author = authors.get(authorIndex);
            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = formatter.parse(data[1]);
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }
            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);
            book.getCategories().add(categories.get(random.nextInt(categories.size())));

            books.add(book);
        }
        this.bookService.saveBooks(books);
    }
}
