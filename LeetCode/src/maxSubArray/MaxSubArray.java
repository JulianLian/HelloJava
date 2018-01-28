package maxSubArray;

public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        if (null == nums || 0 >= nums.length) {
            return 0;
        }
        int maxResult = nums[0], curMax = (nums[0] > 0) ? nums[0] : 0;
        for (int i = 1; i < nums.length; i++) {
            curMax = curMax + nums[i];
            maxResult = Math.max(maxResult, curMax);
            curMax = (curMax > 0) ? curMax : 0;
        }
        return maxResult;
    }
}
