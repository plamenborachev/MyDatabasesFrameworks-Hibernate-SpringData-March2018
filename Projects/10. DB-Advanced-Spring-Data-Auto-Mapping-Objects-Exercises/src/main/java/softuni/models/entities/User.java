package softuni.models.entities;

import softuni.validators.email.Email;
import softuni.validators.passowrd.Password;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email", unique = true)
    @Email
    private String email;

    @Column(name = "password")
    @Password(containsUpperCase = true, containsLowerCase = true, containsDigit = true)
    private String password;

    @Column(name = "full_name")
    private  String fullName;

    @ManyToMany
    @JoinTable(name = "users_games",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "game_id"))
    private Set<Game> games;

    @OneToMany(mappedBy = "buyer", targetEntity = Order.class)
    private Set<Order> orders;

    @Column(name = "is_administrator")
    private boolean isAdministrator;

    @Column(name = "is_logged_in")
    private boolean loggedIn;

    public User() {
        this.games = new HashSet<>();
        this.orders = new HashSet<>();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Game> getGames() {
        return this.games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public boolean isAdministrator() {
        return this.isAdministrator;
    }

    public void setAdministrator(boolean administrator) {
        isAdministrator = administrator;
    }

    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
