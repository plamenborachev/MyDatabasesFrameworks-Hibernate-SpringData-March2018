package soft_uni.models.dto.books;

import soft_uni.enums.AgeRestriction;
import soft_uni.enums.EditionType;

import java.math.BigDecimal;

public class ReducedBookImpl implements ReducedBook {

    private String title;
    private EditionType editionType;
    private AgeRestriction ageRestriction;
    private BigDecimal price;

    public ReducedBookImpl(String title, EditionType editionType, AgeRestriction ageRestriction, BigDecimal price) {
        this.title = title;
        this.editionType = editionType;
        this.ageRestriction = ageRestriction;
        this.price = price;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public EditionType getEditionType() {
        return this.editionType;
    }

    @Override
    public AgeRestriction getAgeRestriction() {
        return this.ageRestriction;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
