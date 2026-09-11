package string.assigment_problems;

public class WordReversalEncoder {

    public static String reverseEachWord(String sentence) {
        if (sentence == null) {
            return "";
        }

        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            StringBuilder reversedWord = new StringBuilder();
            for (int j = words[i].length() - 1; j >= 0; j--) {
                reversedWord.append(words[i].charAt(j));
            }
            result.append(reversedWord);
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String input = "hello club";
        String output = reverseEachWord(input);
        System.out.println(output);
    }
}