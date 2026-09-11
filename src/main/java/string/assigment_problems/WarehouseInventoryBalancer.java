package string.assigment_problems;

/**
 * Problem 4: The Warehouse Inventory Balancer
 *
 * Scenario:
 * Checks whether item totals in Section A and Section B are balanced and
 * identifies the single highest quantity item across the warehouse.
 */
public class WarehouseInventoryBalancer {

    /**
     * Computes totals for Section A and Section B, evaluates balance status,
     * and locates the item with the highest quantity.
     *
     * @param sectionA array of quantities for Section A
     * @param sectionB array of quantities for Section B (equal length)
     */
    public static void analyzeInventory(int[] sectionA, int[] sectionB) {
        if (sectionA == null || sectionB == null) {
            System.out.println("Invalid input arrays.");
            return;
        }

        int totalA = 0;
        int totalB = 0;
        int highestQty = Integer.MIN_VALUE;
        String highestSection = "";
        int highestItemIndex = -1;

        // Process Section A
        for (int i = 0; i < sectionA.length; i++) {
            totalA += sectionA[i];
            if (sectionA[i] > highestQty) {
                highestQty = sectionA[i];
                highestSection = "Section A";
                highestItemIndex = i + 1; // 1-based item number
            }
        }

        // Process Section B
        for (int i = 0; i < sectionB.length; i++) {
            totalB += sectionB[i];
            if (sectionB[i] > highestQty) {
                highestQty = sectionB[i];
                highestSection = "Section B";
                highestItemIndex = i + 1; // 1-based item number
            }
        }

        String status = (totalA == totalB) ? "Balanced" : "Not Balanced";

        System.out.printf("Section A Total: %d | Section B Total: %d | Status: %s | Highest Quantity: %d (%s, Item %d)%n",
                totalA, totalB, status, highestQty, highestSection, highestItemIndex);
    }

    public static void main(String[] args) {
        // Sample Test Case
        int[] secA = {20, 15, 30};
        int[] secB = {25, 10, 30};

        System.out.println("Test Case:");
        analyzeInventory(secA, secB);
    }
}