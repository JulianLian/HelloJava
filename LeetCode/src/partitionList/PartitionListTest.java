package partitionList;

import env.ListNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PartitionListTest {
    PartitionList pl;

    @Before
    public void setUp() {
        pl = new PartitionList();
    }

    @Test
    public void testNullList() {
        assertEquals(null, pl.partition(null, 0));
    }

    @Test
    public void testJust1Element() {
        assertEquals(new ListNode(1), pl.partition(new ListNode(1), 0));
    }

    @Test
    public void testList21X1() {
        ListNode origin = new ListNode(2);
        origin.next = new ListNode(1);
        ListNode expect = new ListNode(1);
        expect.next = new ListNode(2);
        assertEquals(expect, pl.partition(origin, 2));
        assertEquals(origin, pl.partition(origin, 1));
    }
}