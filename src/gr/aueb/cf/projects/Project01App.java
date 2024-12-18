package gr.aueb.cf.projects;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Project01App {
    public static void main(String[] args) {
        String fileInPath = "C:/Users/user/tmp/project-01-test.txt";
        String fileOutPath = "C:/Users/user/tmp/project-01-output.txt";
        final int COMBO_SIZE = 6;
        int[] combination = new int[COMBO_SIZE];
        ArrayList<Integer> numArray = new ArrayList<>();
        int window = 0;

        try (PrintStream ps = new PrintStream(fileOutPath)) {
            numArray = readInputNumbers(fileInPath);
            Collections.sort(numArray);
            window = numArray.size() - COMBO_SIZE;
            for (int i = 0; i <= window; i++) {
                for (int j = i + 1; j <= window + 1; j++) {
                    for (int k = j + 1; k <= window + 2; k++) {
                        for (int l = k + 1; l <= window + 3; l++) {
                            for (int m = l + 1; m <= window + 4; m++) {
                                for (int n = m + 1; n <= window + 5; n++) {
                                    combination[0] = numArray.get(i);
                                    combination[1] = numArray.get(j);
                                    combination[2] = numArray.get(k);
                                    combination[3] = numArray.get(l);
                                    combination[4] = numArray.get(m);
                                    combination[5] = numArray.get(n);
                                    if (isValidCombination(combination)) {
                                        ps.printf("%d, %d, %d, %d, %d, %d\n", combination[0],combination[1],combination[2],combination[3],combination[4],combination[5]);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error with opening file");
        } catch (IllegalArgumentException e) {
            System.out.println("The provided data do not fulfill the requirements. " + e.getMessage());
        }
    }


    public static ArrayList<Integer> readInputNumbers(String fileInPath) throws FileNotFoundException{
        int num = 0;
        ArrayList<Integer> numArray = new ArrayList<>();
        try (Scanner inFile = new Scanner(new File(fileInPath))){
            while (inFile.hasNextInt()){
                num = inFile.nextInt();
                if (!isValidInteger(num)) throw new IllegalArgumentException("Number out of range: " + num);
                numArray.add(num);
            }
            if (!isValidInputArray(numArray)) throw new IllegalArgumentException("File must contain between 6 and 49 numbers.");
            return numArray;
        } catch (FileNotFoundException | IllegalArgumentException e){
            System.err.println(e.getMessage());
            throw e;
        }
    }

    /**
     * Validates if a number is within the range [1, 49].
     * @param num
     * @return
     */
    public static boolean isValidInteger(int num){
        return num >= 1 && num <= 49;
    }

    /**
     * Validates the length of the ArrayList, array must contain 6 to 49 numbers.
     * @param array
     * @return
     */
    public static boolean isValidInputArray(ArrayList<Integer> array){
        if (array == null) return false;
        int size = array.size();
        return size >= 6 && size <= 49;
    }

    /**
     * Checks if the array contains not more than four even numbers.
     * @param arr
     * @return
     */
    public static boolean notMoreThanFourEvens(int[] arr){
        if (arr == null) return true;
        int countEvens = 0;
        for (int num : arr){
            if (num % 2 == 0) countEvens++;
        }
        return countEvens <= 4;
    }

    /**
     * Checks if the array contains no more than four odd numbers.
     * @param arr
     * @return
     */
    public static boolean notMoreThanFourOdds(int[] arr){
        if (arr == null) return true;
        int countOdd = 0;
        for (int num : arr){
            if (num % 2 != 0) countOdd++;
        }
        return countOdd <= 4;
    }

    /**
     * Checks if the array contains no more than two contiguous numbers.
     * @param arr
     * @return
     */
    public static boolean notMoreThanTwoContiguous(int[] arr){
        if (arr == null || arr.length <= 2) return true;
        for (int i = 0; i < arr.length - 2; i++){
            if ((arr[i] == arr[i+1] - 1) && ((arr[i] == arr[i+2] - 2))) return false;
        }
        return true;
    }

    /**
     * Checks if the array contains no more than three numbers with the same last digit.
     * @param arr
     * @return
     */
    public static boolean notMoreThanThreeSameEndings(int[] arr){
        if (arr == null) return true;
        boolean hasNotMoreThanThreeSameEndings = true;
        int[] endings = new int[10];
        for (int num : arr){
            endings[num % 10]++;
        }
        for (int countEnding : endings){
            if (countEnding > 3){
                hasNotMoreThanThreeSameEndings = false;
                break;
            }
        }
        return hasNotMoreThanThreeSameEndings;
    }


    /**
     * Checks if the array contains no more than three numbers in the same decade.
     * @param arr
     * @return
     */
    public static boolean notMoreThanThreeSameDecade(int[] arr){
        if (arr == null) return true;
        boolean hasNotMoreThanThreeSameDecade = true;
        int[] decades = new int[5];
        for (int number: arr){
            decades[number / 10]++;
        }
        for (int dec: decades){
            if (dec > 3){
                hasNotMoreThanThreeSameDecade = false;
                break;
            }
        }
        return hasNotMoreThanThreeSameDecade;
    }

    /**
     * Checks if a combination meets all filtering criteria.
     * @param combination
     * @return
     */
    public static boolean isValidCombination(int[] combination) {
        if (combination == null) return true;
        return notMoreThanFourEvens(combination) &&
                notMoreThanFourOdds(combination) &&
                notMoreThanTwoContiguous(combination) &&
                notMoreThanThreeSameEndings(combination) &&
                notMoreThanThreeSameDecade(combination);
    }
}

