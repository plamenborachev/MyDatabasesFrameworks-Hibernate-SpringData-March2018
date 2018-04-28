package soft_uni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import soft_uni.enums.AgeRestriction;
import soft_uni.enums.EditionType;
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
import java.time.Year;
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

    @Autowired
    public ConsoleRunner(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... strings) throws Exception {
        initAuthors();
        initCategories();
        initBooks();

        allBooksTitlesAfter2000();
//        authorsWithAtLeastOneBookReleasedBefore1990();
//        allAuthorsByNumberOfBooks();
//        allBooksFromGeorgePowell();
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

    private void allBooksTitlesAfter2000() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2000-01-01");
        List<String> test = this.bookService.allTitlesAfterYear(date);

        System.out.println("1. Get all books after the year 2000. Print only their titles.");
        test.forEach(System.out::println);
    }

    private void authorsWithAtLeastOneBookReleasedBefore1990() throws ParseException {
        List<String> names = this.authorService
                .authorNamesWithAtLeastOneBookReleasedBeforeYear(1990);

        System.out.println("2. Get all authors with at least one book with release date before 1990. Print their " +
                "first name and last name.");
        names.forEach(System.out::println);
    }

    private void allAuthorsByNumberOfBooks() {
        System.out.println("3. Get all authors, ordered by the number of their books (descending). Print their first " + "name, last name and book count.");
        List<String> result = this.authorService.getAll().stream()
                .sorted((a1, a2) -> Integer.compare(a2.getBooks().size(), a1.getBooks().size()))
                .map(a -> String.format("%s %s %d",
                        a.getFirstName(),
                        a.getLastName(),
                        a.getBooks().size()))
                .collect(Collectors.toList());
        result.forEach(System.out::println);
    }

    private void allBooksFromGeorgePowell(){
        System.out.println("4. Get all books from author George Powell, ordered by their release date (descending), then by book title (ascending). Print the book's title, release date and copies.");
        this.bookService.getAll().stream()
                .filter(b -> b.getAuthor().getFirstName().equals("George")
                            && b.getAuthor().getLastName().equals("Powell"))
                .sorted(Comparator.comparing(Book::getReleaseDate).reversed()
                        .thenComparing(Book::getTitle))
                .forEach(b -> System.out.println(String.format("%s - %s - %d",
                                                b.getTitle(), b.getReleaseDate(), b.getCopies())));
    }
}
