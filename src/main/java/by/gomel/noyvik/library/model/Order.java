package by.gomel.noyvik.library.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@EqualsAndHashCode(exclude = {"book", "user"})
@ToString(exclude = {"book", "user"})
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DATE_RECEIVING")
    private LocalDate dateReceiving;

    @Positive(message = "validation.order.duration.message")
    @Max(value = 180, message = "validation.order.duration.message")
    private int duration;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKS_ID", referencedColumnName = "ID")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID", referencedColumnName = "ID")
    private User user;


    public Order(LocalDate dateReceiving, int duration, Book book, User user) {
        this.dateReceiving = dateReceiving;
        this.duration = duration;
        this.book = book;
        this.user = user;
    }


    public void addUser(User user) {
        this.setUser(user);
        user.getOrders().add(this);
    }

    public void removeUser() {
        user.getOrders().remove(this);
        this.setUser(null);
    }

    public void addBook(Book book) {
        this.setBook(book);
        book.getOrders().add(this);
    }

    public void removeBook() {
        book.getOrders().remove(this);
        this.setBook(null);
    }


}
