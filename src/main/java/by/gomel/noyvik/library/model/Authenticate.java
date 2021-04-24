package by.gomel.noyvik.library.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(exclude = "user")
@ToString(exclude = "user")
@Entity
@Table(name = "AUTHENTICATES")
public class Authenticate {

    @Id
    private Long id;
    private String login;
    private String password;
    @Column(name = "UNLOCKED_DATE")
    private LocalDate unlockedDate = LocalDate.now();

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    public Authenticate(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void addUser(User user) {
        this.user = user;
        user.setAuthenticate(this);
    }

    public void removeUser() {
        user.setAuthenticate(null);
        this.setUser(null);

    }


}
