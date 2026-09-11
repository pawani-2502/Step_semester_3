package string.assigment_problems;

/**
 * Problem 2: The Typing Speed Test Accuracy Checker
 *
 * Scenario:
 * Compares a user-typed string against the original passage character by character.
 * Calculates matching character count, accuracy percentage, and reports the position
 * of the first mismatch.
 */
public class TypingSpeedTestAccuracyChecker {

    /**
     * Compares original and typed strings, calculates accuracy and flags first mismatch.
     *
     * @param original the reference text passage
     * @param typed the text typed by user (assumed equal length per task definition)
     */
    public static void checkTypingAccuracy(String original, String typed) {
        if (original == null || typed == null) {
            System.out.println("Invalid input: string cannot be null.");
            return;
        }

        int total = original.length();
        int matched = 0;
        int firstMismatchPos = -1;
        char origChar = ' ';
        char typedChar = ' ';

        for (int i = 0; i < total; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1; // 1-based position
                origChar = original.charAt(i);
                typedChar = typed.charAt(i);
            }
        }

        double accuracy = total > 0 ? ((double) matched / total) * 100.0 : 0.0;

        if (firstMismatchPos != -1) {
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | First Mismatch at position %d ('%c' vs '%c')%n",
                    matched, total, accuracy, firstMismatchPos, origChar, typedChar);
        } else {
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | No Mismatches%n",
                    matched, total, accuracy);
        }
    }

    public static void main(String[] args) {
        // Sample Test Case 1
        System.out.println("Test Case 1:");
        checkTypingAccuracy("hello world", "hello worlt");

        // Sample Test Case 2
        System.out.println("\nTest Case 2:");
        checkTypingAccuracy("coding", "coding");
    }
}