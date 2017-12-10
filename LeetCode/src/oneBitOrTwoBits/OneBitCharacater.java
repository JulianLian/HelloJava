package oneBitOrTwoBits;

public class OneBitCharacater {
    public static boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        while (i < bits.length-1)
            i += bits[i]+1;
        return i == bits.length-1;
    }

    public static boolean isOneBitCharacterX(int[] bits) {
        int i = bits.length-1;
        while (i > 0 && bits[i-1] != 0 )
            i--;

        return (bits.length-1 - i)%2 == 0;
    }
}
