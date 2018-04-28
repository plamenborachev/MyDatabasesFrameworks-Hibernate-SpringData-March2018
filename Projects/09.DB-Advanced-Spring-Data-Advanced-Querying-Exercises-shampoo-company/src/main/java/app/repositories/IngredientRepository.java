package app.repositories;

import app.model.ingredients.BasicIngredient;
import app.model.ingredients.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<BasicIngredient, Long> {

    List<Ingredient> findAllByNameStartsWith(String letters);

    List<Ingredient> findAllByNameInOrderByPriceAsc(List<String> ingredientNames);

    List<Ingredient> findAllByNameIn(List<String> ingredientNames);

    @Modifying
    void deleteByName(@Param("name") String name);

    @Modifying
    void increasePrice(@Param("percent") int percent);

    @Modifying
    @Query("UPDATE BasicIngredient bi SET bi.price = bi.price * 1.1 WHERE bi.name IN :names")
    void updateIngredientsByNamesInList(@Param("names") List<String> names);
}
