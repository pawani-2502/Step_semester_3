package string.class_problems;

public class FirstNonRepeatingCharacter {

    public static char findFirstNonRepeatingChar(String text) {
        if (text == null || text.isEmpty()) {
            return '\0';
        }

        int[] freq = new int[256];
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch < 256) {
                freq[ch]++;
            }
        }

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch < 256 && freq[ch] == 1) {
                return ch;
            }
        }

        return '\0';
    }

    public static void displayFirstNonRepeating(String text) {
        char result = findFirstNonRepeatingChar(text);
        if (result != '\0') {
            System.out.printf("\"%s\" -> First Non-Repeating Character: '%c'%n", text, result);
        } else {
            System.out.printf("\"%s\" -> No Non-Repeating Character Found%n", text);
        }
    }

    public static void main(String[] args) {
        displayFirstNonRepeating("swiss");
        displayFirstNonRepeating("aabbcc");
    }
}