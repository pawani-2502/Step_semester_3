package constructors.assigment_problems;

public final class SurgeFeeCalculator {

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        if (minimumSurgePercent < 0) {
            throw new IllegalArgumentException("Minimum surge percent cannot be negative");
        }
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Order value and delay minutes cannot be negative");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double tieredFee = 0.0;

        int bracket1 = Math.min(delayMinutes, 5);
        tieredFee += bracket1 * (0.005 * orderValue);

        if (delayMinutes > 5) {
            int bracket2 = Math.min(delayMinutes - 5, 10);
            tieredFee += bracket2 * (0.010 * orderValue);
        }

        if (delayMinutes > 15) {
            int bracket3 = delayMinutes - 15;
            tieredFee += bracket3 * (0.020 * orderValue);
        }

        double floor = (minimumSurgePercent / 100.0) * orderValue;
        return Math.max(tieredFee, floor);
    }

    public static void main(String[] args) {
        SurgeFeeCalculator calc = new SurgeFeeCalculator(1.0);

        System.out.printf("Rs %.1f%n", calc.calculateSurgeFee(500, 0));
        System.out.printf("Rs %.1f%n", calc.calculateSurgeFee(500, 1));
        System.out.printf("Rs %.1f%n", calc.calculateSurgeFee(500, 16));
    }
}