package softuni.models.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "trailer")
    private String trailer;

    @Column(name = "image_thumbnail")
    private String imageThumbnail;

    @Column(name = "size", columnDefinition = "DECIMAL(10,1)")
    private double size;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description", columnDefinition = "TEXT")
    @Min(20)
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToMany(mappedBy = "games", targetEntity = User.class)
    private Set<User> users;

    @ManyToMany(mappedBy = "products", targetEntity = Order.class)
    private Set<Order> orders;

    public Game() {
        this.users = new HashSet<>();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        if (!Character.isUpperCase(title.charAt(0))){
            throw new IllegalArgumentException("Title has to begin with uppercase letter");
        }
        if (title.length() < 3 || title.length() > 100){
            throw new IllegalArgumentException("Title should has length between 3 and 100 symbols (inclusive");
        }
        this.title = title;
    }

    public String getTrailer() {
        return this.trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImageThumbnail() {
        return this.imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        if (imageThumbnail.startsWith("http://")
                || imageThumbnail.startsWith("https://")){
            throw new IllegalArgumentException("Invalid Thumbnail URL");
        }
        this.imageThumbnail = imageThumbnail;
    }

    public double getSize() {
        return this.size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
