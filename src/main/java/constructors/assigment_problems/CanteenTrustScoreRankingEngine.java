package constructors.assigment_problems;

public class CanteenTrustScoreRankingEngine {

    public static class Canteen {
        private String canteenCode;
        private String canteenName;
        private int trustScore;

        public Canteen(String canteenCode, String canteenName, int trustScore) {
            this.canteenCode = canteenCode;
            this.canteenName = canteenName;
            this.trustScore = trustScore;
        }

        public Canteen(String canteenCode, String canteenName) {
            this(canteenCode, canteenName, 3);
        }

        public int compareTo(Canteen other) {
            if (other == null) {
                return 1;
            }
            if (this.trustScore != other.trustScore) {
                return other.trustScore - this.trustScore;
            }
            int codeCompare = this.canteenCode.compareToIgnoreCase(other.canteenCode);
            if (codeCompare != 0) {
                return codeCompare;
            }
            return this.canteenName.compareToIgnoreCase(other.canteenName);
        }

        public String getCanteenCode() {
            return canteenCode;
        }

        public String getCanteenName() {
            return canteenName;
        }

        public int getTrustScore() {
            return trustScore;
        }
    }

    public static Canteen[] rankCanteens(Canteen[] canteens) {
        if (canteens == null || canteens.length <= 1) {
            return canteens;
        }

        Canteen[] sorted = canteens.clone();
        for (int i = 0; i < sorted.length - 1; i++) {
            for (int j = 0; j < sorted.length - 1 - i; j++) {
                if (sorted[j].compareTo(sorted[j + 1]) > 0) {
                    Canteen temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }
        return sorted;
    }

    public static void main(String[] args) {
        Canteen[] canteens = {
            new Canteen("HB3-C", "Spice Junction", 3),
            new Canteen("hb1-c", "Grand Mess", 5),
            new Canteen("HB2-C", "Southern Treats")
        };

        Canteen[] ranked = rankCanteens(canteens);

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < ranked.length; i++) {
            sb.append("\"").append(ranked[i].getCanteenCode()).append("\"");
            if (i < ranked.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}