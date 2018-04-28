package softuni.user_system.entities;

import org.springframework.data.annotation.Transient;
import softuni.user_system.validators.email.Email;
import softuni.user_system.validators.passowrd.Password;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Transient
    private String fullName;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    @Password(containsDigit = true, containsLowerCase = true,
            containsUpperCase = true, containsSpecialSymbol = true)
    private String password;

    @Column(name = "email", nullable = false)
    @Email
    private String email;

    @Column(name = "profile_picture", columnDefinition = "LONGBLOB")
    private byte[] profilePicture;

    @Column(name = "registered_on")
    private Date registeredOn;

    @Column(name = "last_time_logged_in")
    private Date lastTimeLoggedIn;

    @Column(name = "age")
    private int age;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "born_town_id", referencedColumnName = "town_id")
    private Town bornTown;

    @ManyToOne
    @JoinColumn(name = "currently_living_in_town_id", referencedColumnName = "town_id")
    private Town currentlyLivingInTown;

    @ManyToMany
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "user_id"))
    private Set<User> friends;

    @OneToMany(mappedBy = "owner", targetEntity = Album.class)
    private Set<Album> albums;

    public User() {
        this.friends = new HashSet<>();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        if (username.length() < 4 || username.length() > 30) {
            throw new IllegalArgumentException("Username length should be between 4 and 30 symbols");
        }
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getProfilePicture() {
        return this.profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        if (profilePicture.length > 1024 * 1024) {
            throw new IllegalArgumentException("Picture too big");
        }
        this.profilePicture = profilePicture;
    }

    public Date getRegisteredOn() {
        return this.registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Date getLastTimeLoggedIn() {
        return this.lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(Date lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        if (age < 1 || age > 120){
            throw new IllegalArgumentException("Age should be between 1 and 120");
        }
        this.age = age;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }

    public Town getBornTown() {
        return this.bornTown;
    }

    public void setBornTown(Town bornTown) {
        this.bornTown = bornTown;
    }

    public Town getCurrentlyLivingInTown() {
        return this.currentlyLivingInTown;
    }

    public void setCurrentlyLivingInTown(Town currentlyLivingInTown) {
        this.currentlyLivingInTown = currentlyLivingInTown;
    }

    public Set<User> getFriends() {
        return this.friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }
}
