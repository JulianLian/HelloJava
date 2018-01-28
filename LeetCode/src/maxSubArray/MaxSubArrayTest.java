package maxSubArray;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaxSubArrayTest {
    MaxSubArray maxSubArray;

    @Before
    public void setUp() {
        maxSubArray = new MaxSubArray();
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, maxSubArray.maxSubArray(null));
        assertEquals(0, maxSubArray.maxSubArray(new int[]{}));
    }

    @Test
    public void testOneElementArray() {
        assertEquals(1, maxSubArray.maxSubArray(new int[]{1}));
    }

    @Test
    public void testTwoElementsArray() {
        assertEquals(3, maxSubArray.maxSubArray(new int[]{1,2}));
        assertEquals(-1, maxSubArray.maxSubArray(new int[]{-1,-2}));
        assertEquals(-1, maxSubArray.maxSubArray(new int[]{-2,-1}));
    }

    @Test
    public void testMoreElementsArray() {
        assertEquals(6, maxSubArray.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        assertEquals(-1, maxSubArray.maxSubArray(new int[]{-2,-1,-3}));
    }
}