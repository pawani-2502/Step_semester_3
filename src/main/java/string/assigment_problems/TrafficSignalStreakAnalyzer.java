package string.assigment_problems;

/**
 * Problem 3: The Traffic Signal Streak Analyzer
 *
 * Scenario:
 * Scans traffic signal reading logs ('R', 'Y', 'G') to find the longest continuous
 * streak of consecutive identical signal colors.
 */
public class TrafficSignalStreakAnalyzer {

    /**
     * Finds and prints the color and length of the longest streak.
     *
     * @param signalLog sequence of signal readings
     */
    public static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.isEmpty()) {
            System.out.println("Signal log is empty.");
            return;
        }

        char maxColor = signalLog.charAt(0);
        int maxLength = 1;

        char currentColor = signalLog.charAt(0);
        int currentLength = 1;

        for (int i = 1; i < signalLog.length(); i++) {
            char ch = signalLog.charAt(i);
            if (ch == currentColor) {
                currentLength++;
            } else {
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    maxColor = currentColor;
                }
                currentColor = ch;
                currentLength = 1;
            }
        }

        // Check last sequence
        if (currentLength > maxLength) {
            maxLength = currentLength;
            maxColor = currentColor;
        }

        System.out.printf("Longest Streak: '%c' repeated %d times%n", maxColor, maxLength);
    }

    public static void main(String[] args) {
        // Sample Test Case 1
        System.out.println("Test Case 1:");
        findLongestStreak("RRGGGYRR");

        // Sample Test Case 2
        System.out.println("\nTest Case 2:");
        findLongestStreak("RRRRYYGG");
    }
}