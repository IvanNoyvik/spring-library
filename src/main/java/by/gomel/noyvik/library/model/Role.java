package by.gomel.noyvik.library.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "role")
@ToString(exclude = "users")
@Entity
@Table(name = "ROLES")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USERS_ROLES",
            joinColumns = @JoinColumn(name = "ROLES_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "USERS_ID", referencedColumnName = "ID"))
    private Set<User> users = new HashSet<>();


    public Role(String role) {
        this.role = role;
    }

    public void addUser(User user) {
        users.add(user);
        user.getRoles().add(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getRoles().remove(this);
    }


}
