package soft_uni.models.entity;

import soft_uni.enums.AgeRestriction;
import soft_uni.enums.EditionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@NamedStoredProcedureQuery(name = "usp_select_total_number_of_books_by_author",
                            procedureName = "BookRepository.usp_select_total_number_of_books_by_author",
                            parameters = {@StoredProcedureParameter(mode = ParameterMode.IN,
                                            name = "first_name", type = String.class),
                                            @StoredProcedureParameter(mode = ParameterMode.IN,
                                            name = "last_name", type = String.class),
                                            @StoredProcedureParameter(mode = ParameterMode.OUT,
                                            name = "book_count", type = Integer.class)})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "edition_type", nullable = false)
    @Enumerated
    private EditionType editionType;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "copies", nullable = false)
    private int copies;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "age_restriction", nullable = false)
    @Enumerated
    private AgeRestriction ageRestriction;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    private Author author;

    @ManyToMany
    @JoinTable(name = "books_categories",
    joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private Set<Category> categories;

    public Book() {
        this.categories = new HashSet<>();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return this.editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCopies() {
        return this.copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public AgeRestriction getAgeRestriction() {
        return this.ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
