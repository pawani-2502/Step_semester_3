package data_structures.assigment_problems;

public class LongestBudgetFriendlyStreak {

    public static class StreakResult {
        public final int length;
        public final int startIndex;

        public StreakResult(int length, int startIndex) {
            this.length = length;
            this.startIndex = startIndex;
        }

        @Override
        public String toString() {
            return "(" + length + ", " + startIndex + ")";
        }
    }

    public static StreakResult longestStreak(int[] costs, long budget) {
        if (costs == null || costs.length == 0 || budget < 0) {
            return new StreakResult(0, -1);
        }

        int maxLength = 0;
        int bestStart = -1;
        int left = 0;
        long currentSum = 0;

        for (int right = 0; right < costs.length; right++) {
            currentSum += costs[right];

            while (currentSum > budget && left <= right) {
                currentSum -= costs[left];
                left++;
            }

            if (currentSum <= budget && left <= right) {
                int len = right - left + 1;
                if (len > maxLength) {
                    maxLength = len;
                    bestStart = left;
                }
            }
        }

        if (maxLength == 0) {
            return new StreakResult(0, -1);
        }

        return new StreakResult(maxLength, bestStart);
    }

    public static void main(String[] args) {
        int[] costs1 = {4, 2, 1, 7, 3, 1, 2, 1, 5};
        System.out.println(longestStreak(costs1, 8));

        int[] costs2 = {9, 10};
        System.out.println(longestStreak(costs2, 8));
    }
}