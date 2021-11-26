package unitTestingExercise.p01_Database;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {

    private static final Integer[] NUMBERS = {1, 2, 3, 4};

    private Database database;

    @Before
    public void prepare() throws OperationNotSupportedException {
        database = new Database(NUMBERS);
    }

    @Test
    public void testConstructorShouldCreateValidDatabase() {
        Integer[] dbElements = database.getElements();
        assertArrayEquals(dbElements, NUMBERS);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorShouldThrowsExceptionForMoreThan16Elements() throws OperationNotSupportedException {
        Integer[] largeArray = new Integer[17];
        new Database(largeArray);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorShouldThrowsExceptionForLessThan1Elements() throws OperationNotSupportedException {
        Integer[] smallArray = new Integer[0];
        new Database(smallArray);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddThrowsExceptionNullArgument() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void testAddWhenIsSuccessful() throws OperationNotSupportedException {
        database.add(3);
        Integer[] dbElements = database.getElements();
        assertEquals(NUMBERS.length + 1, dbElements.length);
        assertEquals(dbElements[dbElements.length - 1], Integer.valueOf(3));
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveShouldThrowArrayIndexException() throws OperationNotSupportedException {
        for (int i = 0; i < NUMBERS.length; i++) {
            database.remove();
        }
        database.remove();
    }

    @Test
    public void testRemoveMethodShouldRemoveLastElement() throws OperationNotSupportedException {
        database.remove();
        Integer[] dbElements = database.getElements();
        assertEquals(NUMBERS.length - 1, dbElements.length);
    }
}