package oop.class_problems;

public class WorkingAttendanceSystem {

    public static class SrmStudent {
        private String name;
        private String regNo;
        private int attendance;

        public SrmStudent(String name, String regNo, int attendance) {
            this.name = name;
            this.regNo = regNo;
            this.attendance = attendance;
        }

        public void addAttendanceUpdate(int newAttendance) {
            this.attendance = newAttendance;
        }

        public boolean isEligible() {
            return this.attendance >= 75;
        }

        public String getName() {
            return this.name;
        }

        public int getAttendance() {
            return this.attendance;
        }

        public static double classAverage(SrmStudent[] students) {
            if (students == null || students.length == 0) {
                return 0.0;
            }
            int total = 0;
            for (SrmStudent s : students) {
                total += s.getAttendance();
            }
            return (double) total / students.length;
        }
    }

    public static void main(String[] args) {
        SrmStudent[] students = {
            new SrmStudent("Ravi", "RA231100301001", 82),
            new SrmStudent("Anitha", "RA231100301002", 68),
            new SrmStudent("Karthik", "RA231100301003", 91),
            new SrmStudent("Meera", "RA231100301004", 74),
            new SrmStudent("Suresh", "RA231100301005", 60)
        };

        for (SrmStudent s : students) {
            String status = s.isEligible() ? "Eligible" : "Detained";
            System.out.printf("%s - %d%% - %s%n", s.getName(), s.getAttendance(), status);
        }

        double avg = SrmStudent.classAverage(students);
        System.out.printf("Class average: %.1f%%%n", avg);
    }
}