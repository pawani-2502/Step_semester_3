package object_modeling.assigment_problems;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ElectiveSeatRush {

    public interface StudentType {
        String getTypeName();
        int getMaxCredits();
    }

    public static class RegularStudentType implements StudentType {
        @Override
        public String getTypeName() {
            return "Regular";
        }

        @Override
        public int getMaxCredits() {
            return 24;
        }
    }

    public static class HonorsStudentType implements StudentType {
        @Override
        public String getTypeName() {
            return "Honors";
        }

        @Override
        public int getMaxCredits() {
            return 28;
        }
    }

    public static class ExchangeStudentType implements StudentType {
        @Override
        public String getTypeName() {
            return "Exchange";
        }

        @Override
        public int getMaxCredits() {
            return 20;
        }
    }

    public static class Student {
        private String name;
        private StudentType studentType;
        private int currentCredits;

        public Student(String name, StudentType studentType, int currentCredits) {
            this.name = name;
            this.studentType = studentType;
            this.currentCredits = currentCredits;
        }

        public String getName() {
            return name;
        }

        public StudentType getStudentType() {
            return studentType;
        }

        public int getCurrentCredits() {
            return currentCredits;
        }

        public void addCredits(int credits) {
            this.currentCredits += credits;
        }

        public void dropCredits(int credits) {
            this.currentCredits -= credits;
        }

        public boolean canAddCredits(int credits) {
            return (this.currentCredits + credits) <= studentType.getMaxCredits();
        }
    }

    public static class Elective {
        private String name;
        private int credits;
        private int capacity;
        private List<Student> enrolled;
        private Queue<Student> waitlist;

        public Elective(String name, int credits, int capacity) {
            this.name = name;
            this.credits = credits;
            this.capacity = capacity;
            this.enrolled = new ArrayList<>();
            this.waitlist = new ArrayDeque<>();
        }

        public String getName() {
            return name;
        }

        public int getCredits() {
            return credits;
        }

        public int getCapacity() {
            return capacity;
        }

        public boolean isEnrolled(Student s) {
            return enrolled.contains(s);
        }

        public boolean isWaitlisted(Student s) {
            return waitlist.contains(s);
        }

        public String enroll(Student student) {
            if (isEnrolled(student) || isWaitlisted(student)) {
                return String.format("%s is already associated with %s.", student.getName(), name);
            }

            if (!student.canAddCredits(credits)) {
                return String.format("Enrollment failed: %s would exceed the %s credit limit (%d/%d).",
                        student.getName(),
                        student.getStudentType().getTypeName(),
                        student.getCurrentCredits() + credits,
                        student.getStudentType().getMaxCredits());
            }

            if (enrolled.size() < capacity) {
                enrolled.add(student);
                student.addCredits(credits);
                return String.format("%s enrolled in %s (credits: %d/%d).",
                        student.getName(),
                        name,
                        student.getCurrentCredits(),
                        student.getStudentType().getMaxCredits());
            } else {
                waitlist.add(student);
                return String.format("%s is full. %s added to waitlist (position %d).",
                        name,
                        student.getName(),
                        waitlist.size());
            }
        }

        public void drop(Student student) {
            if (!enrolled.contains(student)) {
                System.out.printf("%s is not enrolled in %s.%n", student.getName(), name);
                return;
            }

            enrolled.remove(student);
            student.dropCredits(credits);
            System.out.printf("%s dropped %s (credits: %d/%d).%n",
                    student.getName(),
                    name,
                    student.getCurrentCredits(),
                    student.getStudentType().getMaxCredits());

            while (!waitlist.isEmpty()) {
                Student candidate = waitlist.poll();
                if (candidate.canAddCredits(credits)) {
                    enrolled.add(candidate);
                    candidate.addCredits(credits);
                    System.out.printf("%s promoted from waitlist and enrolled in %s (credits: %d/%d).%n",
                            candidate.getName(),
                            name,
                            candidate.getCurrentCredits(),
                            candidate.getStudentType().getMaxCredits());
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Elective cloudComputing = new Elective("Cloud Computing", 4, 2);

        Student asha = new Student("Asha", new RegularStudentType(), 20);
        Student ravi = new Student("Ravi", new HonorsStudentType(), 22);
        Student neha = new Student("Neha", new ExchangeStudentType(), 12);
        Student kiran = new Student("Kiran", new RegularStudentType(), 22);

        System.out.println(cloudComputing.enroll(asha));
        System.out.println(cloudComputing.enroll(ravi));
        System.out.println(cloudComputing.enroll(neha));
        System.out.println(cloudComputing.enroll(kiran));

        cloudComputing.drop(asha);
    }
}