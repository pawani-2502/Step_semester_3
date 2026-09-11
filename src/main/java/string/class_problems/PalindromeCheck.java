package string.class_problems;

public class PalindromeCheck {

    public static boolean isPalindrome(String str) {
        if (str == null) return false;
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(str.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(str.charAt(right))) {
                right--;
            }
            if (Character.toLowerCase(str.charAt(left)) != Character.toLowerCase(str.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] testStrings = {
            "A man, a plan, a canal: Panama",
            "race a car",
            "Madam",
            "Step Semester 3"
        };

        for (String s : testStrings) {
            System.out.printf("\"%s\" -> isPalindrome: %b%n", s, isPalindrome(s));
        }
    }
}