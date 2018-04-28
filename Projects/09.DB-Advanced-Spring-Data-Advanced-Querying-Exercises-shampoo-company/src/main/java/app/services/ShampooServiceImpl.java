package app.services;

import app.model.enums.Size;
import app.model.ingredients.Ingredient;
import app.model.labels.BasicLabel;
import app.model.labels.Label;
import app.model.shampoos.Shampoo;
import app.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;

    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> selectsAllsBySizeOrderedById(Size size) {
        return this.shampooRepository.findAllBySizeOrderById(size);
    }

    @Override
    public List<Shampoo> selectsAllBySizeOrLabelOrderedByPriceAsc(Size size, Label label) {
        return this.shampooRepository.findAllBySizeOrLabelOrderByPriceAsc(size, label);
    }

    @Override
    public List<Shampoo> selectsAllHigherThanGivenPriceOrderedByPriceDesc(BigDecimal price) {
        return this.shampooRepository.findAllByPriceAfterOrderByPriceDesc(price);
    }

    @Override
    public Long selectAllWithPriceLowerThanGivenPrice(BigDecimal price) {
        return this.shampooRepository.countAllByPriceLessThan(price);
    }

    @Override
    public List<Shampoo> selectAllWithIngredientsInGivenList(List<Ingredient> ingredients) {
        return this.shampooRepository.findAllByIngredientsIn(ingredients);
    }

    @Override
    public List<Shampoo> selectAllWithIngredientsLessThanNumber(int count) {
        return this.shampooRepository.findAllByIngredients(count);
    }
}
