package Question_2;

import java.util.ArrayList;
import java.util.List;

public class RandomlistedPort {
    private List<Integer> whitelistedPorts;

    public RandomlistedPort(int k, int[] blacklisted_ports) {
        whitelistedPorts = new ArrayList<>();

        // Generate the list of whitelisted ports
        for (int i = 0; i < k; i++) {
            // Add ports up to k to the whitelist.
            whitelistedPorts.add(i);
        }
        for (int port : blacklisted_ports) {
            //Remove ports from the whitelistedPorts
            whitelistedPorts.remove(Integer.valueOf(port));
        }
    }

    public int get() {
        int randomIndex = (int) (Math.random() * whitelistedPorts.size());
        // Return the port at the random index.
        return whitelistedPorts.get(randomIndex);
    }

    public static void main(String[] args) {
        int k = 7;
        int[] blacklisted_ports = {2, 3, 5};
        RandomlistedPort randomPort = new RandomlistedPort(k, blacklisted_ports);

        System.out.println(randomPort.get()); // Output: Random whitelisted port between 0 and 6
        System.out.println(randomPort.get()); // Output: Random whitelisted port between 0 and 6
    }
}
