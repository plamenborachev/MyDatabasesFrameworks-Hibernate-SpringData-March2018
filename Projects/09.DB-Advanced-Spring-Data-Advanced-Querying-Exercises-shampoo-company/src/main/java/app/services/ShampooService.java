package app.services;

import app.model.enums.Size;
import app.model.ingredients.Ingredient;
import app.model.labels.Label;
import app.model.shampoos.Shampoo;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {

    List<Shampoo> selectsAllsBySizeOrderedById(Size size);

    List<Shampoo> selectsAllBySizeOrLabelOrderedByPriceAsc(Size size, Label label);

    List<Shampoo> selectsAllHigherThanGivenPriceOrderedByPriceDesc(BigDecimal price);

    Long selectAllWithPriceLowerThanGivenPrice(BigDecimal price);

    List<Shampoo> selectAllWithIngredientsInGivenList(List<Ingredient> ingredients);

    List<Shampoo> selectAllWithIngredientsLessThanNumber(int count);

}
