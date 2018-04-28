package app.services;

import app.model.enums.Size;
import app.model.ingredients.Ingredient;
import app.model.shampoos.Shampoo;

import java.math.BigDecimal;
import java.util.List;

public interface IngredientService {

    List<Ingredient> selectsAllByNameStartingWithGivenLetters(String letters);

    List<Ingredient> selectsAllContainedInGivenListOrderedByPriceAsc(List<String> ingredientNames);

    List<Ingredient> selectAllWithNameInGivenList(List<String> ingredientNames);

    void deleteByName(String name);

    void updateIngredientsByPrice(int percent);

    void updateIngredientsByNames(List<String> names);
}
