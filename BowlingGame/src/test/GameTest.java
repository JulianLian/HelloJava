package test;

import main.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Julian on 2017/6/3.
 */
public class GameTest {
    Game g;

    @Before
    public void setUp() throws Exception {
        g = new Game();
    }

    private void rollMany(int times, int pins) {
        for (int i = 0; i < times; i++) {
            g.roll(pins);
        }
    }

    @Test
    public void testAllZeroes() throws Exception {
        rollMany(20, 0);
        assertEquals(0, g.score());
    }

    @Test
    public void testAllOnes() throws Exception {
        rollMany(20,1);
        assertEquals(20, g.score());
    }

    @Test
    public void testOneSpare() throws Exception {
        rollSpare();
        g.roll(3);
        rollMany(17,0);
        assertEquals(16, g.score());
    }

    private void rollSpare() {
        g.roll(6);
        g.roll(4);
    }
}
