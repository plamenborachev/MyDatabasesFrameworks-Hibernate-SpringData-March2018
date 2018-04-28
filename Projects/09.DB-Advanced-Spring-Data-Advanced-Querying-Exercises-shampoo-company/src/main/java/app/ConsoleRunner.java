package app;

import app.model.enums.Size;
import app.model.ingredients.Ingredient;
import app.model.labels.Label;
import app.model.shampoos.Shampoo;
import app.services.IngredientService;
import app.services.LabelService;
import app.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private BufferedReader reader;
    private final ShampooService shampooService;
    private final IngredientService ingredientService;
    private final LabelService labelService;

    @Autowired
    public ConsoleRunner(ShampooService shampooService, IngredientService ingredientService, LabelService labelService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
        this.labelService = labelService;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... strings) throws Exception {
//        Problem 1
//        selectShampoosBySize();
//        Problem 2
//        selectShampoosBySizeOrLabel();
//        Problem 3
//        selectShampoosByPrice();
//        Problem 4
//        selectIngredientsByName();
//        Problem 5
//        selectIngredientsByNames();
//        Problem 6
//        countShampoosByPrice();
//        Problem 7
//        selectShampoosByIngredients();
//        Problem 8 todo
//        selectShampoosByIngredientsCount();
//        Problem 9 todo
//        selectIngredientNameAndShampooBrandByName();
//        Problem 10
//        deleteIngredientsByName();
//        Problem 11
//        updateIngredientsPriceBy10Percent();
//        Problem 12
        updateIngredientsByNames();
    }



    public void selectShampoosBySize() throws IOException {
        Size shampooSize = Size.valueOf(reader.readLine());
        List<Shampoo> shampoos = this.shampooService.selectsAllsBySizeOrderedById(shampooSize);
        printShampoos(shampoos);
    }

    private void selectShampoosBySizeOrLabel() throws IOException {
        Size shampooSize = Size.valueOf(reader.readLine());
        long labelId = Long.parseLong(reader.readLine());
        Label label = this.labelService.getLabelById(labelId);
        List<Shampoo> shampoos = this.shampooService.selectsAllBySizeOrLabelOrderedByPriceAsc(shampooSize, label);
        printShampoos(shampoos);
    }

    private void selectShampoosByPrice() throws IOException {
        BigDecimal price = BigDecimal.valueOf(Long.parseLong(reader.readLine()));
        List<Shampoo> shampoos = this.shampooService.selectsAllHigherThanGivenPriceOrderedByPriceDesc(price);
        printShampoos(shampoos);
    }

    private void printShampoos(List<Shampoo> shampoos) {
        shampoos.forEach(sh -> System.out.println(String.format("%s %s %slv.",
                sh.getBrand(), sh.getSize(), sh.getPrice())));
    }

    private void selectIngredientsByName() throws IOException {
        String letters = reader.readLine();
        List<Ingredient> ingredients = this.ingredientService
                .selectsAllByNameStartingWithGivenLetters(letters);
        ingredients.forEach(i -> System.out.println(i.getName()));
    }

    private void selectIngredientsByNames() throws IOException {
        List<String> input = Arrays.stream(reader.readLine().split("\\s+"))
                .collect(Collectors.toList());
        List<Ingredient> ingredients = this.ingredientService
                .selectsAllContainedInGivenListOrderedByPriceAsc(input);
        ingredients.forEach(i -> System.out.println(i.getName()));
    }

    private void countShampoosByPrice() throws IOException {
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(reader.readLine()));
        Long shampoos = this.shampooService.selectAllWithPriceLowerThanGivenPrice(price);
        System.out.println(shampoos);
    }

    private void selectShampoosByIngredients() throws IOException {
        List<String> inputIngredientNames = Arrays.stream(reader.readLine().split("\\s+"))
                .collect(Collectors.toList());
        List<Ingredient> ingredients = this.ingredientService.selectAllWithNameInGivenList(inputIngredientNames);
        List<Shampoo> shampoos = this.shampooService.selectAllWithIngredientsInGivenList(ingredients);
        shampoos.forEach(s -> System.out.println(s.getBrand()));
    }

    private void selectShampoosByIngredientsCount() throws IOException {
        int count = Integer.parseInt(reader.readLine());
        List<Shampoo> shampoos = this.shampooService.selectAllWithIngredientsLessThanNumber(count);
        shampoos.forEach(s -> System.out.println(s.getBrand()));
    }

    private void selectIngredientNameAndShampooBrandByName() {

    }

    private void deleteIngredientsByName() throws IOException {
        String name = reader.readLine();
        this.ingredientService.deleteByName(name);
    }

    private void updateIngredientsPriceBy10Percent() {
        this.ingredientService.updateIngredientsByPrice(10);
    }

    private void updateIngredientsByNames() throws IOException {
        List<String> names = Arrays.stream(reader.readLine().split(";"))
                .collect(Collectors.toList());
        this.ingredientService.updateIngredientsByNames(names);
    }
}
