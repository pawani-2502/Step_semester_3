package string.assigment_problems;

public class ExamHallSeatDuplicationChecker {

    public static void checkDuplicateSeats(int[] seatNumbers) {
        if (seatNumbers == null || seatNumbers.length <= 1) {
            System.out.println("No Duplicate Seats Found");
            return;
        }

        boolean foundDuplicate = false;

        for (int i = 0; i < seatNumbers.length; i++) {
            boolean alreadyProcessed = false;
            for (int k = 0; k < i; k++) {
                if (seatNumbers[k] == seatNumbers[i]) {
                    alreadyProcessed = true;
                    break;
                }
            }
            if (alreadyProcessed) {
                continue;
            }

            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    foundDuplicate = true;
                    break;
                }
            }
        }

        if (!foundDuplicate) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void main(String[] args) {
        int[] hall1 = {101, 102, 103, 102, 105};
        System.out.println("Test Case 1:");
        checkDuplicateSeats(hall1);

        int[] hall2 = {101, 102, 103, 104, 105};
        System.out.println("\nTest Case 2:");
        checkDuplicateSeats(hall2);
    }
}