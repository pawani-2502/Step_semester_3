package string.assigment_problems;

/**
 * Problem 1: The Exam Hall Seat Duplication Checker
 *
 * Scenario:
 * The Examination Cell manages seat allocation across a large exam hall.
 * This program checks for duplicate seat numbers in an assigned seat array
 * using only loops and arrays (no Collections).
 */
public class ExamHallSeatDuplicationChecker {

    /**
     * Scans the list of assigned seat numbers and flags any duplicates.
     * Uses only arrays and nested loops.
     *
     * @param seatNumbers array of integer seat numbers
     */
    public static void checkDuplicateSeats(int[] seatNumbers) {
        if (seatNumbers == null || seatNumbers.length <= 1) {
            System.out.println("No Duplicate Seats Found");
            return;
        }

        boolean foundDuplicate = false;

        for (int i = 0; i < seatNumbers.length; i++) {
            // Check if this seat number was already printed earlier to avoid duplicate reports
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

            // Look forward to find if seatNumbers[i] appears again
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
        // Sample Test Case 1
        int[] hall1 = {101, 102, 103, 102, 105};
        System.out.println("Test Case 1:");
        checkDuplicateSeats(hall1);

        // Sample Test Case 2
        int[] hall2 = {101, 102, 103, 104, 105};
        System.out.println("\nTest Case 2:");
        checkDuplicateSeats(hall2);
    }
}