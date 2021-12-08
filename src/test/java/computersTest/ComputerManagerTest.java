package computersTest;

import examPreparation.computersTest.Computer;
import examPreparation.computersTest.ComputerManager;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ComputerManagerTest {

    private ComputerManager computerManager;
    private Computer computer;
    private Computer computer1;

    @Before
    public void setUp() {
        this.computerManager = new ComputerManager();
        this.computer = new Computer("Apple", "MacBook", 2300);
        this.computer1 = new Computer("Asus", "Zen", 2000);
    }

    @Test
    public void testGettingCorrectComputersSize() {
        this.computerManager.addComputer(computer);
        this.computerManager.addComputer(computer1);
        assertEquals(2, this.computerManager.getCount());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetComputers() {
        computerManager.getComputers().remove(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWhenAddingNull() {
        computerManager.addComputer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenAddingSameComputer() {
        this.computerManager.addComputer(computer);
        this.computerManager.addComputer(computer);
    }

    @Test
    public void testAdding() {
        this.computerManager.addComputer(computer);
        assertEquals(1, this.computerManager.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputersWhenManufacturerIsNull() {
        this.computerManager.getComputer(null, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputersWhenMModelIsNull() {
        this.computerManager.getComputer("null", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNonExistingComputer() {
        this.computerManager.addComputer(computer);
        this.computerManager.getComputer("test", "test");
    }

    @Test
    public void testGettingTheCorrectComputer() {
        this.computerManager.addComputer(computer);
        this.computerManager.addComputer(computer1);
        Computer returned = this.computerManager.getComputer(this.computer.getManufacturer(), this.computer.getModel());
        assertNotNull(returned);
        assertEquals(computer.getManufacturer(),computer.getManufacturer());
        assertEquals(computer.getModel(),computer.getModel());
    }


    @Test
    public void testRemoveComputer() {
        this.computerManager.addComputer(computer);
        this.computerManager.addComputer(computer1);
        this.computerManager.removeComputer("Apple", "MacBook");
        assertEquals(1, this.computerManager.getCount());
    }

    @Test
    public void testGetComputerByManufacturer() {
        this.computerManager.addComputer(computer);
        List<Computer> computersByManufacturer = this.computerManager.getComputersByManufacturer(computer.getManufacturer());
        assertNotNull(computersByManufacturer);
        assertEquals(computersByManufacturer.get(0).getManufacturer(),computer.getManufacturer());
    }
}