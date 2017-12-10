package longestIncreasingSequence;

/**
 * Created by Julian on 2017/8/4.
 */

/**
 * Time Limit Exceed
 */
/*
public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int nums[]) {
        List<LIS> lisList = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            lisList = longestIncreasingSubsequencs(lisList, nums[i]);
        }

        int maxLen = 0;
        for (LIS lis : lisList) {
            maxLen = (maxLen < lis.getLength()) ? lis.getLength() : maxLen;
        }
        return maxLen;
    }

    private List<LIS> longestIncreasingSubsequencs(List<LIS> lisList, int num) {
        List<LIS> newLiss = new ArrayList<>();
        newLiss.add(new LIS(num, 1));
        for (LIS lis : lisList) {
            if (lis.getBiggestNum() < num)
                newLiss.add(new LIS(num, lis.getLength()+1));
        }
        newLiss.addAll(lisList);

        return newLiss;
    }

    class LIS{
        private int biggestNum = 0;
        private int length = 0;

        protected LIS(int num, int len) {
            biggestNum = num;
            length = len;
        }

        int getBiggestNum() {
            return biggestNum;
        }

        int getLength() {
            return length;
        }
    }
}
*/

/**
 * dynamic programming
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int nums[]) {
        int dp[] = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                dp[i] = (nums[i] > nums[j]) ? ((dp[i] < dp[j]+1) ? (dp[j] + 1) : dp[i]) : dp[i];
            }
        }

        int maxLen = 0;
        for (int len : dp){
            maxLen = (maxLen < len) ? len : maxLen;
        }
        return maxLen;
    }
}