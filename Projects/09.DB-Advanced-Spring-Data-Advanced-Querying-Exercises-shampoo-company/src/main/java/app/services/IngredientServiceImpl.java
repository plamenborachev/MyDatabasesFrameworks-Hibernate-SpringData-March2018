package app.services;

import app.model.ingredients.Ingredient;
import app.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> selectsAllByNameStartingWithGivenLetters(String letters) {
        return this.ingredientRepository.findAllByNameStartsWith(letters);
    }

    @Override
    public List<Ingredient> selectsAllContainedInGivenListOrderedByPriceAsc(List<String> ingredientNames) {
        return this.ingredientRepository.findAllByNameInOrderByPriceAsc(ingredientNames);
    }

    @Override
    public List<Ingredient> selectAllWithNameInGivenList(List<String> ingredientNames) {
        return this.ingredientRepository.findAllByNameIn(ingredientNames);
    }

    @Override
    public void deleteByName(String name) {
        this.ingredientRepository.deleteByName(name);
    }

    @Override
    public void updateIngredientsByPrice(int percent) {
        this.ingredientRepository.increasePrice(percent);
    }

    @Override
    public void updateIngredientsByNames(List<String> names) {
        this.ingredientRepository.updateIngredientsByNamesInList(names);
    }
}
