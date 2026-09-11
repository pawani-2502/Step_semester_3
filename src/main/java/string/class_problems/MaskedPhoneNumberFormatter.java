package string.class_problems;

public class MaskedPhoneNumberFormatter {

    public static String maskPhoneNumber(String phone) {
        if (phone == null || phone.length() != 10) {
            return "Invalid phone number";
        }

        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("XXXXXX");
        sb.append(phone.substring(6));
        sb.insert(6, "-");

        return sb.toString();
    }

    public static void main(String[] args) {
        String p1 = "9876543210";
        String p2 = "98765";

        System.out.println(maskPhoneNumber(p1));
        System.out.println(maskPhoneNumber(p2));
    }
}