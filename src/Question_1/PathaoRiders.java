package Question_1;

import java.util.Arrays;

public class PathaoRiders {
    public static void main(String[] args) {
        int[] ratings = {1, 0, 2};
        int minimumCoins = minimumCoins(ratings);
        System.out.println("Minimum number of coins required: " + minimumCoins);
    }
    public static int minimumCoins(int[] ratings) {
        int n = ratings.length;
        // Initialize coins array with 1 coin for each position.
        int[] coins = new int[n];
        Arrays.fill(coins, 1);

        // Iterate forward to adjust coins based on increasing ratings.
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                coins[i] = coins[i - 1] + 1;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && coins[i] <= coins[i + 1]) {
                // Increase coins if rating is higher than next and not already greater.
                coins[i] = coins[i + 1] + 1;
            }
        }

        // Calculate the total coins to be needed.
        int totalCoins = 0;
        for (int coinsCount : coins) {
            totalCoins += coinsCount;
        }

        return totalCoins;
    }
}
