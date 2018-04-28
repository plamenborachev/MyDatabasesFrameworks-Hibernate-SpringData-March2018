package soft_uni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soft_uni.models.entity.Book;
import soft_uni.models.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
