package string.class_problems;

/**
 * Class Problem: Reverse a String
 * Demonstration of reversing a string using in-place character array two-pointer approach
 * and StringBuilder.
 */
public class ReverseString {

    public static String reverseWithPointers(String str) {
        if (str == null) return null;
        char[] chars = str.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }

    public static String reverseWithStringBuilder(String str) {
        if (str == null) return null;
        return new StringBuilder(str).reverse().toString();
    }

    public static void main(String[] args) {
        String input = "Hello, World!";
        System.out.println("Original String: " + input);
        System.out.println("Reversed (Two Pointers): " + reverseWithPointers(input));
        System.out.println("Reversed (StringBuilder): " + reverseWithStringBuilder(input));
    }
}
