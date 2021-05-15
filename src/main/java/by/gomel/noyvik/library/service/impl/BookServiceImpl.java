package by.gomel.noyvik.library.service.impl;

import by.gomel.noyvik.library.exception.ServiceException;
import by.gomel.noyvik.library.model.Author;
import by.gomel.noyvik.library.model.Book;
import by.gomel.noyvik.library.model.Genre;
import by.gomel.noyvik.library.persistence.repository.AuthorRepository;
import by.gomel.noyvik.library.persistence.repository.BookRepository;
import by.gomel.noyvik.library.persistence.repository.GenreRepository;
import by.gomel.noyvik.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static by.gomel.noyvik.library.util.constant.ApplicationConstant.BOOK_EXISTS;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;


    @Override
    public Page<Book> findPageBooks(int page) {

        return bookRepository.findAll(PageRequest.of(page, 5));

    }

    @Override
    public byte[] findImageById(Long id) {

        byte[] image = bookRepository.findImageById(id);

        if (image != null) {

            return image;

        }
        throw new ServiceException("Image not find");
    }

    @Override
    @Transactional
    public void addImage(Long id, MultipartFile file) {

        try {
            bookRepository.imageBulkUpdate(id, file.getBytes());
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isExists(String title, String author) {

        return !title.isEmpty() && !author.isEmpty() && bookRepository.existsByTitleAndAuthorAuthor(title, author);
    }

    @Override
    @Modifying
    @Transactional
    public Book save(Book book) {

        if (!isExists(book.getTitle(), book.getAuthor().getAuthor())) {

            Author author = authorRepository.findByAuthor(book.getAuthor().getAuthor());
            book.setAuthor(author);

            if (book.getGenres().size() == 1) {

                Genre genre = genreRepository.findByGenre(book.getGenres().stream().findFirst().orElseThrow(ServiceException::new).getGenre());
                book.getGenres().clear();
                book.addGenre(genre);

            } else {

                switchGenres(book);
            }

            return bookRepository.save(book);

        } else {
            throw new ServiceException(BOOK_EXISTS);
        }

    }


    @Override
    @Transactional(rollbackFor = ServiceException.class)
    @Modifying
    public Book update(Book book) {

        try {

            Book oldBook = bookRepository.findFullBookById(book.getId());

            if (book.getAuthor().getAuthor().equals(oldBook.getAuthor().getAuthor())) {
                book.setAuthor(oldBook.getAuthor());
            } else {
                book.setAuthor(authorRepository.findByAuthor(book.getAuthor().getAuthor()));
            }


            if (oldBook.getGenres().equals(book.getGenres())) {
                book.setGenres(oldBook.getGenres());
            } else {
                while (!oldBook.getGenres().isEmpty()) {
                    for (Genre genre : oldBook.getGenres()) {
                        oldBook.removeGenre(genre);
                        break;
                    }
                }

                switchGenres(book);
            }
            book.setImage(oldBook.getImage());
            return bookRepository.save(book);

        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }

    @Override
    public Book findBookById(Long bookId) {

        return bookRepository.findFullBookById(bookId);
    }

    private void switchGenres(Book book) {
        List<Genre> allGenre = genreRepository.findAll();
        Set<Genre> newGenres = book.getGenres();
        book.setGenres(new HashSet<>());

        for (Genre genre : allGenre) {
            for (Genre newGenre : newGenres) {
                if (newGenre.equals(genre)) {
                    book.addGenre(genre);
                    break;
                }
            }
        }
    }
}
