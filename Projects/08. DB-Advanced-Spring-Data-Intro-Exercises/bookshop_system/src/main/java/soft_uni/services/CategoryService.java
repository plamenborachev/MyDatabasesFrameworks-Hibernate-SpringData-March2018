package soft_uni.services;

import soft_uni.models.entity.Author;
import soft_uni.models.entity.Category;

import java.util.List;

public interface CategoryService {

    void saveCategories(List<Category> categories);

    List<Category> getAll();
}

