package app.repository;

import app.models.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category getById(Long id);

    @Query(value = "SELECT c FROM Category c")
    List<Category> getAllByProductsCount();

    @Query(value = "SELECT c.name, COUNT(p.product_id), AVG(p.price), SUM(p.price)\n" +
            "FROM categories AS c\n" +
            "JOIN categories_products AS cp ON c.category_id = cp.category_id\n" +
            "JOIN products AS p ON cp.product_id = p.product_id\n" +
            "GROUP BY c.category_id\n" +
            "ORDER BY COUNT(p.product_id) DESC", nativeQuery = true)
    String[] getAllByNumberOfProducts();

}
