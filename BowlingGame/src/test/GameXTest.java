package test;

import main.GameX;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Julian on 2017/6/7.
 */
public class GameXTest {
    private GameX gameX;

    @Before
    public void setUp() throws Exception {
        gameX = new GameX();
    }

    @Test
    public void testAllZeroes() throws Exception {
        rollMany(20, 0);
        assertEquals(0, gameX.score());
    }

    private void rollMany(int times, int pins) {
        for (int i = 0; i < times; i++) {
            gameX.roll(pins);
        }
    }

    @Test
    public void testAllOnes() throws Exception {
        rollMany(20, 1);
        assertEquals(20, gameX.score());
    }

    @Test
    public void testOneSpare() throws Exception {
        rollSpare();
        gameX.roll(3);
        rollMany(17, 0);
        assertEquals(16, gameX.score());
    }

    @Test
    public void testOneStrike() throws Exception {
        rollStrike();
        gameX.roll(4);
        gameX.roll(3);
        rollMany(16, 0);
        assertEquals(24, gameX.score());
    }

    private void rollStrike() {
        gameX.roll(10);
    }

    private void rollSpare() {
        gameX.roll(6);
        gameX.roll(4);
    }

    @Test
    public void testPerfectGame() throws Exception {
        rollMany(12, 10);
        assertEquals(300, gameX.score());
    }
}