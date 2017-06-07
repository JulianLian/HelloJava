package main;

/**
 * Created by Julian on 2017/6/7.
 */
public class GameX {

    private int score = 0;

    public int score() {
        return score;
    }

    public void roll(int pins) {
        score += pins;
    }
}
