package app.service.impl;

import app.models.entity.Category;
import app.repository.CategoryRepository;
import app.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

    }

    @Override
    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public void addToDb(Category category) {
        this.categoryRepository.saveAndFlush(category);
    }

    @Override
    public Category findById(Long id) {
        return this.categoryRepository.getById(id);
    }

    @Override
    public long getCountOfCategories() {
        return this.categoryRepository.count();
    }

    @Override
    public String[] getAllByProductsCount() {
        return this.categoryRepository.getAllByNumberOfProducts();
    }

    @Override
    public List<Category> getAllByProductsCountOrdered() {
        return this.categoryRepository.getAllByProductsCount();
    }
}
