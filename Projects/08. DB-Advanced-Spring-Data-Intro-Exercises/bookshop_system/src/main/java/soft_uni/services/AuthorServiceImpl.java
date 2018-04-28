package soft_uni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soft_uni.models.entity.Author;
import soft_uni.repositories.AuthorRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void saveAuthor(Author author) {
        this.authorRepository.save(author);
    }

    @Override
    public void saveAuthor(List<Author> authors) {
        this.authorRepository.saveAll(authors);
    }

    @Override
    public List<Author> getAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public List<String> authorNamesWithAtLeastOneBookReleasedBeforeYear(int year) {
        return this.authorRepository.findAllByBooksBefore(year).stream()
                .map(a -> a.getFirstName() + " " + a.getLastName())
                .distinct()
                .collect(Collectors.toList());
    }
}
