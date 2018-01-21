package partitionList;

import env.ListNode;


public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        long start = System.nanoTime();
        ListNode l = new ListNode(x);
        ListNode lHead = l;
        ListNode ge = new ListNode(x);
        ListNode geHead = ge;

        while (head != null) {
            if (head.val < x) {
                l.next = head;
                l = head;
            } else {
                ge.next = head;
                ge = head;
            }
            head = head.next;
        }

        ge.next = null;
        l.next = geHead.next;
        System.out.println("Resumption: " + (System.nanoTime() - start));
        return lHead.next;
    }
}
