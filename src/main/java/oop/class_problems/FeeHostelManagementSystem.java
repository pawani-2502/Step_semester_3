package oop.class_problems;

public class FeeHostelManagementSystem {

    public static class FeeAccount {
        private String regNo;
        private double totalFee;
        private double amountPaid;

        public FeeAccount(String regNo, double totalFee) {
            this.regNo = regNo;
            this.totalFee = totalFee;
            this.amountPaid = 0.0;
        }

        public boolean pay(double amount) {
            if (amount <= 0) {
                return false;
            }
            this.amountPaid += amount;
            return true;
        }

        public double getDue() {
            return totalFee - amountPaid;
        }
    }

    public static class HostelFeeAccount extends FeeAccount {
        public HostelFeeAccount(String regNo, double totalFee) {
            super(regNo, totalFee);
        }
    }

    public static class HostelRoom {
        private String roomNo;
        private int beds;
        private int occupied;

        public HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        public boolean allot() {
            if (occupied < beds) {
                occupied++;
                return true;
            }
            return false;
        }

        public String getRoomNo() {
            return roomNo;
        }
    }

    public static class SrmStudent {
        public static int totalStudents = 0;

        private String name;
        private String regNo;
        private HostelFeeAccount feeAccount;
        private HostelRoom room;

        public SrmStudent(String name, String regNo, HostelFeeAccount feeAccount, HostelRoom room) {
            this.name = name;
            this.regNo = regNo;
            this.feeAccount = feeAccount;
            this.room = room;
            totalStudents++;
        }

        public String fullStatus() {
            String roomDisplay = (room != null) ? room.getRoomNo() : "unallotted";
            double due = (feeAccount != null) ? feeAccount.getDue() : 0.0;
            return String.format("%s | Due: Rs %.1f | Room: %s", name, due, roomDisplay);
        }
    }

    public static void main(String[] args) {
        HostelRoom r1 = new HostelRoom("C-214", 3, 2);
        HostelRoom r2 = new HostelRoom("C-507", 2, 1);

        HostelFeeAccount f1 = new HostelFeeAccount("RA01", 200000.0);
        f1.pay(60000.0);

        HostelFeeAccount f2 = new HostelFeeAccount("RA02", 200000.0);
        f2.pay(20000.0);

        HostelFeeAccount f3 = new HostelFeeAccount("RA03", 200000.0);
        f3.pay(-5000.0);

        r1.allot();
        r2.allot();

        SrmStudent s1 = new SrmStudent("Ravi", "RA01", f1, r1);
        SrmStudent s2 = new SrmStudent("Anitha", "RA02", f2, r2);
        SrmStudent s3 = new SrmStudent("Karthik", "RA03", f3, null);

        System.out.println(s1.fullStatus());
        System.out.println(s2.fullStatus());
        System.out.println(s3.fullStatus());
        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}