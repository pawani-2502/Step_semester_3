package inheritance.assigment_problems;

public class RaceEntryFoundation {

    public static class RaceEntry {
        protected String bibNumber;
        protected double entryFee;
        protected double balanceDue;

        public RaceEntry(String bibNumber, double entryFee) {
            if (bibNumber == null || bibNumber.trim().isEmpty() || bibNumber.length() < 4 || bibNumber.trim().length() < 4) {
                throw new IllegalArgumentException("Invalid bibNumber");
            }
            this.bibNumber = bibNumber.trim();
            this.entryFee = entryFee;
            this.balanceDue = entryFee;
        }

        public void pay(double amount) {
            this.balanceDue -= amount;
        }

        public double getBalanceDue() {
            return this.balanceDue;
        }

        public String getBibNumber() {
            return bibNumber;
        }

        public double getEntryFee() {
            return entryFee;
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

    public static String registerBatch(String[] bibNumbers, double entryFee) {
        if (bibNumbers == null) {
            return "Registered: 0 | Rejected: 0";
        }
        int registered = 0;
        int rejected = 0;
        for (String bib : bibNumbers) {
            try {
                new RaceEntry(bib, entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return String.format("Registered: %d | Rejected: %d", registered, rejected);
    }

    public static void main(String[] args) {
        try {
            new RaceEntry("B1", 50);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        System.out.println(r.getBalanceDue());

        String[] batch = {"BIB1", "B1", "BIB2"};
        System.out.println(registerBatch(batch, 80));
    }
}