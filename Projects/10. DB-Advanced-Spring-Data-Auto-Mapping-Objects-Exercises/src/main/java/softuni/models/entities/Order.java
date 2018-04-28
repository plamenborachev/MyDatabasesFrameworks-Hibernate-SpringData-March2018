package softuni.models.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User buyer;

    @ManyToMany
    @JoinTable(name = "orders_games",
    joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"),
    inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "game_id"))
    private Set<Game> products;

    public Order() {
        this.products = new HashSet<>();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getBuyer() {
        return this.buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Set<Game> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Game> products) {
        this.products = products;
    }


}
