package inheritance.class_problems;

public class NightlyTicketAnnouncer {

    public static class EventTicket {
        protected String attendeeId;
        protected double basePrice;
        protected double balanceDue;

        public EventTicket(String attendeeId, double basePrice) {
            this.attendeeId = attendeeId;
            this.basePrice = basePrice;
            this.balanceDue = basePrice;
        }

        public EventTicket(double basePrice) {
            this("TCK-DEFAULT", basePrice);
        }

        public void pay(double amount) {
            this.balanceDue -= amount;
        }

        public double getBalanceDue() {
            return this.balanceDue;
        }

        public String printTicket() {
            return "Standard | Balance: " + balanceDue;
        }
    }

    public static class WorkshopTicket extends EventTicket {
        private String track;

        public WorkshopTicket(String attendeeId, double basePrice, String track) {
            super(attendeeId, basePrice);
            this.track = track;
        }

        public WorkshopTicket(double basePrice, String track) {
            super(basePrice);
            this.track = track;
        }

        public String getTrack() {
            return track;
        }

        @Override
        public String printTicket() {
            return "Workshop | Track: " + track + " | Balance: " + balanceDue;
        }
    }

    public static String batchPrint(EventTicket[] tickets) {
        if (tickets == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (EventTicket ticket : tickets) {
            if (ticket != null) {
                sb.append(ticket.printTicket());
                if (ticket instanceof WorkshopTicket) {
                    WorkshopTicket wt = (WorkshopTicket) ticket;
                    sb.append(" [Track via downcast: ").append(wt.getTrack()).append("]");
                }
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        EventTicket[] tickets = {
            new EventTicket(500),
            new WorkshopTicket(1200, "AI/ML")
        };
        System.out.println(batchPrint(tickets));

        try {
            EventTicket plain = new EventTicket(500);
            WorkshopTicket bad = (WorkshopTicket) plain;
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }
    }
}