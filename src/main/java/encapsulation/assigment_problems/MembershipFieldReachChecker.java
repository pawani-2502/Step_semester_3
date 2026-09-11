package encapsulation.assigment_problems;

public class MembershipFieldReachChecker {

    public static class LibraryMember {
        private String membershipId;
        String branchCode;
        protected double finesOwed;
        public String displayName;

        public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
            if (membershipId == null || membershipId.trim().length() < 4) {
                throw new IllegalArgumentException("Invalid membershipId");
            }
            this.membershipId = membershipId.trim();
            this.branchCode = branchCode;
            this.finesOwed = finesOwed;
            this.displayName = displayName;
        }

        public String getMembershipId() {
            return membershipId;
        }

        public String getBranchCode() {
            return branchCode;
        }

        public double getFinesOwed() {
            return finesOwed;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        String mod = fieldModifier.trim().toLowerCase();
        String ctx = accessorContext.trim().toUpperCase();

        if (mod.equals("public")) {
            return "ALLOWED";
        }

        if (ctx.equals("SAME_CLASS")) {
            return "ALLOWED";
        }

        if (mod.equals("private")) {
            return "DENIED";
        }

        if (ctx.equals("SAME_PACKAGE")) {
            if (mod.equals("default") || mod.equals("protected")) {
                return "ALLOWED";
            }
        }

        if (ctx.equals("DIFFERENT_PACKAGE")) {
            return "DENIED";
        }

        return "DENIED";
    }

    public static String summarizeByModifier(String[][] attempts) {
        int privAllowed = 0, privDenied = 0;
        int defAllowed = 0, defDenied = 0;
        int protAllowed = 0, protDenied = 0;
        int pubAllowed = 0, pubDenied = 0;

        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt != null && attempt.length >= 2) {
                    String mod = attempt[0].trim().toLowerCase();
                    String result = classifyAccess(attempt[0], attempt[1]);
                    boolean isAllowed = result.equals("ALLOWED");

                    switch (mod) {
                        case "private":
                            if (isAllowed) privAllowed++; else privDenied++;
                            break;
                        case "default":
                            if (isAllowed) defAllowed++; else defDenied++;
                            break;
                        case "protected":
                            if (isAllowed) protAllowed++; else protDenied++;
                            break;
                        case "public":
                            if (isAllowed) pubAllowed++; else pubDenied++;
                            break;
                    }
                }
            }
        }

        return String.format("private: %d allowed / %d denied | default: %d allowed / %d denied | protected: %d allowed / %d denied | public: %d allowed / %d denied",
                privAllowed, privDenied, defAllowed, defDenied, protAllowed, protDenied, pubAllowed, pubDenied);
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] batch = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(summarizeByModifier(batch));

        try {
            new LibraryMember("LB9", "BR1", 0, "Priya Nair");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }
    }
}