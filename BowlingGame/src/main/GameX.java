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
        for (int frame = 0; frame < 9; frame++) {
            if (isSpare(frame)) {
                score += 10 + rolls[frame + 1][0];
            } else {
                score += scoreOfTwoRollsInFrame(frame);
            }
        }
        if (isSpare(9)){
            score += 10 + rolls[9][2];
        } else {
            score += scoreOfTwoRollsInFrame(9);
        }
        return score;
    }

    private int scoreOfTwoRollsInFrame(int frame) {
        return rolls[frame][0] + rolls[frame][1];
    }

    private boolean isSpare(int frame) {
        return scoreOfTwoRollsInFrame(frame) == 10;
    }

    public void roll(int pins) {
        rolls[frameIndex][rollIndex++] = pins;
        if (rollIndex == 2 && frameIndex < 9) {
            frameIndex++;
            rollIndex = 0;
        }
    }
}
