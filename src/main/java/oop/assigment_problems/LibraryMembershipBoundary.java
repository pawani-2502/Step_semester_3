package oop.assigment_problems;

public class LibraryMembershipBoundary {

    public static class BrokenLibraryMember {
        public static String name;
        public static String memberId;
        public static int booksIssued;

        public BrokenLibraryMember(String n, String m, int b) {
            name = n;
            memberId = m;
            booksIssued = b;
        }

        public String getName() {
            return name;
        }
    }

    public static class FixedLibraryMember {
        public static String libraryName = "Central Library";
        public static int memberCount = 0;

        private String name;
        private String memberId;
        private int booksIssued;

        public FixedLibraryMember(String name, int booksIssued) {
            memberCount++;
            this.name = name;
            this.booksIssued = booksIssued;
            this.memberId = "LM-" + (1000 + memberCount);
        }

        public void printMemberCard() {
            System.out.println(name + " | " + memberId);
        }

        public static void printTotalMembers() {
            System.out.println("Total members: " + memberCount);
        }
    }

    public static void main(String[] args) {
        BrokenLibraryMember b1 = new BrokenLibraryMember("Aditi", "LM-01", 2);
        BrokenLibraryMember b2 = new BrokenLibraryMember("Rohan", "LM-02", 3);

        System.out.println(b1.getName());
        System.out.println(b2.getName());
        System.out.println("(Aditi's data was overwritten - both members now show \"Rohan\")\n");

        FixedLibraryMember f1 = new FixedLibraryMember("Aditi", 2);
        FixedLibraryMember f2 = new FixedLibraryMember("Rohan", 3);

        f1.printMemberCard();
        f2.printMemberCard();
        FixedLibraryMember.printTotalMembers();
    }
}