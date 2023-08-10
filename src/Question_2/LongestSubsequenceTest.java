package Question_2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LongestSubsequenceTest {
    @Test
    public void testLongestSubsequence() {
        int[] nums = {8, 5, 4, 2, 1, 4, 3, 4, 3, 1, 15};
        int k = 3;
        int expected = 5; // The longest subsequence is {5, 4, 3, 1}.

        int result = LongestSubsequence.longestSubsequence(nums, k);
        assertEquals(expected, result);
    }
}
