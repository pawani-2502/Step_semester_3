package string.assigment_problems;

public class TrafficSignalStreakAnalyzer {

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

        if (currentLength > maxLength) {
            maxLength = currentLength;
            maxColor = currentColor;
        }

        System.out.printf("Longest Streak: '%c' repeated %d times%n", maxColor, maxLength);
    }

    public static void main(String[] args) {
        System.out.println("Test Case 1:");
        findLongestStreak("RRGGGYRR");

        System.out.println("\nTest Case 2:");
        findLongestStreak("RRRRYYGG");
    }
}