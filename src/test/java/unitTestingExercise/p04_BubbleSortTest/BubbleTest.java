package unitTestingExercise.p04_BubbleSortTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class BubbleTest {

    @Test
    public void testBubbleSort() {
        int[] numbers = {1, 5, 3, 56, 33, -1};
        int[] expected = {-1, 1, 3, 5, 33, 56};
        Bubble.sort(numbers);
        assertArrayEquals(expected, numbers);

    }
}