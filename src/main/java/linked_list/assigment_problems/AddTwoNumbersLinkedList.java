package linked_list.assigment_problems;

public class AddTwoNumbersLinkedList {

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static ListNode addNumbers(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;

        ListNode p1 = a;
        ListNode p2 = b;

        while (p1 != null || p2 != null || carry != 0) {
            int sum = carry;
            if (p1 != null) {
                sum += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                sum += p2.val;
                p2 = p2.next;
            }

            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
        }

        return dummy.next;
    }

    public static String printList(ListNode head) {
        if (head == null) {
            return "(empty list)";
        }
        StringBuilder sb = new StringBuilder();
        ListNode curr = head;
        while (curr != null) {
            sb.append(curr.val);
            if (curr.next != null) {
                sb.append(" -> ");
            }
            curr = curr.next;
        }
        return sb.toString();
    }

    public static ListNode buildList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int v : values) {
            curr.next = new ListNode(v);
            curr = curr.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode a1 = buildList(new int[]{2, 4, 3});
        ListNode b1 = buildList(new int[]{5, 6, 4});
        ListNode res1 = addNumbers(a1, b1);
        System.out.println(printList(res1));

        ListNode a2 = buildList(new int[]{9, 9});
        ListNode b2 = buildList(new int[]{1});
        ListNode res2 = addNumbers(a2, b2);
        System.out.println(printList(res2));
    }
}