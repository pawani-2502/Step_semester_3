package constructors.class_problems;

public final class BoardingPenaltyCalculator {

    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        if (minimumPenaltyPercent < 0) {
            throw new IllegalArgumentException("Minimum penalty percent cannot be negative");
        }
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {
        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException("Fare and minutes late cannot be negative");
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        double tieredPenalty = 0.0;

        int bracket1 = Math.min(minutesLate, 5);
        tieredPenalty += bracket1 * (0.005 * ticketFare);

        if (minutesLate > 5) {
            int bracket2 = Math.min(minutesLate - 5, 10);
            tieredPenalty += bracket2 * (0.010 * ticketFare);
        }

        if (minutesLate > 15) {
            int bracket3 = minutesLate - 15;
            tieredPenalty += bracket3 * (0.020 * ticketFare);
        }

        double floor = (minimumPenaltyPercent / 100.0) * ticketFare;
        return Math.max(tieredPenalty, floor);
    }

    public static void main(String[] args) {
        BoardingPenaltyCalculator calc = new BoardingPenaltyCalculator(1.0);

        System.out.printf("Rs %.1f%n", calc.calculatePenalty(1000, 0));
        System.out.printf("Rs %.1f%n", calc.calculatePenalty(1000, 1));
        System.out.printf("Rs %.1f%n", calc.calculatePenalty(1000, 16));
    }
}