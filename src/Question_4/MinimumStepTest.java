package Question_4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinimumStepTest {
    @Test
    public void testMinSteps() {
        int N = 3;
        int[][] prerequisites = {{1, 3}, {2, 3}};
        int expectedResult = 2; // The minimum steps required to complete the tasks.

        int result = MinimumStep.minSteps(N, prerequisites);
        assertEquals(expectedResult, result);
    }
}
