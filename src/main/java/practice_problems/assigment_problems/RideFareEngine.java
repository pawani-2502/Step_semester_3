package practice_problems.assigment_problems;

public class RideFareEngine {

    public static abstract class Ride {
        protected double distance;

        public Ride(double distance) {
            this.distance = distance;
        }

        public abstract double calculateFare();

        public abstract String getType();
    }

    public static class Auto extends Ride {
        public Auto(double distance) {
            super(distance);
        }

        @Override
        public double calculateFare() {
            return 30.0 + 15.0 * distance;
        }

        @Override
        public String getType() {
            return "Auto";
        }
    }

    public static class Cab extends Ride {
        private double surgeFactor;

        public Cab(double distance, double surgeFactor) {
            super(distance);
            this.surgeFactor = surgeFactor;
        }

        @Override
        public double calculateFare() {
            return (50.0 + 20.0 * distance) * surgeFactor;
        }

        @Override
        public String getType() {
            return "Cab";
        }
    }

    public static class Bike extends Ride {
        private int waitingMinutes;

        public Bike(double distance, int waitingMinutes) {
            super(distance);
            this.waitingMinutes = waitingMinutes;
        }

        @Override
        public double calculateFare() {
            return 20.0 + 10.0 * distance + 2.0 * waitingMinutes;
        }

        @Override
        public String getType() {
            return "Bike";
        }
    }

    private static String formatNumber(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }

    public static void main(String[] args) {
        Ride[] rides = new Ride[] {
            new Auto(4.0),
            new Cab(6.0, 1.5),
            new Bike(3.0, 5)
        };

        double total = 0.0;
        for (Ride ride : rides) {
            double fare = ride.calculateFare();
            total += fare;
            System.out.println(ride.getType() + ": " + formatNumber(fare));
        }
        System.out.println("Total: " + formatNumber(total));
    }
}