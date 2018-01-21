package env;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) { val = x;}

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
