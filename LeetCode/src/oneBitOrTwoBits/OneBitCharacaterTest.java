package oneBitOrTwoBits;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class OneBitCharacaterTest {
    @Test
    public void testIsOneBitCharacter() throws Exception {
        int[] arri = {1,1,0};
        assertTrue(OneBitCharacater.isOneBitCharacter(arri));
        int[] arri2 = {1,0,1,0};
        assertTrue(!OneBitCharacater.isOneBitCharacter(arri2));
    }

    @Test
    public void testIsOneBitCharacterX() throws Exception {
        int[] arri1 = {1,1,0};
        int[] arri2 = {1,0,1,0};
        int[] arri3 = {1,0,1,1,0};
        assertTrue(OneBitCharacater.isOneBitCharacterX(arri1));
        assertTrue(!OneBitCharacater.isOneBitCharacterX(arri2));
        assertTrue(OneBitCharacater.isOneBitCharacterX(arri3));
    }
}