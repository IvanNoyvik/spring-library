package by.gomel.noyvik.library.service.impl;

import by.gomel.noyvik.library.exception.ServiceException;
import by.gomel.noyvik.library.model.Author;
import by.gomel.noyvik.library.persistence.repository.AuthorRepository;
import by.gomel.noyvik.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;


    @Override
    public void addAuthor(Author author) {
        if (!isExists(author.getAuthor())) {
            authorRepository.save(author);
        } else {
            throw new ServiceException();
        }
    }


    @Override
    public boolean isExists(String authorStr) {

        return authorRepository.findByAuthor(authorStr) != null;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

}
