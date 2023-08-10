package Question_1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PathaoRidersTest {
    @Test
    public void testMinimumCoins() {
        int[] ratings = {1, 0, 2};
        int expectedMinimumCoins = 5; // The minimum number of coins required based on the given ratings.

        int result = PathaoRiders.minimumCoins(ratings);
        assertEquals(expectedMinimumCoins, result);
    }
}
