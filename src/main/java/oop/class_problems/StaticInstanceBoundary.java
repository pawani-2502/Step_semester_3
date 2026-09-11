package oop.class_problems;

public class StaticInstanceBoundary {

    public static class BrokenSrmStudent {
        public static String name;
        public static String regNo;
        public static int attendance;

        public BrokenSrmStudent(String n, String r, int a) {
            name = n;
            regNo = r;
            attendance = a;
        }

        public String getName() {
            return name;
        }
    }

    public static class FixedSrmStudent {
        public static String university = "SRM";
        public static int admissionCount = 0;

        private String name;
        private String regNo;
        private int attendance;

        public FixedSrmStudent(String name, int attendance) {
            admissionCount++;
            this.name = name;
            this.attendance = attendance;
            this.regNo = "RA23110030101" + admissionCount;
        }

        public void printIdCard() {
            System.out.println(name + " | " + regNo);
        }

        public static void printTotalAdmissions() {
            System.out.println("Students admitted so far: " + admissionCount);
        }
    }

    public static void main(String[] args) {
        BrokenSrmStudent b1 = new BrokenSrmStudent("Ravi", "RA01", 80);
        BrokenSrmStudent b2 = new BrokenSrmStudent("Meera", "RA02", 85);

        System.out.println(b1.getName());
        System.out.println(b2.getName());
        System.out.println("(Ravi's data was overwritten - both students now show \"Meera\")\n");

        FixedSrmStudent f1 = new FixedSrmStudent("Ravi", 80);
        FixedSrmStudent f2 = new FixedSrmStudent("Meera", 85);

        f1.printIdCard();
        f2.printIdCard();
        FixedSrmStudent.printTotalAdmissions();
    }
}