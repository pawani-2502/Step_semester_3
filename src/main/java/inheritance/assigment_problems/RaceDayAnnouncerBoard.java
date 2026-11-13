package inheritance.assigment_problems;

public class RaceDayAnnouncerBoard {

    public static class RaceEntry {
        protected String bibNumber;
        protected double entryFee;
        protected double balanceDue;

        public RaceEntry(String bibNumber, double entryFee) {
            this.bibNumber = bibNumber;
            this.entryFee = entryFee;
            this.balanceDue = entryFee;
        }

        public void pay(double amount) {
            this.balanceDue -= amount;
        }

        public double getBalanceDue() {
            return this.balanceDue;
        }

        public String announce() {
            return "Race Entry | Bib: " + bibNumber + " | Balance: " + balanceDue;
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

        @Override
        public String announce() {
            return "Runner Entry | Bib: " + bibNumber + " | Category: " + category + " | Balance: " + balanceDue;
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

        @Override
        public String announce() {
            return "Relay Team | Bib: " + bibNumber + " | Team Size: " + teamSize + " | Balance: " + balanceDue;
        }
    }

    public static String announceAll(RaceEntry[] entries) {
        if (entries == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (RaceEntry entry : entries) {
            if (entry != null) {
                sb.append(entry.announce());
                if (entry instanceof RelayTeamEntry) {
                    RelayTeamEntry re = (RelayTeamEntry) entry;
                    sb.append(" [Team size via downcast: ").append(re.getTeamSize()).append("]");
                }
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RunnerEntry runner = new RunnerEntry("BIB2001", 80, "Open 10K");
        runner.pay(30);
        runner.balanceDue += 40;

        RelayTeamEntry relay = new RelayTeamEntry("BIB4001", 300, 4);

        RaceEntry[] fleet = {runner, relay};
        System.out.println(announceAll(fleet));

        try {
            RaceEntry plain = new RaceEntry("BIB5001", 50);
            RelayTeamEntry bad = (RelayTeamEntry) plain;
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }
    }
}