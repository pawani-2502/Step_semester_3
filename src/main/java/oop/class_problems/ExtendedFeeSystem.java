package oop.class_problems;

public class ExtendedFeeSystem {

    public static class FeeAccount {
        private String regNo;
        private double totalFee;
        private double amountPaid;

        public FeeAccount(String regNo, double totalFee) {
            this.regNo = regNo;
            this.totalFee = totalFee;
            this.amountPaid = 0.0;
        }

        public void pay(double amount) {
            if (amount <= 0) {
                System.out.println("Payment rejected: Amount must be positive.");
                return;
            }
            this.amountPaid += amount;
        }

        public double getDue() {
            return totalFee - amountPaid;
        }

        public String getRegNo() {
            return regNo;
        }
    }

    public static class HostelFeeAccount extends FeeAccount {
        public HostelFeeAccount(String regNo, double totalFee) {
            super(regNo, totalFee);
        }

        public void payInTwoInstallments(double amount) {
            pay(amount / 2.0);
            pay(amount / 2.0);
        }
    }

    public static class ScholarshipFeeAccount extends FeeAccount {
        private double scholarshipPercent;

        public ScholarshipFeeAccount(String regNo, double totalFee, double scholarshipPercent) {
            super(regNo, totalFee);
            this.scholarshipPercent = scholarshipPercent;
        }

        public double effectiveDue() {
            return getDue() * (1.0 - (scholarshipPercent / 100.0));
        }
    }

    public static void main(String[] args) {
        FeeAccount plain = new FeeAccount("RA001", 150000.0);
        plain.pay(150000.0);

        HostelFeeAccount hostel = new HostelFeeAccount("RA002", 200000.0);
        hostel.pay(60000.0);

        ScholarshipFeeAccount scholarship = new ScholarshipFeeAccount("RA003", 180000.0, 20.0);

        FeeAccount[] accounts = {plain, hostel, scholarship};

        for (FeeAccount acc : accounts) {
            if (acc instanceof ScholarshipFeeAccount) {
                ScholarshipFeeAccount s = (ScholarshipFeeAccount) acc;
                System.out.printf("Scholarship account effective due: Rs %.1f%n", s.effectiveDue());
            } else if (acc instanceof HostelFeeAccount) {
                System.out.printf("Hostel account due: Rs %.1f%n", acc.getDue());
            } else {
                System.out.printf("Plain account due: Rs %.1f%n", acc.getDue());
            }
        }
    }
}