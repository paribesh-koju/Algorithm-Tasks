package Question_3;

public class MaximumTarget {
    public static int maxPoints(int[] a) {
        int n = a.length;
        int[] paddedTargets = new int[n + 2];
        paddedTargets[0] = paddedTargets[n + 1] = 1;
        System.arraycopy(a, 0, paddedTargets, 1, n);

        int[][] dp = new int[n + 2][n + 2];

        // Calculate maximum points for different segments of the line.
        for (int len = 1; len <= n; len++) {
            for (int left = 1; left <= n - len + 1; left++) {
                int right = left + len - 1;
                for (int i = left; i <= right; i++) {
                    // Update the maximum points using a dynamic programming approach.
                    dp[left][right] = Math.max(dp[left][right],
                            dp[left][i - 1] + paddedTargets[left - 1] * paddedTargets[i] * paddedTargets[right + 1] + dp[i + 1][right]);
                }
            }
        }

        // Return the maximum points for the entire line.
        return dp[1][n];
    }

    public static void main(String[] args) {
        int[] a = {3, 1, 5, 8};
        int result = maxPoints(a);
        System.out.println("Maximum points: " + result);
    }
    /*
Key Steps:
    Pad input array with 1's on both ends
    Use DP to calculate max points for increasing line segments
    Transition equation combines left, right splits with current target
    Return max points for full line
 */
}
