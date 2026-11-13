package inheritance.class_problems;

public class TicketHierarchyFoundation {

    public static class EventTicket {
        protected String attendeeId;
        protected double basePrice;
        protected double balanceDue;

        public EventTicket(String attendeeId, double basePrice) {
            if (attendeeId == null || attendeeId.trim().isEmpty() || attendeeId.length() < 4 || attendeeId.trim().length() < 4) {
                throw new IllegalArgumentException("Invalid attendeeId");
            }
            this.attendeeId = attendeeId.trim();
            this.basePrice = basePrice;
            this.balanceDue = basePrice;
        }

        public void pay(double amount) {
            this.balanceDue -= amount;
        }

        public double getBalanceDue() {
            return this.balanceDue;
        }

        public String getAttendeeId() {
            return attendeeId;
        }

        public double getBasePrice() {
            return basePrice;
        }
    }

    public static class WorkshopTicket extends EventTicket {
        private String track;

        public WorkshopTicket(String attendeeId, double basePrice, String track) {
            super(attendeeId, basePrice);
            this.track = track;
        }

        public String getTrack() {
            return track;
        }
    }

    public static String registerBatch(String[] attendeeIds, double basePrice) {
        if (attendeeIds == null) {
            return "Registered: 0 | Rejected: 0";
        }
        int registered = 0;
        int rejected = 0;
        for (String id : attendeeIds) {
            try {
                new EventTicket(id, basePrice);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return String.format("Registered: %d | Rejected: %d", registered, rejected);
    }

    public static void main(String[] args) {
        try {
            new EventTicket("ST1", 500);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        WorkshopTicket w = new WorkshopTicket("STU2", 1200, "AI/ML");
        w.pay(500);
        System.out.println(w.getBalanceDue());

        String[] batch = {"STU1", "ST1", "STU2", " ", "STU3"};
        System.out.println(registerBatch(batch, 500));
    }
}