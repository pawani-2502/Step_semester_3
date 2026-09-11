package oop.assigment_problems;

public class LibraryFineSystem {

    public static class BookIssue {
        private String title;
        private String borrowerName;
        private int daysOverdue;

        public BookIssue(String title, String borrowerName, int daysOverdue) {
            this.title = title;
            this.borrowerName = borrowerName;
            this.daysOverdue = daysOverdue;
        }

        public double fineAmount() {
            if (daysOverdue > 0) {
                return daysOverdue * 5.0;
            }
            return 0.0;
        }

        public boolean isSeverelyOverdue() {
            return daysOverdue > 14;
        }

        public String getTitle() {
            return title;
        }

        public int getDaysOverdue() {
            return daysOverdue;
        }

        public static double totalFineCollected(BookIssue[] issues) {
            if (issues == null) {
                return 0.0;
            }
            double total = 0.0;
            for (BookIssue issue : issues) {
                if (issue != null) {
                    total += issue.fineAmount();
                }
            }
            return total;
        }
    }

    public static void main(String[] args) {
        BookIssue[] books = {
            new BookIssue("Clean Code", "Student1", 18),
            new BookIssue("Effective Java", "Student2", 5),
            new BookIssue("Refactoring", "Student3", 0),
            new BookIssue("DSA Handbook", "Student4", 21),
            new BookIssue("Design Patterns", "Student5", 9)
        };

        for (BookIssue b : books) {
            String status = b.isSeverelyOverdue() ? "Severely overdue" : "OK";
            System.out.printf("%s - %d days - %s%n", b.getTitle(), b.getDaysOverdue(), status);
        }

        double totalFine = BookIssue.totalFineCollected(books);
        System.out.printf("Total fine collected: Rs %.1f%n", totalFine);
    }
}