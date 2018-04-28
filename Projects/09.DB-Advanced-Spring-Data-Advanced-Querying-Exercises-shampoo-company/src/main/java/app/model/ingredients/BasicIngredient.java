package app.model.ingredients;

import app.model.shampoos.BasicShampoo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "ingredients")
@NamedQueries({@NamedQuery(name = "BasicIngredient.deleteByName",
                            query = "DELETE FROM BasicIngredient WHERE name = :name"),
                @NamedQuery(name = "BasicIngredient.increasePrice",
                            query = "UPDATE BasicIngredient bi SET bi.price = bi.price * (1 + :percent / 100)")})
public class BasicIngredient implements Ingredient {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToMany(mappedBy = "ingredients",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<BasicShampoo> shampoos;

    public BasicIngredient(){};
    public BasicIngredient(String name, BigDecimal price){
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public List<BasicShampoo> getShampoos() {
        return this.shampoos;
    }

    @Override
    public void setShampoos(List<BasicShampoo> shampoos) {
        this.shampoos = shampoos;
    }

    // Getters and setters


}
