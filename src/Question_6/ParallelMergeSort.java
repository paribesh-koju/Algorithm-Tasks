package Question_6;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort {
    // Define a private inner class that extends RecursiveAction
    // to enable fork/join parallelism
    private static class MergeSortTask extends RecursiveAction {
        // Array to sort and subrange to process
        private final int[] array;
        private final int start;
        private final int end;

        public MergeSortTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }
        // Recursively split task into smaller tasks

        @Override
        protected void compute() {
            if (end - start <= 1) {
                return;
            }

            //Divide array in half
            int mid = (start + end) / 2;

            //create left and right subtask
            MergeSortTask leftTask = new MergeSortTask(array, start, mid);
            MergeSortTask rightTask = new MergeSortTask(array, mid, end);

            // Launch subtasks in parallel
            invokeAll(leftTask, rightTask);

            //merge sorted subarray
            merge(array, start, mid, end);
        }

        private void merge(int[] array, int start, int mid, int end) {
            int[] merged = new int[end - start];
            int leftIndex = start;
            int rightIndex = mid;
            int mergedIndex = 0;

            while (leftIndex < mid && rightIndex < end) {
                if (array[leftIndex] < array[rightIndex]) {
                    merged[mergedIndex++] = array[leftIndex++];
                } else {
                    merged[mergedIndex++] = array[rightIndex++];
                }
            }

            while (leftIndex < mid) {
                merged[mergedIndex++] = array[leftIndex++];
            }

            while (rightIndex < end) {
                merged[mergedIndex++] = array[rightIndex++];
            }

            System.arraycopy(merged, 0, array, start, merged.length);
        }
    }

    // Launch parallel merge sort on array
    public static void parallelMergeSort(int[] array) {
        //create thread pool
        ForkJoinPool pool = new ForkJoinPool();
        //submit merge sort task
        MergeSortTask task = new MergeSortTask(array, 0, array.length);
        pool.invoke(task);
    }

    public static void main(String[] args) {
        int[] inputArray = {5, 3, 9, 1, 7, 2, 8, 4, 6};
        //launch parallel merge sort
        parallelMergeSort(inputArray);
        System.out.println("Sorted array: " + Arrays.toString(inputArray));
    }
    /*
The key ideas are:
    Extend RecursiveAction for fork/join parallelism
    Recursively split problem into smaller subproblems
    Compute subproblems in parallel with invokeAll()
    Merge results of subproblems
 */
}
