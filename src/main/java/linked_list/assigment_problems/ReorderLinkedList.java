package linked_list.assigment_problems;

public class ReorderLinkedList {

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

    public static void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondHalf = slow.next;
        slow.next = null;

        ListNode prev = null;
        ListNode curr = secondHalf;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        secondHalf = prev;

        ListNode firstHalf = head;
        while (firstHalf != null && secondHalf != null) {
            ListNode next1 = firstHalf.next;
            ListNode next2 = secondHalf.next;

            firstHalf.next = secondHalf;
            if (next1 == null) {
                break;
            }
            secondHalf.next = next1;

            firstHalf = next1;
            secondHalf = next2;
        }
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
        ListNode l1 = buildList(new int[]{1, 2, 3, 4});
        reorderList(l1);
        System.out.println(printList(l1));

        ListNode l2 = buildList(new int[]{1, 2, 3, 4, 5});
        reorderList(l2);
        System.out.println(printList(l2));
    }
}