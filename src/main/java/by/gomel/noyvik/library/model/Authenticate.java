package by.gomel.noyvik.library.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

import static by.gomel.noyvik.library.util.constant.ApplicationConstant.LOGIN_REGEX;
import static by.gomel.noyvik.library.util.constant.ApplicationConstant.PASSWORD_REGEX;

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

    @Pattern(regexp = LOGIN_REGEX, message = "{validation.login.Pattern.message}")
    private String login;

    @Pattern(regexp = PASSWORD_REGEX, message = "{validation.password.Pattern.message}")
    private String password;

    @Column(name = "UNLOCKED_DATE")
    private LocalDate unlockedDate = LocalDate.now();

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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
