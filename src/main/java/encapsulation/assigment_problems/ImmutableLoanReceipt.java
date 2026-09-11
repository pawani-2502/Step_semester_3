package encapsulation.assigment_problems;

public class ImmutableLoanReceipt {

    public static class LoanReceipt {
        private final String memberId;
        private final String[] bookIds;

        public LoanReceipt(String memberId, String[] bookIds) {
            if (memberId == null || memberId.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid memberId");
            }
            if (bookIds == null) {
                throw new IllegalArgumentException("Book IDs cannot be null");
            }

            for (String id : bookIds) {
                if (id == null || id.length() != 6 || !id.startsWith("BK-") ||
                    !Character.isDigit(id.charAt(3)) || !Character.isDigit(id.charAt(4)) || !Character.isDigit(id.charAt(5))) {
                    throw new IllegalArgumentException("Invalid book ID format");
                }
            }

            this.memberId = memberId;
            this.bookIds = bookIds.clone();
        }

        public String getMemberId() {
            return memberId;
        }

        public String[] getBookIds() {
            return bookIds.clone();
        }

        public LoanReceipt withCorrectedBookId(int index, String newId) {
            if (newId == null || newId.length() != 6 || !newId.startsWith("BK-") ||
                !Character.isDigit(newId.charAt(3)) || !Character.isDigit(newId.charAt(4)) || !Character.isDigit(newId.charAt(5))) {
                throw new IllegalArgumentException("Invalid book ID format");
            }
            if (index < 0 || index >= bookIds.length) {
                throw new IndexOutOfBoundsException("Invalid index");
            }

            String[] updated = bookIds.clone();
            updated[index] = newId;
            return new LoanReceipt(this.memberId, updated);
        }
    }

    public static class ReferenceOnlyLoanReceipt extends LoanReceipt {
        private final String roomNumber;

        public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
            super(memberId, bookIds);
            this.roomNumber = roomNumber;
        }

        public String getRoomNumber() {
            return roomNumber;
        }
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        if (receipts == null) {
            return "0 processed | 0 null skipped | 0 reference-only | 0 regular";
        }

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return String.format("%d processed | %d null skipped | %d reference-only | %d regular",
                processed, nullSkipped, referenceOnly, regular);
    }

    public static void main(String[] args) {
        try {
            new LoanReceipt("LIB-8841", new String[]{"BK-100", "bad"});
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println(r.getBookIds()[0]);

        LoanReceipt[] batch = {
            new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };
        System.out.println(processNightlyCirculation(batch));
    }
}