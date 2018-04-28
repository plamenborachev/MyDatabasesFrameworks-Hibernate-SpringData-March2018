package app.service.api;

import app.models.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    void addToDb(Category category);

    Category findById(Long id);

    long getCountOfCategories();

    String[] getAllByProductsCount();

    List<Category> getAllByProductsCountOrdered();

}
