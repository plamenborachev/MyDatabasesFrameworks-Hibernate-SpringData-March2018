package p08BookLibrary;

public class Book {
    private String title;
    private String author;
    private String publisher;
    private String releaseDate;
    private String ISBNnumber;
    private double price;

    public Book(String title, String author, String publisher, String releaseDate, String ISBNnumber, double price) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.ISBNnumber = ISBNnumber;
        this.price = price;
    }

    public String getAuthor() {
        return this.author;
    }

    public double getPrice() {
        return this.price;
    }
}
