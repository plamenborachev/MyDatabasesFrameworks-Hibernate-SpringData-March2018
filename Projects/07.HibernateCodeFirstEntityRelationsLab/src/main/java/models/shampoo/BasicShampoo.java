package models.shampoo;

import models.batch.ProductionBatch;
import models.ingredient.BasicIngredient;
import models.interfaces.Shampoo;
import models.label.BasicLabel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "shampoos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "shampoo_type", discriminatorType = DiscriminatorType.STRING)
public abstract class BasicShampoo implements Shampoo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Basic
    private String brand;

    @Basic
    private BigDecimal price;

    @Enumerated
    private Size size;

    @OneToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "label", referencedColumnName = "id")
    private BasicLabel label;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "shampoos_ingredients",
    joinColumns = @JoinColumn(name = "shampoo_id",
    referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "ingredient_id",
    referencedColumnName = "id"))
    private Set<BasicIngredient> ingredients;

    @ManyToOne(optional = false)
    @JoinColumn(name = "batch_id", referencedColumnName = "id")
    private ProductionBatch batch;

    public BasicShampoo() {
    }

    public BasicShampoo(BasicLabel label) {
        this.label = label;
    }

    public BasicShampoo(String brand, BigDecimal price, Size size, BasicLabel label) {
        this.brand = brand;
        this.price = price;
        this.size = size;
        this.label = label;
        this.ingredients = new LinkedHashSet<BasicIngredient>();
        this.batch = new ProductionBatch();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Size getSize() {
        return this.size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public BasicLabel getLabel() {
        return this.label;
    }

    public void setLabel(BasicLabel label) {
        this.label = label;
    }

    public Set<BasicIngredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(Set<BasicIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ProductionBatch getBatch() {
        return this.batch;
    }

    public void setBatch(ProductionBatch batch) {
        this.batch = batch;
    }
}
