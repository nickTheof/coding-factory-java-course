package gr.aueb.cf.projects;

/**
 * The applied algorithm can be described through the following steps:
 * - We initialize variables to keep track of:
 *   - The current maximum sum ("localMaximum").
 *   - The global maximum sum ("globalMaximum").
 *   - The indices of the desirable sub array (start index, end index).
 *   - A temporary start index ("tempStartIndex") to track the start of the current sub array.
 * - For each element in the array:
 *   - Calculate the current maximum sum as the maximum of:
 *      - The current element's value, or
 *      - The sum of the current maximum sum of the previous element and the value of the current element.
 *     - If the maximum value is the current element, a new sub array starts:
 *        - Update the temporary start index to the current element's index.
 *        - Set the current maximum sum to the value of the current element.
 *     - Otherwise, extend the existing sub array:
 *        - Update the current maximum sum to the sum of the previous local maximum and the current element.
 * - After each element, check if the current maximum sum exceeds the global maximum sum:
 *      - If it does:
 *        - Update the global maximum sum to the value of the current maximum sum.
 *        - Set the start index to the temporary start index of the current sub array.
 *        - Set the end index to the index of the current element.
 * - At the end of the iteration:
 *      - The global maximum sum represents the maximum sum of any contiguous sub array.
 *      - The start and end indices indicate the boundaries of this sub array.
 * - The time complexity of the applied algorithm is O(n). This is because there is a single "for" loop which
 * iterates through the array exactly once, performing constant-time operations for each element.
 * As a result, the number of operations is directly proportional to the size n of the input array, establishing a linear relationship.
 */
public class Project02App {
    public static void main(String[] args) {
        int[] arr = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        // Initialize local and global maximum sum
        int localMaximum = arr[0];
        int globalMaximum = arr[0];
        // Initialize the start and the end index of the max sum sub array
        int startIndex = 0;
        int endIndex = 0;
        int tempStartIndex = 0; //Test start index of the current sub array
        for (int i = 1; i < arr.length; i++){
            if (localMaximum + arr[i] < arr[i]){
                localMaximum = arr[i];
                tempStartIndex = i;
            } else {
                localMaximum += arr[i];
            }

            if (localMaximum > globalMaximum) {
                globalMaximum = localMaximum;
                startIndex = tempStartIndex;
                endIndex = i;
            }
        }
        System.out.println("Maximum Sub Array:");
        for (int i = startIndex; i <= endIndex; i++){
            System.out.printf("%d ", arr[i]);
        }
    }

}
