package by.gomel.noyvik.library.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(exclude = {"author", "genres", "orders"})
@ToString(exclude = {"author", "genres", "orders"})
@Entity
@Builder
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "validation.book.title.NotEmpty.message")
    @NotBlank(message = "validation.book.title.NotEmpty.message")
    @NotEmpty(message = "validation.book.title.NotEmpty.message")
    private String title;

    private String description;
    private byte[] image;

    @PositiveOrZero(message = "validation.book.quantity.PositiveOrZero.message")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHORS_ID", referencedColumnName = "ID")
    private Author author;

    @ManyToMany(mappedBy = "books")
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();


    public Book(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Book(String title, String description, int quantity) {
        this.title = title;
        this.description = description;
        this.quantity = quantity;
    }

    public Book(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setBook(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setBook(null);
    }

    public void addAuthor(Author author) {
        this.setAuthor(author);
        author.getBooks().add(this);
    }

    public void removeAuthor() {
        author.getBooks().remove(this);
        this.setAuthor(null);
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
        genre.getBooks().add(this);
    }

    public void removeGenre(Genre genre) {
        genres.remove(genre);
        genre.getBooks().remove(this);
    }
}
