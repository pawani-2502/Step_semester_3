package inheritance.assigment_problems;

import java.util.Arrays;

public class LateWithdrawalPenaltyAudit {

    public static class RaceEntry {
        protected String bibNumber;
        protected double entryFee;
        protected double balanceDue;
        private double[] lateFeeHistory;
        private int lateFeeCount;

        public RaceEntry(String bibNumber, double entryFee) {
            if (bibNumber == null || bibNumber.trim().isEmpty() || bibNumber.length() < 4 || bibNumber.trim().length() < 4) {
                throw new IllegalArgumentException("Invalid bibNumber");
            }
            this.bibNumber = bibNumber.trim();
            this.entryFee = entryFee;
            this.balanceDue = entryFee;
            this.lateFeeHistory = new double[10];
            this.lateFeeCount = 0;
        }

        public void pay(double amount) {
            this.balanceDue -= amount;
        }

        public double getBalanceDue() {
            return this.balanceDue;
        }

        protected void applyLateFee(double amount) {
            if (lateFeeCount < 10) {
                lateFeeHistory[lateFeeCount++] = amount;
            }
            this.balanceDue += amount;
        }

        public double[] getLateFeeHistory() {
            double[] copy = new double[lateFeeCount];
            System.arraycopy(lateFeeHistory, 0, copy, 0, lateFeeCount);
            return copy;
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
        protected void applyLateFee(double amount) {
            super.applyLateFee(amount * 2);
        }
    }

    public static void main(String[] args) {
        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        r.applyLateFee(20);
        System.out.println(r.getBalanceDue());

        double[] history = r.getLateFeeHistory();
        System.out.println(Arrays.toString(history));
        history[0] = 999;
        System.out.println(Arrays.toString(r.getLateFeeHistory()));
    }
}