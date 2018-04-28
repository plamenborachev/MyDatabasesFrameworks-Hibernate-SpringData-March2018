package p08BookLibrary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int numberOfBooks = Integer.parseInt(reader.readLine());

        Library library = new Library();

        Book book = null;

        for (int i = 0; i < numberOfBooks; i++) {
            String[] bookTokens = reader.readLine().split("\\s+");
            book = new Book(bookTokens[0], bookTokens[1], bookTokens[2], bookTokens[3], bookTokens[4], Double.parseDouble(bookTokens[5]));
            library.addBook(book);
        }

        final Comparator<Map.Entry<String, Double>> byTotalPrice =
                Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder());

        final Comparator<Map.Entry<String, Double>> byAuthorName =
                Comparator.comparing(Map.Entry::getKey);

        library.getBooks().stream()
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.summingDouble(Book::getPrice)))
                .entrySet().stream()
                .sorted(byTotalPrice.thenComparing(byAuthorName))
                .forEach(a -> System.out.println(String.format("%s -> %.2f", a.getKey(), a.getValue())));

//        library.getBooks().stream()
//                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.summingDouble(Book::getPrice)))
//                .entrySet().stream()
//                .sorted(Comparator.comparing(Map.Entry<String, Double>::getValue, Comparator.reverseOrder())
// .thenComparing(Comparator.comparing(Map.Entry::getKey)))
//                .forEach(a -> System.out.println(String.format("%s -> %.2f", a.getKey(), a.getValue())));
    }
}
