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
        for (int i = 0; i < 20; i++) {
            gameX.roll(0);
        }
        assertEquals(0, gameX.score());
    }

    @Test
    public void testAllOnes() throws Exception {
        for (int i = 0; i < 20; i++) {
            gameX.roll(1);
        }
        assertEquals(20, gameX.score());
    }
}