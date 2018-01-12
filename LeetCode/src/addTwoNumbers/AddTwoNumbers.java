package addTwoNumbers;

public class AddTwoNumbers {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        int temp;
        ListNode preNode = l1;
        ListNode head = preNode;

        do {
            temp = l1.val + l2.val + carry;
            if (temp >= 10) {
                l1.val = temp - 10;
                carry = 1;
            } else {
                l1.val = temp;
                carry = 0;
            }

            preNode = l1;
            l1 = l1.next;
            l2 = l2.next;
        } while (l1 != null && l2 != null);

        l1 = (l1 != null) ? l1 : l2;
        preNode.next = l1;
        while (l1 != null && carry != 0) {
            temp = l1.val + carry;
            if (temp >= 10) {
                l1.val = temp - 10;
                carry = 1;
            } else {
                l1.val = temp;
                carry = 0;
            }

            preNode = l1;
            l1 = l1.next;
        }

        if (carry != 0){
            preNode.next = new ListNode(carry);
            preNode.next.next = null;
        }

        return head;
    }
}
