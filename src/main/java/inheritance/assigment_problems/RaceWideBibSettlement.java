package inheritance.assigment_problems;

public class RaceWideBibSettlement {

    public static class RaceEntry {
        private static int bibCounter = 0;

        public final String entryCode;
        protected String bibNumber;
        protected double entryFee;
        protected double balanceDue;

        public RaceEntry(String bibNumber, double entryFee) {
            if (bibNumber == null || bibNumber.trim().isEmpty() || bibNumber.length() < 4 || bibNumber.trim().length() < 4) {
                throw new IllegalArgumentException("Invalid bibNumber");
            }
            bibCounter++;
            this.entryCode = "ENTRY-" + (1000 + bibCounter);
            this.bibNumber = bibNumber.trim();
            this.entryFee = entryFee;
            this.balanceDue = entryFee;
        }

        public void pay(double amount) {
            this.balanceDue -= amount;
        }

        public void pay(double amount, String mode) {
            System.out.println("Paying via " + mode);
            pay(amount);
        }

        public double getBalanceDue() {
            return this.balanceDue;
        }

        public static boolean isValidDiscountCode(String code) {
            if (code == null || code.length() != 5) {
                return false;
            }
            if (code.charAt(0) != 'M') {
                return false;
            }
            if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2)) || !Character.isDigit(code.charAt(3))) {
                return false;
            }
            if (!Character.isUpperCase(code.charAt(4))) {
                return false;
            }
            return true;
        }

        public static int getBibCounter() {
            return bibCounter;
        }

        public static void resetCounter() {
            bibCounter = 0;
        }
    }

    public static class RunnerEntry extends RaceEntry {
        private String category;

        public RunnerEntry(String bibNumber, double entryFee, String category) {
            super(bibNumber, entryFee);
            this.category = category;
        }

        public String getCategory() {
            return category;
        }
    }

    public static class EliteRunnerEntry extends RunnerEntry {
        private double sponsorBonus;

        public EliteRunnerEntry(String bibNumber, double entryFee, String category, double sponsorBonus) {
            super(bibNumber, entryFee, category);
            this.sponsorBonus = sponsorBonus;
        }

        public double getSponsorBonus() {
            return sponsorBonus;
        }
    }

    public static class RelayTeamEntry extends RaceEntry {
        private int teamSize;

        public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
            super(bibNumber, entryFee);
            this.teamSize = teamSize;
        }

        public int getTeamSize() {
            return teamSize;
        }
    }

    public static String settleNight(RaceEntry[] entries) {
        if (entries == null) {
            return "0 processed | 0 null skipped | 0 relay | 0 individual";
        }

        int processed = 0;
        int nullSkipped = 0;
        int relayCount = 0;
        int individualCount = 0;

        for (RaceEntry entry : entries) {
            if (entry == null) {
                nullSkipped++;
            } else {
                processed++;
                if (entry instanceof RelayTeamEntry) {
                    relayCount++;
                } else {
                    individualCount++;
                }
            }
        }

        return String.format("%d processed | %d null skipped | %d relay | %d individual",
                processed, nullSkipped, relayCount, individualCount);
    }

    public static void main(String[] args) {
        System.out.println(RaceEntry.isValidDiscountCode("M123A"));
        System.out.println(RaceEntry.isValidDiscountCode("M12A"));
        System.out.println(RaceEntry.isValidDiscountCode("X123A"));

        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        EliteRunnerEntry eliteEntry = new EliteRunnerEntry("BIB3001", 150, "Elite Full Marathon", 500);
        RelayTeamEntry relayEntry = new RelayTeamEntry("BIB4001", 300, 4);

        r.pay(10, "UPI");

        RaceEntry[] batch = {eliteEntry, null, relayEntry};
        System.out.println(settleNight(batch));

        RaceEntry extra = new RaceEntry("BIB5001", 50);
        System.out.println(RaceEntry.getBibCounter());
    }
}