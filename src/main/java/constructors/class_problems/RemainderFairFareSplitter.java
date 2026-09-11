package constructors.class_problems;

import java.util.Arrays;

public class RemainderFairFareSplitter {

    public static class FareSplitter {
        private String tripId;
        private double totalFare;
        private int passengerCount;

        public FareSplitter(String tripId, double totalFare, int passengerCount) {
            if (tripId == null || tripId.trim().isEmpty()) {
                throw new IllegalArgumentException("Trip ID cannot be empty");
            }
            if (totalFare < 0) {
                throw new IllegalArgumentException("Fare cannot be negative");
            }
            if (passengerCount <= 0) {
                throw new IllegalArgumentException("Passenger count must be positive");
            }
            this.tripId = tripId;
            this.totalFare = totalFare;
            this.passengerCount = passengerCount;
        }

        public FareSplitter(String tripId, double totalFare) {
            this(tripId, totalFare, 2);
        }

        public FareSplitter(String tripId) {
            this(tripId, 0.0, 2);
        }

        public double[] fareBreakdown() {
            double[] breakdown = new double[passengerCount];
            long totalCents = Math.round(totalFare * 100.0);
            long baseCents = totalCents / passengerCount;
            long allocated = 0;

            for (int i = 0; i < passengerCount - 1; i++) {
                breakdown[i] = baseCents / 100.0;
                allocated += baseCents;
            }

            breakdown[passengerCount - 1] = (totalCents - allocated) / 100.0;
            return breakdown;
        }

        public boolean isConfirmationOverdue(int confirmed, int expected) {
            return confirmed < expected;
        }

        public String getTripId() {
            return tripId;
        }
    }

    public static void main(String[] args) {
        FareSplitter f1 = new FareSplitter("TRIP001", 100000, 3);
        System.out.println(Arrays.toString(f1.fareBreakdown()));

        FareSplitter f2 = new FareSplitter("TRIP003");
        System.out.println(Arrays.toString(f2.fareBreakdown()));
    }
}