package by.gomel.noyvik.library.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static by.gomel.noyvik.library.util.constant.ApplicationConstant.ROLE_ADMIN;

@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(exclude = {"authenticate", "roles", "orders", "messages", "status"})
@ToString(exclude = {"authenticate", "roles", "orders", "messages", "status"})
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Email(message = "{validation.email.Email.message}")
    private String email;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Authenticate authenticate;

    @ManyToMany(mappedBy = "users", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE})
    @JoinColumn(name = "STATUSES_ID", referencedColumnName = "ID")
    private Status status;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();


    public User(String name, String email) {
        this.name = name;
        this.email = email;

    }


    public int getCountOverdueOrder() {
        return (int) orders.stream().
                filter(o -> o.getDateReceiving().plusDays(o.getDuration()).isBefore(LocalDate.now()))
                .count();
    }

    public boolean isAdministrator() {

            return roles.stream().map(Role::getRole).anyMatch(s -> s.equalsIgnoreCase(ROLE_ADMIN));

    }


    public void addAuthenticate(Authenticate authenticate) {
        this.authenticate = authenticate;
        authenticate.setUser(this);
    }

    public void removeAuthenticate() {
        authenticate.setUser(null);
        this.setAuthenticate(null);

    }

    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }

    public void addStatus(Status status) {
        this.setStatus(status);
        status.getUsers().add(this);
    }

    public void removeStatus() {
        status.getUsers().remove(this);
        this.setStatus(null);
    }

    public void addMessage(Message message) {
        messages.add(message);
        message.setUser(this);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
        message.setUser(null);
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setUser(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setUser(null);
    }

}
