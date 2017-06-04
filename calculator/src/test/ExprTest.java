package test;

import expr.Expr;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Julian on 2017/5/28.
 */
public class ExprTest {
    Expr cal;

    @Before
    public void setUp() throws Exception {
        cal = new Expr();
    }

    @Test
    public void exprTest( ) {
        assertEquals(1, cal.expr("1"));
        assertEquals(9, cal.expr("9"));
    }

    @Test
    public void addTest() {
        assertEquals(1+2, cal.expr("1+2"));
        assertEquals(1+2+3, cal.expr("1+2+3"));
        assertEquals(1+2+3+4, cal.expr("1+2+3+4"));
        assertEquals(1+2-3-4, cal.expr("1+2-3-4"));
    }

    @Test
    public void mulTest() {
        assertEquals(2*3, cal.expr("2*3"));
        assertEquals(2*3*4*5, cal.expr("2*3*4*5"));
        assertEquals(2*3*4/2/3, cal.expr("2*3*4/2/3"));
    }

    @Test
    public void addMulTest() {
        assertEquals(1+2*3, cal.expr("1+2*3"));
        assertEquals(2*3+4/2, cal.expr("2*3+4/2"));
    }
}