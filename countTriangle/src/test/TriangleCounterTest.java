package test;

import main.TriangleCounter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Julian on 2017/6/21.
 */
public class TriangleCounterTest {
    private TriangleCounter tc;

    @org.junit.Before
    public void setUp() throws Exception {
        tc = new TriangleCounter();
    }

    @Test
    public void testPointsOnline() throws Exception{
        List<String> ls = new ArrayList<>();
        ls.add("abc");
        ls.add("cd");
        tc.setLines(ls);
        assertTrue(tc.isOnLine('a','b'));
        assertTrue(tc.isOnLine('c','b'));
        assertTrue(!tc.isOnLine('d','b'));
        assertTrue(tc.isOnLine('c', 'd'));
        assertTrue(!tc.isOnLine('c', 'd', 'e'));
    }

    @Test
    public void testOneTriangle() throws Exception {
        List<String> ls = new ArrayList<>();
        ls.add("ab");
        ls.add("bc");
        ls.add("ca");
        tc.setLines(ls);
        assertEquals(1, tc.count('a','b','c'));
    }

    @Test
    public void testThreeTrianle() {
        List<String> ls = new ArrayList<>();
        ls.add("ab");
        ls.add("ac");
        ls.add("ad");
        ls.add("bcd");
        tc.setLines(ls);
        assertEquals(3, tc.count('a','b','c','d'));
    }

    @Test
    public void testTrianle() {
        List<String> ls = new ArrayList<>();
        ls.add("aeb");
        ls.add("agc");
        ls.add("afd");
        ls.add("bcd");
        ls.add("efg");
        tc.setLines(ls);
        assertEquals(6, tc.count('a','b','c','d','e','f','g'));
    }

    @Test
    public void test6Trianle() {
        List<String> ls = new ArrayList<>();
        ls.add("ab");
        ls.add("ac");
        ls.add("ad");
        ls.add("ae");
        ls.add("bdec");
        tc.setLines(ls);
        assertEquals(6, tc.count('a','b','c','d','e'));
    }

    @Test
    public void test12Trianle() {
        List<String> ls = new ArrayList<>();
        ls.add("afb");
        ls.add("aic");
        ls.add("agd");
        ls.add("ahe");
        ls.add("bdec");
        ls.add("fghi");
        tc.setLines(ls);
        assertEquals(12, tc.count('a','b','c','d','e','f','g','h','i'));
    }

    @Test
    public void test24Trianle() {
        List<String> ls = new ArrayList<>();
        ls.add("afb");
        ls.add("aic");
        ls.add("agjd");
        ls.add("ahke");
        ls.add("bdec");
        ls.add("fghi");
        ls.add("bjki");
        tc.setLines(ls);
        assertEquals(24, tc.count('a','b','c','d','e','f','g','h','i','j','k'));
    }
}