package testDrivenDevelopment;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class InstockTest {

    private ProductStock productStock;


    @Before
    public void setUp() {
        this.productStock = new Instock();
    }

    @Test
    public void testProductContainsOrProductIsAddedCorrectly() {
        Product product = new Product("test_product", 10.99, 1);

        assertFalse(productStock.contains(product));

        productStock.add(product);
        
        assertTrue(productStock.contains(product));
    }

    @Test
    public void testCountShouldReturnZeroWhenEmptyAndNoneZeroWhenProductsAreAdded() {
        assertEquals(0, this.productStock.getCount());

        createAndAddProducts(productStock);

        assertEquals(4, this.productStock.getCount());
    }

    @Test
    public void testFindShouldReturnCorrectElement() {
        Product[] andAddProducts = createAndAddProducts(this.productStock);

        int index = andAddProducts.length - 2;

        assertEquals(andAddProducts[index].getLabel(), this.productStock.find(index).getLabel());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindShouldFailWhenIndexIsGreater() {
        int greater = createAndAddProducts(this.productStock).length;

        this.productStock.find(greater);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindShouldFailWhenIndexIsNegative() {
        createAndAddProducts(this.productStock);

        this.productStock.find(-1);
    }

    @Test
    public void testChangeQuantityShouldUpdateCorrectItemInStock() {
        Product[] addProducts = createAndAddProducts(this.productStock);

        Product product = addProducts[1];

        int newQuantity = product.getQuantity() + 10;

        this.productStock.changeQuantity(product.getLabel(), newQuantity);

        assertEquals(newQuantity, product.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityShouldFailWhenNoSuchProductInStock() {
        createAndAddProducts(this.productStock);

        Product fakeProduct = new Product("fake_product", 22.22, 1);

        this.productStock.changeQuantity(fakeProduct.getLabel(), fakeProduct.getQuantity() + 13);
    }

    @Test
    public void testGetByLabelShouldReturnTheCorrectProduct() {
        Product[] addProducts = createAndAddProducts(this.productStock);

        String expected = addProducts[1].getLabel();

        Product product = this.productStock.findByLabel(expected);
        String actual = product.getLabel();

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByLabelShouldFailWhenNoSuchProductInStock() {
        createAndAddProducts(this.productStock);
        this.productStock.findByLabel("fake_product");
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnTheCorrectNumberOfProducts() {
        Product[] addProducts = createAndAddProducts(this.productStock);

        this.productStock.add(new Product("testProduct", 3.00, 1));

        Iterable<Product> iterable = this.productStock.findFirstByAlphabeticalOrder(addProducts.length);

        int count = assertAndExtract(iterable).size();

        assertEquals(addProducts.length, count);
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnTheCorrectOrder() {
        Product[] addedProducts = createAndAddProducts(this.productStock);

        Iterable<Product> iterable = this.productStock.findFirstByAlphabeticalOrder(addedProducts.length);

        List<String> actualProducts = assertAndExtract(iterable, Product::getLabel);

        List<String> expectedProducts = Arrays.stream(addedProducts)
                .map(Product::getLabel)
                .sorted()
                .collect(Collectors.toList());

        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnEmptyCollectionWhenOutOfRange() {
        Product[] andAddProducts = createAndAddProducts(this.productStock);

        Iterable<Product> iterable = this.productStock.findFirstByAlphabeticalOrder(andAddProducts.length + 1);

        List<Product> products = assertAndExtract(iterable);

        assertTrue(products.isEmpty());
    }

    @Test
    public void testFindAllInRangeShouldReturnTheCorrectRange() {
        Product[] addProducts = createAndAddProducts(this.productStock);
        double start = addProducts[0].getPrice();
        double end = addProducts[3].getPrice();

        Iterable<Product> iterable = this.productStock.findAllInRange(start, end);

        List<Double> returnedPrice = assertAndExtract(iterable, Product::getPrice);

        double actual = returnedPrice.get(0);

        assertEquals(1, returnedPrice.size());
        assertEquals(end, actual, 0.00);

    }


    public static Product[] createAndAddProducts(ProductStock productStock) {
        Product[] products = createProducts();

        for (Product product : products) {
            productStock.add(product);
        }

        return products;
    }

    private static Product[] createProducts() {
        return new Product[]{
                new Product("test_product_2", 10.99, 1),
                new Product("test_product_3", 9.99, 1),
                new Product("test_product_4", 1.99, 1),
                new Product("test_product_1", 122.99, 1),
        };
    }

    private static List<Product> assertAndExtract(Iterable<Product> iterable) {
        return assertAndExtract(iterable, product -> product);
    }

    private static <T> List<T> assertAndExtract(Iterable<Product> iterable, Function<Product, T> mapper) {
        assertNotNull(iterable);

        List<T> result = new ArrayList<>();

        for (Product product : iterable) {
            result.add(mapper.apply(product));
        }
        return result;
    }
}