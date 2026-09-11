package string.assigment_problems;

import java.util.Arrays;

/**
 * Assignment Problem: Anagram Check
 * Checks if two strings are anagrams of each other (contain the same characters with the same frequencies).
 */
public class AnagramCheck {

    public static boolean isAnagram(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        
        // Normalize: remove spaces and convert to lowercase
        String cleanS1 = s1.replaceAll("\\s+", "").toLowerCase();
        String cleanS2 = s2.replaceAll("\\s+", "").toLowerCase();

        if (cleanS1.length() != cleanS2.length()) return false;

        char[] arr1 = cleanS1.toCharArray();
        char[] arr2 = cleanS2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        return Arrays.equals(arr1, arr2);
    }

    public static void main(String[] args) {
        String[][] pairs = {
            {"listen", "silent"},
            {"triangle", "integral"},
            {"apple", "pale"},
            {"Conversation", "Voices, rant on"}
        };

        for (String[] pair : pairs) {
            System.out.printf("\"%s\" and \"%s\" -> Anagram: %b%n", pair[0], pair[1], isAnagram(pair[0], pair[1]));
        }
    }
}
