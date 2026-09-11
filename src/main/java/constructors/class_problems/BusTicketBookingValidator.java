package constructors.class_problems;

import java.util.ArrayList;
import java.util.List;

public class BusTicketBookingValidator {

    public static class BusTicket {
        private String passengerName;
        private String destination;
        private boolean checkedIn;

        public BusTicket(String passengerName, String destination) {
            if (passengerName == null || destination == null) {
                throw new IllegalArgumentException("Parameters cannot be null");
            }

            String pName = passengerName.trim();
            String dest = destination.trim();

            if (pName.isEmpty() || dest.isEmpty()) {
                throw new IllegalArgumentException("Fields cannot be empty");
            }

            for (int i = 0; i < pName.length(); i++) {
                char ch = pName.charAt(i);
                if (!Character.isLetter(ch) && ch != ' ') {
                    throw new IllegalArgumentException("Name contains invalid characters");
                }
            }

            for (int i = 0; i < dest.length(); i++) {
                char ch = dest.charAt(i);
                if (!Character.isLetter(ch) && ch != ' ') {
                    throw new IllegalArgumentException("Destination contains invalid characters");
                }
            }

            this.passengerName = pName;
            this.destination = dest;
            this.checkedIn = false;
        }

        public void markCheckedIn() {
            if (this.checkedIn) {
                System.out.println("Warning: Ticket already checked in.");
                return;
            }
            this.checkedIn = true;
        }

        public String getPassengerName() {
            return passengerName;
        }

        public String getDestination() {
            return destination;
        }

        public boolean isCheckedIn() {
            return checkedIn;
        }
    }

    public static void processBatch(String[][] rawBookings) {
        if (rawBookings == null) {
            System.out.println("Valid: 0 | Rejected: 0 | Duplicates skipped: 0");
            return;
        }

        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        List<String> acceptedKeys = new ArrayList<>();

        for (String[] booking : rawBookings) {
            if (booking == null || booking.length < 2) {
                rejected++;
                continue;
            }

            try {
                BusTicket ticket = new BusTicket(booking[0], booking[1]);
                String key = ticket.getPassengerName().toLowerCase() + ":" + ticket.getDestination().toLowerCase();

                if (acceptedKeys.contains(key)) {
                    duplicates++;
                } else {
                    acceptedKeys.add(key);
                    valid++;
                }
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.printf("Valid: %d | Rejected: %d | Duplicates skipped: %d%n", valid, rejected, duplicates);
    }

    public static void main(String[] args) {
        String[][] batch = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };

        processBatch(batch);
    }
}