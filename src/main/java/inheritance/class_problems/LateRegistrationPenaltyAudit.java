package inheritance.class_problems;

import java.util.Arrays;

public class LateRegistrationPenaltyAudit {

    public static class EventTicket {
        protected String attendeeId;
        protected double basePrice;
        protected double balanceDue;
        private double[] lateFeeHistory;
        private int lateFeeCount;

        public EventTicket(String attendeeId, double basePrice) {
            if (attendeeId == null || attendeeId.trim().isEmpty() || attendeeId.length() < 4 || attendeeId.trim().length() < 4) {
                throw new IllegalArgumentException("Invalid attendeeId");
            }
            this.attendeeId = attendeeId.trim();
            this.basePrice = basePrice;
            this.balanceDue = basePrice;
            this.lateFeeHistory = new double[10];
            this.lateFeeCount = 0;
        }

        public EventTicket(double basePrice) {
            this("TCK-DEFAULT", basePrice);
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

    public static class WorkshopTicket extends EventTicket {
        private String track;

        public WorkshopTicket(String attendeeId, double basePrice, String track) {
            super(attendeeId, basePrice);
            this.track = track;
        }

        public WorkshopTicket(double basePrice) {
            super(basePrice);
            this.track = "General";
        }

        public String getTrack() {
            return track;
        }

        @Override
        protected void applyLateFee(double amount) {
            super.applyLateFee(amount * 2);
        }
    }

    public static void main(String[] args) {
        WorkshopTicket w = new WorkshopTicket(1200);
        w.pay(1200);
        w.applyLateFee(100);
        System.out.println(w.getBalanceDue());

        double[] history = w.getLateFeeHistory();
        System.out.println(Arrays.toString(history));
        history[0] = 999;
        System.out.println(Arrays.toString(w.getLateFeeHistory()));
    }
}