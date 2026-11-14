package linked_list.assigment_problems;

public class IntersectionNodeLinkedList {

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode findIntersection(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return null;
        }

        int lenA = getLength(a);
        int lenB = getLength(b);

        ListNode currA = a;
        ListNode currB = b;

        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB; i++) {
                currA = currA.next;
            }
        } else if (lenB > lenA) {
            for (int i = 0; i < lenB - lenA; i++) {
                currB = currB.next;
            }
        }

        while (currA != null && currB != null) {
            if (currA == currB) {
                return currA;
            }
            currA = currA.next;
            currB = currB.next;
        }

        return null;
    }

    private static int getLength(ListNode head) {
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        return length;
    }

    public static void main(String[] args) {
        ListNode common = new ListNode(8, new ListNode(4, new ListNode(5)));

        ListNode a1 = new ListNode(4, new ListNode(1, common));
        ListNode b1 = new ListNode(5, new ListNode(6, new ListNode(1, common)));

        ListNode res1 = findIntersection(a1, b1);
        if (res1 != null) {
            System.out.println("Node with value " + res1.val);
        } else {
            System.out.println("null");
        }

        ListNode a2 = new ListNode(2, new ListNode(6, new ListNode(4)));
        ListNode b2 = new ListNode(1, new ListNode(5));

        ListNode res2 = findIntersection(a2, b2);
        if (res2 != null) {
            System.out.println("null");
        } else {
            System.out.println("null");
        }
    }
}