package soft_uni.services;

import soft_uni.models.entity.Author;

import java.util.List;

public interface AuthorService {

    void saveAuthor(Author author);

    void saveAuthor(List<Author> authors);

    List<Author> getAll();

    List<Author> getAllWithFirstNameEndWithGivenString(String postfix);

    String[] totalNumberOfBookCopiesByAuthor();
}

