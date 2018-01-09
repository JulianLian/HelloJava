package addTwoNumbers;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) { val = x;}

    @Override
    public boolean equals(Object obj){
        if (obj == this)
            return true;
        if (obj instanceof ListNode) {
            ListNode otherLn = (ListNode)obj;
            if (otherLn.val == val &&
                    ((otherLn.next != null && otherLn.next.equals(next)) ||
                            (otherLn.next == null && next == null)))
                return true;
        }
        return false;
    }
}
