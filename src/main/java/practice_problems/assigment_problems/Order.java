package practice_problems.assigment_problems;

import java.util.NoSuchElementException;

public class Order {

    public static class LineItem {
        private String productName;
        private int quantity;
        private double unitPrice;

        public LineItem(String productName, int quantity, double unitPrice) {
            this.productName = productName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public String getProductName() {
            return productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public double total() {
            return quantity * unitPrice;
        }
    }

    public class Cursor {
        private int index = 0;

        public boolean hasNext() {
            return index < count;
        }

        public LineItem next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return items[index++];
        }

        public void reset() {
            index = 0;
        }
    }

    private LineItem[] items;
    private int count;

    public Order() {
        this(100);
    }

    public Order(int capacity) {
        this.items = new LineItem[capacity];
        this.count = 0;
    }

    public void addItem(LineItem item) {
        if (count == items.length) {
            LineItem[] next = new LineItem[items.length * 2];
            System.arraycopy(items, 0, next, 0, items.length);
            items = next;
        }
        items[count++] = item;
    }

    public void addItem(String productName, int quantity, double unitPrice) {
        addItem(new LineItem(productName, quantity, unitPrice));
    }

    public Cursor getCursor() {
        return new Cursor();
    }

    public double getOrderTotal() {
        double total = 0.0;
        Cursor cursor = getCursor();
        while (cursor.hasNext()) {
            total += cursor.next().total();
        }
        return total;
    }

    private static String formatNumber(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }

    public static void main(String[] args) {
        Order order = new Order();
        order.addItem("Pen", 3, 10.0);
        order.addItem("Book", 2, 250.0);
        order.addItem("Bag", 1, 700.0);

        Cursor cursor = order.getCursor();
        while (cursor.hasNext()) {
            LineItem item = cursor.next();
            System.out.println(item.getProductName() + " " + formatNumber(item.total()));
        }

        System.out.println("Total " + formatNumber(order.getOrderTotal()));
        System.out.println("cursor: inner (reads the order's array)");
        System.out.println("line item: static nested (needs no order)");
    }
}