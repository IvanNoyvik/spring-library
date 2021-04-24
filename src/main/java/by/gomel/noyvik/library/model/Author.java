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
@Table(name = "AUTHORS")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Book> books = new HashSet<>();


    public Author(String author) {
        this.author = author;
    }

    public void addBook(Book book) {
        books.add(book);
        book.setAuthor(this);
    }

    public void removeUser(Book book) {
        books.remove(book);
        book.setAuthor(null);
    }
}
