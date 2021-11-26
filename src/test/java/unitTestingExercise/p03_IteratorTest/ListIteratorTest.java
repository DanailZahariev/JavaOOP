package unitTestingExercise.p03_IteratorTest;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class ListIteratorTest {

    private ListIterator listIterator;
    private static final String[] NAMES = new String[]{"Danail", "Java"};

    @Before
    public void prepare() throws OperationNotSupportedException {
        listIterator = new ListIterator(NAMES);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorShouldThrowException() throws OperationNotSupportedException {
        new ListIterator(null);
    }

    @Test
    public void testConstructorShouldCreateListIT() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator("Danail");
    }

    @Test
    public void testHasNext() {
        assertTrue(listIterator.hasNext());
        listIterator.move();
        assertFalse(listIterator.hasNext());
    }

    @Test
    public void testMove() {
        listIterator.move();
        listIterator.move();
    }

    @Test(expected = IllegalStateException.class)
    public void testPrintWithException() throws OperationNotSupportedException {
        listIterator = new ListIterator();
        listIterator.print();
    }

    @Test
    public void testPrintSuccess() {
        int index = 0;
        while (listIterator.hasNext()) {
            assertEquals(NAMES[index], listIterator.print());
            index++;
            listIterator.move();
        }
    }
}