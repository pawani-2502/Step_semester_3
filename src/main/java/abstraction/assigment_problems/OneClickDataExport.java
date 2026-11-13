package abstraction.assigment_problems;

public class OneClickDataExport {

    private static int totalExports = 0;

    public interface Exportable {
        String exportData();
    }

    public static class ReportGenerator implements Exportable {
        private String reportName;

        public ReportGenerator(String reportName) {
            this.reportName = reportName;
        }

        @Override
        public String exportData() {
            totalExports++;
            return "Exported report: " + reportName;
        }

        public String getReportName() {
            return reportName;
        }
    }

    public static class UserProfile implements Exportable {
        private String username;

        public UserProfile(String username) {
            this.username = username;
        }

        @Override
        public String exportData() {
            totalExports++;
            return "Exported profile: " + username;
        }

        public String getUsername() {
            return username;
        }
    }

    public static int getTotalExports() {
        return totalExports;
    }

    public static void resetExports() {
        totalExports = 0;
    }

    public static void exportAll(Exportable[] items) {
        if (items == null) {
            return;
        }
        for (Exportable item : items) {
            if (item != null) {
                System.out.println(item.exportData());
            }
        }
    }

    public static void main(String[] args) {
        ReportGenerator r = new ReportGenerator("Sales Q1");
        System.out.println(r.exportData());

        UserProfile u = new UserProfile("jane_doe");
        System.out.println(u.exportData());

        resetExports();

        Exportable ref = r;
        exportAll(new Exportable[]{ref, u});
        System.out.println(getTotalExports());
    }
}