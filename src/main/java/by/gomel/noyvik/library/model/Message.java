package by.gomel.noyvik.library.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@EqualsAndHashCode(exclude = "user")
@ToString(exclude = "user")
@Setter
@Getter
@Table(name = "MESSAGES")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DATE_SENT")
    private LocalDate dateSent;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID", referencedColumnName = "ID")
    private User user;


    public Message(LocalDate dateSent, String content, User user) {
        this.dateSent = dateSent;
        this.content = content;
        this.addUser(user);
    }

    public void addUser(User user) {
        user.getMessages().add(this);
        this.setUser(user);
    }

    public void removeUser() {
        user.getMessages().remove(this);
        this.setUser(null);
    }


}
