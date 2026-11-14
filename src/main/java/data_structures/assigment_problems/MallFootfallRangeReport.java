package data_structures.assigment_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MallFootfallRangeReport {

    public static List<Integer> footfallReport(int[] visitors, int[][] queries) {
        List<Integer> result = new ArrayList<>();
        if (visitors == null || visitors.length == 0 || queries == null) {
            return result;
        }

        int n = visitors.length;
        int[] prefix = new int[n];
        prefix[0] = visitors[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + visitors[i];
        }

        for (int[] query : queries) {
            if (query == null || query.length < 2) {
                continue;
            }
            int start = query[0];
            int end = query[1];

            if (start < 0 || end >= n || start > end) {
                continue;
            }

            int sum = (start == 0) ? prefix[end] : (prefix[end] - prefix[start - 1]);
            result.add(sum);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] visitors = {12, 7, 3, 9, 15, 4, 8};
        int[][] queries = {{0, 2}, {2, 5}, {4, 6}, {3, 3}};

        List<Integer> report = footfallReport(visitors, queries);
        System.out.println(report);
    }
}