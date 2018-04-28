package models.batch;

import models.interfaces.Batch;
import models.shampoo.BasicShampoo;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "batches")
public class ProductionBatch implements Batch{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "batch",
            targetEntity = BasicShampoo.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BasicShampoo> basicShampoos;

    public ProductionBatch() {
    }

    public ProductionBatch(Set<BasicShampoo> basicShampoos) {
        this.basicShampoos = basicShampoos;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<BasicShampoo> getBasicShampoos() {
        return this.basicShampoos;
    }

    public void setBasicShampoos(Set<BasicShampoo> basicShampoos) {
        this.basicShampoos = basicShampoos;
    }
}
