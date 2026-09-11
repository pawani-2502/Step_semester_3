package string.class_problems;

public class PalindromeChecker {

    public static boolean isPalindromeIterative(String text) {
        if (text == null) return false;
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (Character.toLowerCase(text.charAt(left)) != Character.toLowerCase(text.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindromeRecursive(String text) {
        if (text == null) return false;
        return checkRecursiveHelper(text.toLowerCase(), 0, text.length() - 1);
    }

    private static boolean checkRecursiveHelper(String text, int start, int end) {
        if (start >= end) {
            return true;
        }
        if (text.charAt(start) != text.charAt(end)) {
            return false;
        }
        return checkRecursiveHelper(text, start + 1, end - 1);
    }

    public static boolean isPalindromeArrayReversal(String text) {
        if (text == null) return false;
        char[] original = text.toLowerCase().toCharArray();
        char[] reversed = new char[original.length];
        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }
        for (int i = 0; i < original.length; i++) {
            if (original[i] != reversed[i]) {
                return false;
            }
        }
        return true;
    }

    public static void displayPalindromeResults(String text) {
        String iter = isPalindromeIterative(text) ? "Palindrome" : "Not Palindrome";
        String recur = isPalindromeRecursive(text) ? "Palindrome" : "Not Palindrome";
        String arrayRev = isPalindromeArrayReversal(text) ? "Palindrome" : "Not Palindrome";

        System.out.printf("\"%s\"%nIterative: %s | Recursive: %s | Array Reversal: %s%n%n",
                text, iter, recur, arrayRev);
    }

    public static void main(String[] args) {
        displayPalindromeResults("madam");
        displayPalindromeResults("hello");
    }
}