package app.terminal;

import app.models.dto.binding.*;
import app.models.dto.view.category.CategoriesByProductsDto;
import app.models.dto.view.category.CategoryByProductsDto;
import app.io.DTOConvertUtil;
import app.models.dto.view.product.ProductInRangeDto;
import app.models.dto.view.product.query4.ProductSoldByUserDto;
import app.models.dto.view.product.ProductsInRangeDto;
import app.models.dto.view.product.query4.ProductsSoldByUserDto;
import app.models.dto.view.user.UserSoldProductsDto;
import app.models.dto.view.user.query4.UserWithSoldProductsDto;
import app.models.dto.view.user.UsersSoldProductsDto;
import app.models.dto.view.user.query4.UsersWithSoldProductsDto;
import app.models.entity.Category;
import app.models.entity.Product;
import app.models.entity.User;
import app.io.serialize.JsonParser;
import app.io.serialize.XmlParser;
import app.service.api.CategoryService;
import app.service.api.ProductService;
import app.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private static final String FILE_INPUT_JSON_USERS = "/files/input/json/users.json";
    private static final String FILE_INPUT_JSON_CATEGORIES = "/files/input/json/categories.json";
    private static final String FILE_INPUT_JSON_PRODUCTS = "/files/input/json/products.json";

    private static final String FILE_OUTPUT_JSON_PRODUCTS_IN_RANGE
            = "src/main/resources/files/output/json/products-in-range.json";
    private static final String FILE_OUTPUT_JSON_USERS_SOLD_PRODUCTS
            = "src/main/resources/files/output/json/users-sold-products.json";
    private static final String FILE_OUTPUT_JSON_USERS_CATEGORIES_BY_PRODUCTS
            = "src/main/resources/files/output/json/categories-by-products.json";
    private static final String FILE_OUTPUT_JSON_USERS_AND_PRODUCTS
            = "src/main/resources/files/output/json/users-and-products.json";

    private static final String FILE_INPUT_XML_USERS = "/files/input/xml/users.xml";
    private static final String FILE_INPUT_XML_CATEGORIES = "/files/input/xml/categories.xml";
    private static final String FILE_INPUT_XML_PRODUCTS = "/files/input/xml/products.xml";

    private static final String FILE_OUTPUT_XML_PRODUCTS_IN_RANGE
            = "src/main/resources/files/output/xml/products-in-range.xml";
    private static final String FILE_OUTPUT_XML_USERS_SOLD_PRODUCTS
            = "src/main/resources/files/output/xml/users-sold-products.xml";
    private static final String FILE_OUTPUT_XML_CATEGORIES_BY_PRODUCTS =
            "src/main/resources/files/output/xml/categories-by-products.xml";
    private static final String FILE_OUTPUT_XML_USERS_AND_PRODUCTS = "src/main/resources/files/output/xml/users-and-products.xml";

    private final JsonParser jsonParser;
    private final XmlParser xmlParser;
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private Random random;

    @Autowired
    public Terminal(JsonParser jsonParser, XmlParser xmlParser, UserService userService, ProductService productService, CategoryService categoryService) {
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.random = new Random();
    }


    @Override
    public void run(String... strings) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

//        1. Seed the Database

        seedUsersXml();
        seedCategoriesXml();
        seedProductsXml();

        seedUsersJson();
        seedCategoriesJson();
        seedProductsJson();

//        2. Query and Export Data

//        Query 1 - Products In Range

//        productsInPriceRangeJson(BigDecimal.valueOf(Long.parseLong(reader.readLine())),
//                BigDecimal.valueOf(Long.parseLong(reader.readLine())));

//        productsInPriceRangeXml(BigDecimal.valueOf(500L), BigDecimal.valueOf(1000L));

//        Query 2 - Successfully Sold Products

//        successfullySoldProductsJson();
//        successfullySoldProductsXml();

//        Query 3 - Categories By Products Count

//        categoriesByProductsCountJson();
//        categoriesByProductsCountXml();

//        Query 4 - Users and Products

//        usersAndProductsJson();
//        usersAndProductsXml();
    }

    private void usersAndProductsXml() {
        List<User> users = this.userService.getAllWithAtLeastOneSoldProduct();
        List<UserWithSoldProductsDto> userWithSoldProductsDtos = new LinkedList<>();
        for (User user : users) {
            List<ProductSoldByUserDto> productSoldByUserDtos
                    = DTOConvertUtil.convert(user.getProductsSold(), ProductSoldByUserDto.class);
            ProductsSoldByUserDto productsSoldByUserDto
                    = new ProductsSoldByUserDto(productSoldByUserDtos);
            UserWithSoldProductsDto userWithSoldProductsDto
                    = DTOConvertUtil.convert(user, UserWithSoldProductsDto.class);
            userWithSoldProductsDto.setProductsSoldByUserDto(productsSoldByUserDto);
            userWithSoldProductsDtos.add(userWithSoldProductsDto);
        }
        UsersWithSoldProductsDto usersWithSoldProductsDto = new UsersWithSoldProductsDto(userWithSoldProductsDtos);
        this.xmlParser.serialize(usersWithSoldProductsDto, FILE_OUTPUT_XML_USERS_AND_PRODUCTS);
    }

    private void categoriesByProductsCountXml() {
        String[] allByProductsCount = this.categoryService.getAllByProductsCount();
        List<CategoryByProductsDto> categoryByProductsDtos = new LinkedList<>();
        for (String s : allByProductsCount) {
            String[] tokens = s.split(",");
            CategoryByProductsDto categoryByProductsDto
                    = new CategoryByProductsDto(tokens[0],
                    Integer.parseInt(tokens[1]),
                    BigDecimal.valueOf(Double.parseDouble(tokens[2])),
                    BigDecimal.valueOf(Double.parseDouble(tokens[3])));
            categoryByProductsDtos.add(categoryByProductsDto);
        }
        CategoriesByProductsDto categoriesByProductsDto = new CategoriesByProductsDto();
        categoriesByProductsDto.setCategoryByProductsDtos(categoryByProductsDtos);
        this.xmlParser.serialize(categoriesByProductsDto, FILE_OUTPUT_XML_CATEGORIES_BY_PRODUCTS);
    }

    private void successfullySoldProductsXml() {
        List<User> users = this.userService.getAllWithAtLeastOneSoldItemWithBuyer();
        List<UserSoldProductsDto> userSoldProductsDtos = new LinkedList<>();
        for (User user : users) {
            Set<Product> productsWithBuyer = user.getProductsSold().stream()
                    .filter(p -> p.getBuyer() != null)
                    .collect(Collectors.toSet());
            user.setProductsSold(productsWithBuyer);
            UserSoldProductsDto userSoldProductsDto = DTOConvertUtil
                    .convert(user, UserSoldProductsDto.class);
            userSoldProductsDtos.add(userSoldProductsDto);
        }
        UsersSoldProductsDto usersSoldProductsDto = new UsersSoldProductsDto();
        usersSoldProductsDto.setUserSoldProductsDtos(userSoldProductsDtos);
        this.xmlParser.serialize(usersSoldProductsDto, FILE_OUTPUT_XML_USERS_SOLD_PRODUCTS);
    }

    private void productsInPriceRangeXml(BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> products = this.productService.getAllProductsInPriceRangeWithoutBuyer
                (minPrice, maxPrice);
        List<ProductInRangeDto> productInRangeDtoList = new LinkedList<>();
        for (Product product : products) {
            ProductInRangeDto productInRangeDto = DTOConvertUtil.convert(product, ProductInRangeDto.class);
            productInRangeDto.setSeller(String.format("%s %s",
                    product.getSeller().getFirstName() == null ? "" : product.getSeller().getFirstName(),
                    product.getSeller().getLastName()).trim());
            productInRangeDtoList.add(productInRangeDto);
        }
        ProductsInRangeDto productsInRangeDto = new ProductsInRangeDto();
        productsInRangeDto.setProductInRangeDtos(productInRangeDtoList);
        this.xmlParser.serialize(productsInRangeDto, FILE_OUTPUT_XML_PRODUCTS_IN_RANGE);
    }

    private void seedProductsXml() {
        ProductsDto productsDto = this.xmlParser.deserialize(ProductsDto.class, FILE_INPUT_XML_PRODUCTS);
        List<Product> products = DTOConvertUtil.convert(productsDto.getProductDtos(), Product.class);
        List<User> users = this.userService.getAll();
        List<Category> categories = this.categoryService.getAll();
        for (Product product : products) {
            User buyer = users.get(this.random.nextInt(users.size()));
            User seller = users.get(this.random.nextInt(users.size()));

            if (buyer == seller || this.random.nextInt() % 3 == 0) {
                product.setBuyer(null);
            } else {
                product.setBuyer(buyer);
            }
            product.setSeller(seller);

            Set<Category> categoriesOfCurrentProduct = new HashSet<>();
            for (int i = 0; i < this.random.nextInt(categories.size()) + 1; i++) {
                categoriesOfCurrentProduct
                        .add(categories.get(random.nextInt(categories.size())));
            }
            product.setCategories(categoriesOfCurrentProduct);

            this.productService.addToDb(product);
        }
    }

    private void seedCategoriesXml() {
        CategoriesDto categoriesDto = this.xmlParser.deserialize(CategoriesDto.class, FILE_INPUT_XML_CATEGORIES);
        List<Category> categories = DTOConvertUtil.convert(categoriesDto.getCategoryDtos(), Category.class);
        for (Category category : categories) {
            this.categoryService.addToDb(category);
        }
    }

    private void seedUsersXml() {
        UsersDto usersDto = this.xmlParser.deserialize(UsersDto.class, FILE_INPUT_XML_USERS);
        List<User> users = DTOConvertUtil.convert(usersDto.getUsers(), User.class);
        for (User user : users) {
            this.userService.addToDb(user);
        }
    }

    private void usersAndProductsJson() {
        List<User> users = this.userService.getAllWithAtLeastOneSoldProduct();
        List<UserWithSoldProductsDto> userWithSoldProductsDtos = new LinkedList<>();
        for (User user : users) {
            List<ProductSoldByUserDto> productSoldByUserDtos
                    = DTOConvertUtil.convert(user.getProductsSold(), ProductSoldByUserDto.class);
            ProductsSoldByUserDto productsSoldByUserDto
                    = new ProductsSoldByUserDto(productSoldByUserDtos);
            UserWithSoldProductsDto userWithSoldProductsDto
                    = DTOConvertUtil.convert(user, UserWithSoldProductsDto.class);
            userWithSoldProductsDto.setProductsSoldByUserDto(productsSoldByUserDto);
            userWithSoldProductsDtos.add(userWithSoldProductsDto);
        }
        UsersWithSoldProductsDto usersWithSoldProductsDto = new UsersWithSoldProductsDto(userWithSoldProductsDtos);
        this.jsonParser.serialize(usersWithSoldProductsDto, FILE_OUTPUT_JSON_USERS_AND_PRODUCTS);
    }

    private void categoriesByProductsCountJson() throws IOException {
        String[] allByProductsCount = this.categoryService.getAllByProductsCount();
        List<CategoryByProductsDto> categoryByProductsDtos = new LinkedList<>();
        for (String s : allByProductsCount) {
            String[] tokens = s.split(",");
            CategoryByProductsDto categoryByProductsDto
                    = new CategoryByProductsDto(tokens[0],
                    Integer.parseInt(tokens[1]),
                    BigDecimal.valueOf(Double.parseDouble(tokens[2])),
                    BigDecimal.valueOf(Double.parseDouble(tokens[3])));
            categoryByProductsDtos.add(categoryByProductsDto);
        }
        this.jsonParser.serialize(categoryByProductsDtos,
                FILE_OUTPUT_JSON_USERS_CATEGORIES_BY_PRODUCTS);

//        List<Category> categories = this.categoryService.getAll();
//        categories.sort((c1, c2) -> Integer.compare(c2.getProducts().size(), c1.getProducts().size()));
//
//        TypeMap<Category, CategoryByProductsDto> typeMap = this.modelMapper.createTypeMap(Category.class,
//                CategoryByProductsDto.class);
//        typeMap.addMappings(m -> m.map(Category::getName, CategoryByProductsDto::setCategory));
//        typeMap.addMappings(m -> m.map(src -> src.getProducts().size(), CategoryByProductsDto::setProductsCount));
//        typeMap.addMappings(m -> m.map(src ->
//            src.getProducts().stream().mapToDouble(p -> p.getPrice().doubleValue()).average()
//        , CategoryByProductsDto::setAveragePrice));
//        typeMap.addMappings(m -> m.map(src ->
//            src.getProducts().stream().mapToDouble(p -> p.getPrice().doubleValue()).sum()
//        , CategoryByProductsDto::setTotalRevenue));
//
//        List<CategoryByProductsDto> categoryByProductsDtos = new LinkedList<>();
//
//        for (Category category : categories) {
//            CategoryByProductsDto categoryByProductsDto = typeMap.map(category);
//            categoryByProductsDtos.add(categoryByProductsDto);
//        }
//        this.jsonParser.exportJson(categoryByProductsDtos,
//                FILE_OUTPUT_JSON_USERS_CATEGORIES_BY_PRODUCTS);
    }

    private void successfullySoldProductsJson() throws IOException {
        List<User> users = this.userService.getAllWithAtLeastOneSoldItemWithBuyer();
        List<UserSoldProductsDto> userSoldProductsDtos = new LinkedList<>();
        for (User user : users) {
            Set<Product> productsWithBuyer = user.getProductsSold().stream()
                    .filter(p -> p.getBuyer() != null)
                    .collect(Collectors.toSet());
            user.setProductsSold(productsWithBuyer);
            UserSoldProductsDto userSoldProductsDto = DTOConvertUtil
                    .convert(user, UserSoldProductsDto.class);
            userSoldProductsDtos.add(userSoldProductsDto);
        }
        this.jsonParser.serialize(userSoldProductsDtos, FILE_OUTPUT_JSON_USERS_SOLD_PRODUCTS);
    }

    private void productsInPriceRangeJson(BigDecimal minPrice, BigDecimal maxPrice) throws IOException {
        List<Product> products = this.productService.getAllProductsInPriceRangeWithoutBuyer
                (minPrice, maxPrice);
        List<ProductInRangeDto> productInRangeDtos = new ArrayList<>();
        for (Product product : products) {
            ProductInRangeDto productInRangeDto = DTOConvertUtil.convert(product, ProductInRangeDto.class);
            productInRangeDto.setSeller(String.format("%s %s",
                    product.getSeller().getFirstName() != null ? product.getSeller().getFirstName() : "",
                    product.getSeller().getLastName())
                    .trim());
            productInRangeDtos.add(productInRangeDto);
        }
        this.jsonParser.serialize(productInRangeDtos, FILE_OUTPUT_JSON_PRODUCTS_IN_RANGE);
    }

    private void seedProductsJson() throws IOException {

        List<User> users = this.userService.getAll();
        List<Category> categories = this.categoryService.getAll();

        ProductDto[] productDtos = this.jsonParser
                .deserialize(ProductDto[].class, FILE_INPUT_JSON_PRODUCTS);

        for (ProductDto productDto : productDtos) {
            Product product = DTOConvertUtil.convert(productDto, Product.class);

            User buyer = users.get(this.random.nextInt(users.size()));
            User seller = users.get(this.random.nextInt(users.size()));

            if (buyer == seller || this.random.nextInt() % 3 == 0) {
                product.setBuyer(null);
            } else {
                product.setBuyer(buyer);
            }
            product.setSeller(seller);

            Set<Category> categoriesOfCurrentProduct = new HashSet<>();
            for (int i = 0; i < this.random.nextInt(categories.size()) + 1; i++) {
                categoriesOfCurrentProduct
                        .add(categories.get(random.nextInt(categories.size())));
            }
            product.setCategories(categoriesOfCurrentProduct);

            this.productService.addToDb(product);
        }
    }

    private void seedCategoriesJson() throws IOException {
        CategoryDto[] categoryDtos = this.jsonParser
                .deserialize(CategoryDto[].class, FILE_INPUT_JSON_CATEGORIES);
        for (CategoryDto categoryDto : categoryDtos) {
            Category category = DTOConvertUtil.convert(categoryDto, Category.class);
            this.categoryService.addToDb(category);
        }
    }

    private void seedUsersJson() throws IOException {
        UserDto[] userDtos = this.jsonParser
                .deserialize(UserDto[].class, FILE_INPUT_JSON_USERS);
        for (UserDto userDto : userDtos) {
            User user = DTOConvertUtil.convert(userDto, User.class);
            this.userService.addToDb(user);
        }
    }
}
