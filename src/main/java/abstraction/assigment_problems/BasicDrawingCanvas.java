package abstraction.assigment_problems;

public class BasicDrawingCanvas {

    public static abstract class Shape {
        private static int counter = 0;
        private final String shapeId;
        protected double dimension;

        public Shape() {
            counter++;
            this.shapeId = "SHP-" + counter;
        }

        public abstract double calculateArea();

        public void scale(double factor) {
            this.dimension *= factor;
        }

        public void scale(double xFactor, double yFactor) {
            scale(xFactor);
            scale(yFactor);
        }

        public String getShapeId() {
            return shapeId;
        }
    }

    public static class CircleShape extends Shape {
        public CircleShape(double radius) {
            super();
            this.dimension = radius;
        }

        @Override
        public double calculateArea() {
            return Math.PI * dimension * dimension;
        }

        public double getRadius() {
            return dimension;
        }
    }

    public static class SquareShape extends Shape {
        public SquareShape(double side) {
            super();
            this.dimension = side;
        }

        @Override
        public double calculateArea() {
            return dimension * dimension;
        }

        public double getSide() {
            return dimension;
        }
    }

    public static void printArea(Shape s) {
        if (s != null) {
            System.out.println(s.calculateArea());
        }
    }

    public static void main(String[] args) {
        CircleShape c = new CircleShape(5.0);
        System.out.printf("%.2f%n", c.calculateArea());

        SquareShape sq = new SquareShape(4.0);
        System.out.println(sq.calculateArea());

        sq.scale(2.0);
        System.out.println(sq.calculateArea());

        printArea(c);
    }
}