package by.gomel.noyvik.library.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")
@Entity
@Table(name = "STATUSES")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;

    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE})
    private Set<User> users = new HashSet<>();

    public Status() {
    }

    public void addUser(User user) {
        users.add(user);
        user.setStatus(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.setStatus(null);
    }


}
