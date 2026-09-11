package constructors.class_problems;

public class NightlyFleetReconciliationEngine {

    public static class BusTicketAccount {
        protected static double defaultFare;

        static {
            defaultFare = 1000.0;
        }

        private String bookingId;
        private double ticketFare;

        public BusTicketAccount(String bookingId, double ticketFare) {
            this.bookingId = bookingId;
            this.ticketFare = ticketFare;
        }

        public BusTicketAccount(String bookingId) {
            this(bookingId, defaultFare);
        }

        public final double calculatePenalty(int minutesLate) {
            if (minutesLate <= 0) {
                return 0.0;
            }
            return minutesLate * 5.0;
        }

        public String getBookingId() {
            return bookingId;
        }

        public double getTicketFare() {
            return ticketFare;
        }
    }

    public static class Sleeper extends BusTicketAccount {
        public Sleeper(String bookingId, double ticketFare) {
            super(bookingId, ticketFare);
        }
    }

    public static void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        if (account == null) {
            return;
        }
        account.calculatePenalty(minutesLate);
    }

    public static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
        if (accounts == null) {
            System.out.println("0 processed | 0 null skipped | 0 sleeper | 0 regular | grand total penalties = Rs 0.0");
            return;
        }

        int processed = 0;
        int nullSkipped = 0;
        int sleeperCount = 0;
        int regularCount = 0;
        double totalPenalties = 0.0;

        for (int i = 0; i < accounts.length; i++) {
            BusTicketAccount acc = accounts[i];
            if (acc == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            int minutes = (minutesLateArray != null && i < minutesLateArray.length) ? minutesLateArray[i] : 0;
            double penalty = acc.calculatePenalty(minutes);
            totalPenalties += penalty;

            if (acc instanceof Sleeper) {
                sleeperCount++;
            } else {
                regularCount++;
            }
        }

        System.out.printf("%d processed | %d null skipped | %d sleeper | %d regular | grand total penalties = Rs %.1f%n",
                processed, nullSkipped, sleeperCount, regularCount, totalPenalties);
    }

    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
            new Sleeper("BK001", 2000.0),
            null,
            new BusTicketAccount("BK002", 1200.0)
        };
        double[] amounts = {1200.0, 900.0, 700.0};
        int[] minutesLateArray = {10, 5, 0};

        processBatch(accounts, amounts, minutesLateArray);
    }
}