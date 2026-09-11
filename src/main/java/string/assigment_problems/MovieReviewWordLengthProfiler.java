package string.assigment_problems;

/**
 * Problem 5: The Movie Review Word Length Profiler
 *
 * Scenario:
 * Profiles word lengths in a movie review to categorize words into
 * Short (1-4 letters), Medium (5-8 letters), and Long (9+ letters).
 */
public class MovieReviewWordLengthProfiler {

    /**
     * Splits review into words, categorizes each word by character count,
     * and prints the category totals.
     *
     * @param review string containing review text
     */
    public static void classifyWordLengths(String review) {
        if (review == null || review.trim().isEmpty()) {
            System.out.println("Short: 0 | Medium: 0 | Long: 0");
            return;
        }

        String[] words = review.trim().split("\\s+");
        int shortCount = 0;
        int mediumCount = 0;
        int longCount = 0;

        for (String word : words) {
            // Strip punctuation if any to count word letters accurately
            String cleanWord = word.replaceAll("[^a-zA-Z0-9]", "");
            int len = cleanWord.length();

            if (len >= 1 && len <= 4) {
                shortCount++;
            } else if (len >= 5 && len <= 8) {
                mediumCount++;
            } else if (len >= 9) {
                longCount++;
            }
        }

        System.out.printf("Short: %d | Medium: %d | Long: %d%n", shortCount, mediumCount, longCount);
    }

    public static void main(String[] args) {
        // Sample Test Case
        String review = "This movie was absolutely fantastic and thrilling";
        System.out.println("Input: \"" + review + "\"");
        classifyWordLengths(review);
    }
}