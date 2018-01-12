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
        ListNode head = new ListNode(0);
        ListNode curNode = head;

        while (l1 != null && l2 != null) {
            temp = l1.val + l2.val + carry;
            if (temp >= 10) {
                temp -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            curNode.next = new ListNode(temp);
            curNode = curNode.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        l1 = (l1 != null) ? l1 : l2;
        while (l1 != null) {
            temp = l1.val + carry;
            if (temp >= 10) {
                temp -= 10;
                carry = 1;
            } else {
                carry = 0;
            }

            curNode.next = new ListNode(temp);
            curNode = curNode.next;
            l1 = l1.next;
        }

        if (carry != 0){
            curNode.next = new ListNode(carry);
        }

        return head.next;
    }
}
