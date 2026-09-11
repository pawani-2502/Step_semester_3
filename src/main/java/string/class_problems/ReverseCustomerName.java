package string.class_problems;

public class ReverseCustomerName {

    public static String reverseCustomerName(String customerName) {
        if (customerName == null) {
            return null;
        }

        char[] chars = customerName.toCharArray();
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

    public static void main(String[] args) {
        String customer = "Sunil";
        String reversed = reverseCustomerName(customer);

        System.out.println("Original Name: " + customer);
        System.out.println("Reversed Name: " + reversed);
    }
}