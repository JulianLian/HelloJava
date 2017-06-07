package main;

/**
 * Created by Julian on 2017/6/3.
 */
public class Game {
    private int[] rolls = new int[21];
    private int currentRoll = 0;

    public void roll(int pins) {
        rolls[currentRoll++] = pins;
    }

    public int score() {
        int score = 0;
        int rollIndex = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (isaBoolean(rollIndex)) {
                score += strikeAndBonus(rollIndex);
                rollIndex += 1;
            } else if (isSpare(rollIndex)) {
                score += spareAndBonus(rollIndex);
                rollIndex += 2;
            } else {
                score += scoreOfFailFrame(rollIndex);
                rollIndex += 2;
            }
        }
        return score;
    }

    private boolean isaBoolean(int rollIndex) {
        return rolls[rollIndex] == 10;
    }

    private int scoreOfFailFrame(int rollIndex) {
        return rolls[rollIndex] + rolls[rollIndex + 1];
    }

    private int spareAndBonus(int rollIndex) {
        return 10 + rolls[rollIndex+2];
    }

    private int strikeAndBonus(int rollIndex) {
        return 10 + rolls[rollIndex+1] + rolls[rollIndex+2];
    }

    private boolean isSpare(int rollIndex) {
        return rolls[rollIndex] + rolls[rollIndex + 1] == 10;
    }
}
