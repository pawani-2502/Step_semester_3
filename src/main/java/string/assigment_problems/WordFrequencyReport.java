package string.assigment_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordFrequencyReport {

    public static void printFilteredWordFrequency(String feedback) {
        if (feedback == null || feedback.trim().isEmpty()) {
            return;
        }

        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};

        String cleaned = feedback.toLowerCase().replace(".", "").replace(",", "").replace("!", "").replace("?", "");
        String[] words = cleaned.split("\\s+");

        Map<String, Integer> freqMap = new HashMap<>();

        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }

            boolean isStopWord = false;
            for (String stop : stopWords) {
                if (word.equals(stop)) {
                    isStopWord = true;
                    break;
                }
            }

            if (!isStopWord) {
                freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(freqMap.entrySet());
        list.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        String input = "The mentor was great, the session was great and clear.";
        printFilteredWordFrequency(input);
    }
}