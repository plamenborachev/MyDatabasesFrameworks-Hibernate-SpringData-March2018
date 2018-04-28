package app.repositories;

import app.model.enums.Size;
import app.model.ingredients.Ingredient;
import app.model.labels.Label;
import app.model.shampoos.BasicShampoo;
import app.model.shampoos.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<BasicShampoo, Long> {

    List<Shampoo> findAllBySizeOrderById(Size size);

    List<Shampoo> findAllBySizeOrLabelOrderByPriceAsc(Size size, Label label);

    List<Shampoo> findAllByPriceAfterOrderByPriceDesc(BigDecimal price);

    Long countAllByPriceLessThan(BigDecimal price);

    @Query(value = "SELECT s FROM BasicShampoo s JOIN s.ingredients i WHERE i IN :ingredients")
    List<Shampoo> findAllByIngredientsIn(@Param(value = "ingredients") List<Ingredient> ingredients);


    @Query("SELECT s FROM BasicShampoo s JOIN s.ingredients i WHERE i.size < :count")
    List<Shampoo> findAllByIngredients(@Param("count") int count);

}
