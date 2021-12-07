package shopAndGoodsTest;

import examPreparation.shopAndGoodsTest.Goods;
import examPreparation.shopAndGoodsTest.Shop;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class ShopTest {

    private Shop shop;

    @Before
    public void setUp() {
        this.shop = new Shop();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetShelvesShouldReturnUnmodifiableCollection() {
        shop.getShelves().clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodShouldFailForNoneExistingShelve() throws OperationNotSupportedException {
        shop.addGoods("invalid_shelves", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodShouldFailForExistingProduct() throws OperationNotSupportedException {
        Goods goods = new Goods("Meat", "meat1");
        shop.addGoods("Shelves1", goods);
        shop.addGoods("Shelves1", goods);
    }

    @Test
    public void testAddGoodsShouldReturnCorrectMessage() throws OperationNotSupportedException {
        Goods goods = new Goods("Meat", "meat1");
        String expected = "Goods: meat1 is placed successfully!";
        String actual = shop.addGoods("Shelves1", goods);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodShouldFailForNoneExistingShelve() throws OperationNotSupportedException {
        shop.removeGoods("invalid_shelves", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodShouldFailForDiffGoodOnSameShelves() throws OperationNotSupportedException {
        Goods goods = new Goods("Meat", "meat1");
        Goods goods1 = new Goods("Meat2", "meat2");
        shop.addGoods("Shelves1", goods);
        shop.removeGoods("Shelves1", goods1);
    }

    @Test
    public void testRemoveGoodsShouldReturnCorrectMessage() throws OperationNotSupportedException {
        Goods goods = new Goods("Meat", "meat1");
        shop.addGoods("Shelves1", goods);
        String expected = "Goods: meat1 is removed successfully!";
        String actual = shop.removeGoods("Shelves1", goods);
        assertEquals(expected, actual);
    }
    @Test
    public void testRemoveGoodsShouldSetValueToNull() throws OperationNotSupportedException {
        Goods goods = new Goods("Meat", "meat1");
        shop.addGoods("Shelves1", goods);
        shop.removeGoods("Shelves1", goods);
        Goods goods1 = shop.getShelves().get("Shelves1");
        assertNull(goods1);
    }
}