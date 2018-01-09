package addTwoNumbers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddTwoNumbersTest {
    AddTwoNumbers testObject;
    @Before
    public void setUp() {
        testObject = new AddTwoNumbers();
    }

    @Test
    public void testAddTwoNumbers1() {
        ListNode num1 = new ListNode(0);
        ListNode num2 = new ListNode(0);
        ListNode expect = new ListNode(0);

        assertEquals(expect, testObject.addTwoNumbers(num1, num2));
    }

    @Test
    public void testAddTwoNumbers2() {
        ListNode num1 = new ListNode(1);
        ListNode num2 = new ListNode(9);
        ListNode expect = new ListNode(0);
        expect.next = new ListNode(1);

        assertEquals(expect, testObject.addTwoNumbers(num1, num2));
    }

    @Test
    public void testAddTwoNumbers3() {
        ListNode num1 = new ListNode(1);
        ListNode num2 = new ListNode(9);
        num2.next = new ListNode(3);
        ListNode expect = new ListNode(0);
        expect.next = new ListNode(4);

        assertEquals(expect, testObject.addTwoNumbers(num1, num2));
    }

    @Test
    public void testAddTwoNumbers4() {
        ListNode num1 = new ListNode(1);
        num1.next = new ListNode(3);
        ListNode num2 = new ListNode(9);
        num2.next = new ListNode(6);
        ListNode expect = new ListNode(0);
        expect.next = new ListNode(0);
        expect.next.next = new ListNode(1);

        assertEquals(expect, testObject.addTwoNumbers(num1, num2));
    }
}