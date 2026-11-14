package linked_list.assigment_problems;

public class BrowserHistory {

    public static class Node {
        public String url;
        public Node prev;
        public Node next;

        public Node(String url) {
            this.url = url;
            this.prev = null;
            this.next = null;
        }
    }

    private Node current;

    public BrowserHistory(String homepage) {
        this.current = new Node(homepage);
    }

    public void visit(String url) {
        Node newNode = new Node(url);
        current.next = newNode;
        newNode.prev = current;
        current = newNode;
    }

    public String back(int steps) {
        while (steps > 0 && current.prev != null) {
            current = current.prev;
            steps--;
        }
        return current.url;
    }

    public String forward(int steps) {
        while (steps > 0 && current.next != null) {
            current = current.next;
            steps--;
        }
        return current.url;
    }

    public String getCurrentUrl() {
        return current.url;
    }
}