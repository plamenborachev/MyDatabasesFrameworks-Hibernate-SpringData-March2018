package softuni.user_system.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "town_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "bornTown",
            targetEntity = User.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<User> usersBornInTown;

    @OneToMany(mappedBy = "currentlyLivingInTown",
            targetEntity = User.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<User> usersLivingInTown;

    public Town() {
        this.usersBornInTown = new HashSet<>();
        this.usersLivingInTown = new HashSet<>();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<User> getUsersBornInTown() {
        return this.usersBornInTown;
    }

    public void setUsersBornInTown(Set<User> usersBornInTown) {
        this.usersBornInTown = usersBornInTown;
    }

    public Set<User> getUsersLivingInTown() {
        return this.usersLivingInTown;
    }

    public void setUsersLivingInTown(Set<User> usersLivingInTown) {
        this.usersLivingInTown = usersLivingInTown;
    }
}
