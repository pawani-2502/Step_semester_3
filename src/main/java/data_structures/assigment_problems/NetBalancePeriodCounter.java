package data_structures.assigment_problems;

import java.util.HashMap;
import java.util.Map;

public class NetBalancePeriodCounter {

    public static int countPeriods(int[] transactions, long k) {
        if (transactions == null || transactions.length == 0) {
            return 0;
        }

        Map<Long, Integer> prefixCounts = new HashMap<>();
        prefixCounts.put(0L, 1);

        long currentSum = 0;
        int count = 0;

        for (int value : transactions) {
            currentSum += value;
            long complement = currentSum - k;

            if (prefixCounts.containsKey(complement)) {
                count += prefixCounts.get(complement);
            }

            prefixCounts.put(currentSum, prefixCounts.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] tx1 = {3, 4, -7, 1, 3, 3, 1, -4};
        System.out.println(countPeriods(tx1, 7));

        int[] tx2 = {1, 2, 3};
        System.out.println(countPeriods(tx2, 10));
    }
}