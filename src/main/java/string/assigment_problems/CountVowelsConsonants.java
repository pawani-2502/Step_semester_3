package string.assigment_problems;

public class CountVowelsConsonants {

    public static class Result {
        public final int vowels;
        public final int consonants;

        public Result(int vowels, int consonants) {
            this.vowels = vowels;
            this.consonants = consonants;
        }

        @Override
        public String toString() {
            return String.format("Vowels: %d, Consonants: %d", vowels, consonants);
        }
    }

    public static Result countVowelsAndConsonants(String input) {
        if (input == null) return new Result(0, 0);

        int vowels = 0;
        int consonants = 0;
        String lower = input.toLowerCase();

        for (int i = 0; i < lower.length(); i++) {
            char ch = lower.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }
        return new Result(vowels, consonants);
    }

    public static void main(String[] args) {
        String test = "Hello World! Java Programming";
        Result res = countVowelsAndConsonants(test);
        System.out.println("Input: " + test);
        System.out.println("Result: " + res);
    }
}