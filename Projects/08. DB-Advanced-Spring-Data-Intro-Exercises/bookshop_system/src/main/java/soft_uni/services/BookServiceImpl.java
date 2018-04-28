package soft_uni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soft_uni.models.entity.Book;
import soft_uni.repositories.BookRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<String> allTitlesAfterYear(Date year) {
        return this.bookRepository.findAllByReleaseDateAfter(year).stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }

}
