package oop.assigment_problems;

public class EmployeeHierarchySystem {

    public static class Employee {
        private String empId;
        private String empName;
        private double salary;

        public Employee(String empId, String empName, double salary) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
        }

        public double getSalary() {
            return salary;
        }

        public String getEmpId() {
            return empId;
        }

        public String getEmpName() {
            return empName;
        }
    }

    public static class ManagerEmployee extends Employee {
        private double teamBonus;

        public ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
            super(empId, empName, salary);
            this.teamBonus = teamBonus;
        }

        public double effectiveSalary() {
            return getSalary() + teamBonus;
        }
    }

    public static class InternEmployee extends Employee {
        private double stipendCap;

        public InternEmployee(String empId, String empName, double salary, double stipendCap) {
            super(empId, empName, salary);
            this.stipendCap = stipendCap;
        }

        public double effectiveSalary() {
            return Math.min(getSalary(), stipendCap);
        }
    }

    public static void main(String[] args) {
        Employee plain = new Employee("EMP01", "Alex", 40000.0);
        ManagerEmployee manager = new ManagerEmployee("MGR01", "Bob", 70000.0, 8000.0);
        InternEmployee intern = new InternEmployee("INT01", "Charlie", 12000.0, 10000.0);

        Employee[] employees = {plain, manager, intern};

        for (Employee e : employees) {
            if (e instanceof ManagerEmployee) {
                ManagerEmployee m = (ManagerEmployee) e;
                System.out.printf("Manager effective pay: Rs %.1f%n", m.effectiveSalary());
            } else if (e instanceof InternEmployee) {
                InternEmployee i = (InternEmployee) e;
                System.out.printf("Intern effective pay: Rs %.1f%n", i.effectiveSalary());
            } else {
                System.out.printf("Plain employee pay: Rs %.1f%n", e.getSalary());
            }
        }
    }
}