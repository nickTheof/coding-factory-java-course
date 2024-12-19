package gr.aueb.cf.projects;

import java.io.*;

public class Project03App {
    // Initialize a class variable to store the character frequencies
    static int[][] freqArray = new int[128][2];

    public static void main(String[] args) {
        String fileInPath = "C:/Users/user/tmp/project-3-test.txt"; // String Path to the input file
        int c; // Variable to store the reading character from the file

        try (BufferedReader reader = new BufferedReader(new FileReader(fileInPath))) {
            while ((c = reader.read()) != -1) {
                if (isValidChar(c)) {
                    updateFrequency(c);
                }
            }
            printStats();

        } catch (IOException e) {
            System.out.println("Error. Fail to handle file.");
        }
    }

    /**
     * Method to validate if a character is a non whitespace character
     * @param ch The character to check
     * @return True if the character is valid, false otherwise
     */
    public static boolean isValidChar(int ch) {
        return !Character.isWhitespace(ch);
    }


    /**
     * Updates the frequency of a character in the frequency array.
     *
     * @param ch The character whose frequency needs to be updated.
     */
    public static void updateFrequency(int ch) {
        freqArray[ch % 128][0] = ch;
        freqArray[ch % 128][1]++;
    }

    /**
     * Counts the number of valid entries (non-zero frequencies) in the frequency array.
     *
     * @return The number of valid entries in the frequency array.
     */
    public static int getValidEntriesCount() {
        int validCount = 0;
        for (int[] freq : freqArray) {
            if (freq[1] > 0) {
                validCount++;
            }
        }
        return validCount;
    }

    /**
     * Filter and returns an array containing only characters that exists in the file
     * @return a two dimension array of valid characters and their respected frequencies
     */
    public static int[][] getFinallyFrequencies() {
        int[][] finalFreq = new int[getValidEntriesCount()][2];
        int index = 0;
        for (int[] freq : freqArray) {
            if (freq[1] > 0) {
                finalFreq[index][0] = freq[0];
                finalFreq[index][1] = freq[1];
                index++;
            }
        }
        return finalFreq;
    }

    /**
     * Prints the frequency of characters from the given 2D array.
     *
     * @param arr The 2D array containing characters and their frequencies.
     */
    public static void printFrequency(int[][] arr){
        if (arr == null || arr.length == 0) {
            System.out.println("No frequency data available to print.");
            return;
        }
        System.out.println("Character : Frequency");
        for (int[] freqChars : arr) {
            if (freqChars != null && freqChars.length == 2) {
                System.out.printf("%s : %d\n", (char) freqChars[0], freqChars[1]);
            } else {
                System.out.println("Invalid data entry encountered. Skipping...");
            }
        }
    }

    /**
     * Prints the frequency table sorted by ASCII values.
     */
    public static void printFreqTable() {
        int[][] freqTable = getFinallyFrequencies();
        printFrequency(freqTable);
    }

    /**
     * Prints the frequency table sorted by frequency count in descending order.
     */
    public static void printSortByCount() {
        int[][] freqTable = getFinallyFrequencies();
        // Bubble sort algorithm
        for (int i = freqTable.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (freqTable[j][1] < freqTable[j + 1][1]) {
                    int[] tmp = freqTable[j];
                    freqTable[j] = freqTable[j + 1];
                    freqTable[j + 1] = tmp;
                }
            }
        }
        printFrequency(freqTable);
    }


    /**
     * Prints character frequency statistics:
     * - Sorted by frequency count
     * - Sorted by ASCII value (character order)
     */
    public static void printStats(){
        System.out.println("--------------------");
        System.out.println("Printing characters frequency sorted by count");
        System.out.println("--------------------");
        printSortByCount();
        System.out.println("--------------------");
        System.out.println("Printing characters frequency sorted by character");
        System.out.println("--------------------");
        printFreqTable();
    }
}

