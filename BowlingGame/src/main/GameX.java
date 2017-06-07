package main;

/**
 * Created by Julian on 2017/6/7.
 */
public class GameX {
    private int[][] rolls = new int[10][3];
    private int frameIndex = 0;
    private int rollIndex = 0;

    public int score() {
        int score = 0;
        for (int i = 0; i < 10; i++) {
            score += rolls[i][0] + rolls[i][1];
        }
        return score;
    }

    public void roll(int pins) {
        rolls[frameIndex][rollIndex++] = pins;
        if (rollIndex == 2) {
            frameIndex++;
            rollIndex = 0;
        }
    }
}
