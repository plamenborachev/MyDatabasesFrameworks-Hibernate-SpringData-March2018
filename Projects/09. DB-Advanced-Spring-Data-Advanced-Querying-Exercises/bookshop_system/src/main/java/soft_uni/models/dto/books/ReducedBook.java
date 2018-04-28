package soft_uni.models.dto.books;

import soft_uni.enums.AgeRestriction;
import soft_uni.enums.EditionType;

import java.math.BigDecimal;

public interface ReducedBook {

    String getTitle();

    EditionType getEditionType();

    AgeRestriction getAgeRestriction();

    BigDecimal getPrice();
}
