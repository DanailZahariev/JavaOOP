package blueOriginTest;

import examPreparation.blueOriginTest.Astronaut;
import examPreparation.blueOriginTest.Spaceship;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpaceshipTest {

    private Spaceship spaceship;
    private Astronaut astronaut;
    private Astronaut astronaut1;

    @Before
    public void setUp() {
        this.spaceship = new Spaceship("java", 10);
        this.astronaut = new Astronaut("dani", 100);
        this.astronaut1 = new Astronaut("gosho", 100);
    }

    @Test(expected = NullPointerException.class)
    public void testNameShouldFailWhenIsNull() {
        new Spaceship(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testNameShouldFailWhenIsWhiteSpace() {
        new Spaceship("   ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityWhenIsSetBelowZero() {
        new Spaceship("java", -1);
    }

    @Test
    public void testShouldReturnCorrectCapacity() {
        this.spaceship.add(astronaut);
        this.spaceship.add(astronaut1);
        assertEquals(2, spaceship.getCount());
    }

    @Test
    public void testShouldReturnCorrectName() {
        this.spaceship.add(astronaut);
        assertEquals("java", spaceship.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWhenCapacityIsFull() {
        Spaceship spaceship = new Spaceship("spaceship", 1);
        spaceship.add(astronaut);
        spaceship.add(astronaut1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWhenAstronautHaveSameName() {
        this.spaceship.add(astronaut);
        this.spaceship.add(astronaut);
    }

    @Test
    public void testWhenAddingIsCorrect() {
        this.spaceship.add(astronaut);
        assertEquals("dani", astronaut.getName());
    }

    @Test
    public void testRemoveShouldFailWhenNonExistingAstronautIsTiredToBeRemove() {
        this.spaceship.add(astronaut);
        assertFalse(this.spaceship.remove("ivan"));
    }

    @Test
    public void testRemoveShouldReturnTrueWhenCorrectAstronautIsRemoved() {
        this.spaceship.add(astronaut);
        assertTrue(this.spaceship.remove("dani"));
    }
}