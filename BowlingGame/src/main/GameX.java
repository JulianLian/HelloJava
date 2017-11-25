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
            if (isStrike(frame)) {
                score += strikeAndBonus(frame);
            } else if (isSpare(frame)) {
                score += spareAndBonus(frame);
            } else {
                score += scoreOfTwoRollsInFrame(frame);
            }
        }
        if (isStrike(9)) {
            score += 10 + rolls[9][1] + rolls[9][2];
        } else if (isSpare(9)){
            score += 10 + rolls[9][2];
        } else {
            score += scoreOfTwoRollsInFrame(9);
        }
        return score;
    }

    private int strikeAndBonus(int frame) {
        return 10 +
                (isStrike(frame+1) ? (10 + (frame == 8 ? rolls[9][1] : rolls[frame+2][0])) : scoreOfTwoRollsInFrame(frame+1));
    }

    private int spareAndBonus(int frame) {
        return 10 + rolls[frame + 1][0];
    }

    private boolean isStrike(int frame) {
        return rolls[frame][0] == 10;
    }

    private int scoreOfTwoRollsInFrame(int frame) {
        return rolls[frame][0] + rolls[frame][1];
    }

    private boolean isSpare(int frame) {
        return scoreOfTwoRollsInFrame(frame) == 10;
    }

    public void roll(int pins) {
        rolls[frameIndex][rollIndex++] = pins;

        if ( (pins == 10 || rollIndex == 2) && frameIndex < 9) {
            frameIndex++;
            rollIndex = 0;
        }
    }
}
