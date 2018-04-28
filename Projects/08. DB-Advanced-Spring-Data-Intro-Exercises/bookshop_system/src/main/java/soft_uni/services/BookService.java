package soft_uni.services;

import soft_uni.models.entity.Author;
import soft_uni.models.entity.Book;

import java.util.Date;
import java.util.List;

public interface BookService {

    void saveBooks(List<Book> books);

    List<String> allTitlesAfterYear(Date year);

    List<Book> getAll();

}

