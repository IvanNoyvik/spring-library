package by.gomel.noyvik.library.model;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@EqualsAndHashCode(exclude = "books")
@ToString(exclude = "books")
@Getter
@Setter
@Entity
@Table(name = "GENRES")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String genre;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "BOOKS_GENRES",
            joinColumns = @JoinColumn(name = "GENRES_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOKS_ID", referencedColumnName = "ID"))
    private Set<Book> books = new HashSet<>();


    public Genre(String genre) {
        this.genre = genre;
    }

    public void addBook(Book book) {
        books.add(book);
        book.getGenres().add(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.getGenres().remove(this);
    }
}
