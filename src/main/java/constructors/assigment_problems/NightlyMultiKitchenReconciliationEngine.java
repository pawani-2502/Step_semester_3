package constructors.assigment_problems;

public class NightlyMultiKitchenReconciliationEngine {

    public static class DeliveryAccount {
        protected static double defaultOrderValue;

        static {
            defaultOrderValue = 250.0;
        }

        private String studentId;
        private double orderValue;

        public DeliveryAccount(String studentId, double orderValue) {
            this.studentId = studentId;
            this.orderValue = orderValue;
        }

        public DeliveryAccount(String studentId) {
            this(studentId, defaultOrderValue);
        }

        public final double calculateSurgeFee(int delayMinutes) {
            if (delayMinutes <= 0) {
                return 0.0;
            }
            return delayMinutes * 2.5;
        }

        public String getStudentId() {
            return studentId;
        }

        public double getOrderValue() {
            return orderValue;
        }
    }

    public static class Premium extends DeliveryAccount {
        public Premium(String studentId, double orderValue) {
            super(studentId, orderValue);
        }
    }

    public static void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        if (account == null) {
            return;
        }
        account.calculateSurgeFee(delayMinutes);
    }

    public static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        if (accounts == null) {
            System.out.println("0 processed | 0 null skipped | 0 premium | 0 regular | grand total surge fees = Rs 0.0");
            return;
        }

        int processed = 0;
        int nullSkipped = 0;
        int premiumCount = 0;
        int regularCount = 0;
        double totalSurgeFees = 0.0;

        for (int i = 0; i < accounts.length; i++) {
            DeliveryAccount acc = accounts[i];
            if (acc == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            int delay = (delayMinutesArray != null && i < delayMinutesArray.length) ? delayMinutesArray[i] : 0;
            double fee = acc.calculateSurgeFee(delay);
            totalSurgeFees += fee;

            if (acc instanceof Premium) {
                premiumCount++;
            } else {
                regularCount++;
            }
        }

        System.out.printf("%d processed | %d null skipped | %d premium | %d regular | grand total surge fees = Rs %.1f%n",
                processed, nullSkipped, premiumCount, regularCount, totalSurgeFees);
    }

    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
            new Premium("STU001", 500.0),
            null,
            new DeliveryAccount("STU002", 300.0)
        };
        double[] amounts = {500.0, 400.0, 300.0};
        int[] delayMinutesArray = {10, 5, 0};

        processBatch(accounts, amounts, delayMinutesArray);
    }
}