package farmvilleTest;

import examPreparation.farmvilleTest.Animal;
import examPreparation.farmvilleTest.Farm;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FarmTest {

    private Farm farm;
    private Animal animal;
    private Animal animal2;

    @Before
    public void setUp() {
        this.farm = new Farm("java", 10);
        this.animal = new Animal("pig", 50);
        this.animal2 = new Animal("cow", 40);

    }

    @Test(expected = NullPointerException.class)
    public void testSetNullName() {
        Farm farm = new Farm(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testWhiteSpaceName() {
        Farm farm = new Farm("    ", 10);
    }

    @Test
    public void testCorrectName() {
        assertEquals("java", farm.getName());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCapacityIsNegative() {
        Farm farm = new Farm("test", -1);
    }

    @Test
    public void testCorrectCapacity() {
        assertEquals(10, farm.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenCapacityIsFullShouldThrowException() {
        Farm farm = new Farm("farm", 1);
        farm.add(animal);
        farm.add(animal2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMethodAnimalExist() {
        this.farm.add(animal);
        this.farm.add(animal);
    }

    @Test
    public void testCorrectFarmCount() {
        this.farm.add(animal);
        this.farm.add(animal2);
        assertEquals(2, farm.getCount());
    }

    @Test
    public void testRemoveAnimalShouldReturnFalse() {
        this.farm.add(animal);
        this.farm.add(animal2);
        assertFalse(this.farm.remove("dog"));
    }

    @Test
    public void testRemoveAnimalShouldReturnTrue() {
        this.farm.add(animal);
        this.farm.add(animal2);
        assertTrue(this.farm.remove(animal.getType()));
    }
}