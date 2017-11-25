package longestIncreasingSequence;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Julian on 2017/8/4.
 */
public class LongestIncreasingSubsequenceTest {
    @Test
    public void lengthOfLIS() throws Exception {
        LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();
        assertEquals(0, solution.lengthOfLIS(new int[]{}));
        assertEquals(1, solution.lengthOfLIS(new int[]{9}));
        assertEquals(2, solution.lengthOfLIS(new int[]{2,4}));
        assertEquals(1, solution.lengthOfLIS(new int[]{2,1}));
        assertEquals(1, solution.lengthOfLIS(new int[]{3,2,1}));
        assertEquals(3, solution.lengthOfLIS(new int[]{2,1,7,5,6}));
        assertEquals(4, solution.lengthOfLIS(new int[]{10,9,2,4,3,7,101,18}));
        assertEquals(6, solution.lengthOfLIS(new int[]{1,3,6,7,9,4,10,5,6}));
    }

}