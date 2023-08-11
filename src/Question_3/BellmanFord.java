package Question_3;

import java.util.*;

class Edge { // used for storing information of edges
    int source, destination, weight;

    Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}
// Bellman-Ford algorithm to find shortest paths and detect negative cycles
class BellmanFordHeap {
    // Graph represented as edges with weight
    private int vertices, edges;
    private List<Edge> edgeList;
    // Constructor
    BellmanFordHeap(int vertices, int edges) {
        this.vertices = vertices;
        this.edges = edges;
        this.edgeList = new ArrayList<>();
    }
    // Add directed edge
    void addEdge(int source, int destination, int weight) {
        edgeList.add(new Edge(source, destination, weight));
    }
    // Run Bellman-Ford algo
    void bellmanFord(int source) {
        // Initialize distances
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        // Relaxation step: Run (V-1) iterations to find shortest paths
        for (int i = 1; i < vertices; ++i) {
            for (Edge edge : edgeList) {
                int u = edge.source;
                int v = edge.destination;
                int w = edge.weight;
                if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                }
            }
        }

        // Check for negative cycles
        for (Edge edge : edgeList) {
            int u = edge.source;
            int v = edge.destination;
            int w = edge.weight;
            if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                System.out.println("Negative cycle detected!");
                return;
            }
        }

        // Printing shortest distances
        System.out.println("Vertex\tDistance");
        for (int i = 0; i < vertices; ++i) {
            System.out.println(i + "\t\t" + distance[i]);
        }
    }
}
// MaxHeapPriorityQueue implementation
class MaxHeapPriorityQueue {
    private List<Integer> heap; // Heap stored as ArrayList

    MaxHeapPriorityQueue() {
        heap = new ArrayList<>();
    }
    // Insert while maintaining max heap property
    void insert(int value) {
        heap.add(value);
        int currentIndex = heap.size() - 1;
        int parentIndex = (currentIndex - 1) / 2;
        // Maintain max-heap property by swapping with parent
        while (currentIndex > 0 && heap.get(currentIndex) > heap.get(parentIndex)) {
            Collections.swap(heap, currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = (currentIndex - 1) / 2;
        }
    }
    // Extract max element
    int extractMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue is empty.");
        }

        int maxValue = heap.get(0);
        int lastIndex = heap.size() - 1;
        // Replace with last, remove last
        heap.set(0, heap.get(lastIndex));
        heap.remove(lastIndex);

        int currentIndex = 0;
        int leftChildIndex = 1;
        int rightChildIndex = 2;
        // Restore max-heap property after removing root
        while (leftChildIndex < heap.size()) {
            int largestIndex = currentIndex;

            if (heap.get(leftChildIndex) > heap.get(largestIndex)) {
                largestIndex = leftChildIndex;
            }
            if (rightChildIndex < heap.size() && heap.get(rightChildIndex) > heap.get(largestIndex)) {
                largestIndex = rightChildIndex;
            }

            if (largestIndex == currentIndex) {
                break;
            }

            Collections.swap(heap, currentIndex, largestIndex);
            currentIndex = largestIndex;
            leftChildIndex = 2 * currentIndex + 1;
            rightChildIndex = 2 * currentIndex + 2;
        }

        return maxValue;
    }

    boolean isEmpty() {
        return heap.isEmpty();
    }
}
public class BellmanFord {
    public static void main(String[] args) {
        int vertices = 5;
        int edges = 8;

        BellmanFordHeap bellmanFordHeap = new BellmanFordHeap(vertices, edges);
        bellmanFordHeap.addEdge(0, 2, 4);
        bellmanFordHeap.addEdge(1, 2, 3);
        bellmanFordHeap.addEdge(0, 1, -1);
        bellmanFordHeap.addEdge(1, 3, 2);
        bellmanFordHeap.addEdge(1, 4, 2);
        bellmanFordHeap.addEdge(3, 2, 5);
        bellmanFordHeap.addEdge(3, 1, 1);
        bellmanFordHeap.addEdge(4, 3, -3);

        int sourceVertex = 0;
        bellmanFordHeap.bellmanFord(sourceVertex);

        MaxHeapPriorityQueue maxHeap = new MaxHeapPriorityQueue();
        maxHeap.insert(5);
        maxHeap.insert(10);
        maxHeap.insert(2);
        maxHeap.insert(8);
        maxHeap.insert(1);

        System.out.println("Max heap elements:");
        //printing the results
        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.extractMax());
        }
    }
}
