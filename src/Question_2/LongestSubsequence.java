package Question_2;

public class LongestSubsequence {
    public static void main(String[] args) {
        int[] nums = {8, 5, 4, 2, 1, 4, 3, 4, 3, 1, 15};
        int k = 3;
        System.out.println(longestSubsequence(nums, k));
    }
    public static int longestSubsequence(int[] nums, int k) {
        int n = nums.length;
        // Array that store the length of longest subsequence ending at each index.
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            // Initialize maxLen with 1 to include the current element itself
            int maxLen = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] < nums[j] && nums[j] - nums[i] <= k) {
                    // Update maxLen if a valid element is found.
                    maxLen = Math.max(maxLen, dp[j]);
                }
            }
            //Store the calculated length in dp array
            dp[i] = maxLen + 1;
        }

        int maxLength = 0;
        for (int len : dp) {
            // Finding the maximum length among all calculated lengths.
            maxLength = Math.max(maxLength, len);
        }

        //Return the longest length
        return maxLength;
    }
}
