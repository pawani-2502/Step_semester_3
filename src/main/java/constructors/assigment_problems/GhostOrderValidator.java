package constructors.assigment_problems;

public class GhostOrderValidator {

    public static class FoodOrder {
        private String studentName;
        private String dishName;
        private boolean delivered;

        public FoodOrder(String studentName, String dishName) {
            if (studentName == null || dishName == null) {
                throw new IllegalArgumentException("Fields cannot be null");
            }

            String sName = studentName.trim();
            String dName = dishName.trim();

            if (sName.isEmpty() || dName.isEmpty()) {
                throw new IllegalArgumentException("Fields cannot be empty or whitespace-only");
            }

            this.studentName = sName;
            this.dishName = dName;
            this.delivered = false;
        }

        public void markDelivered() {
            if (this.delivered) {
                System.out.println("Warning: Order was already delivered.");
                return;
            }
            this.delivered = true;
            System.out.println("Order delivered successfully.");
        }

        public String getStudentName() {
            return studentName;
        }

        public String getDishName() {
            return dishName;
        }

        public boolean isDelivered() {
            return delivered;
        }
    }

    public static void processBatch(String[][] rawOrders) {
        if (rawOrders == null) {
            System.out.println("Valid: 0 | Rejected: 0");
            return;
        }

        int valid = 0;
        int rejected = 0;

        for (String[] order : rawOrders) {
            if (order == null || order.length < 2) {
                rejected++;
                continue;
            }

            try {
                new FoodOrder(order[0], order[1]);
                valid++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.printf("Valid: %d | Rejected: %d%n", valid, rejected);
    }

    public static void main(String[] args) {
        String[][] batch = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };

        processBatch(batch);
    }
}