package main;

import java.util.List;

/**
 * Created by Julian on 2017/6/21.
 */
public class TriangleCounter
{
    List<String> lines;

    public TriangleCounter() {
    }

    public TriangleCounter(List<String> ls) {
        this.lines = ls;
    }


    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public boolean isContained(String s,char ... cs) {
        for (char c : cs) {
            if (s.indexOf(c) < 0)
                return false;
        }
        return true;
    }

    public boolean isOnLine(char ... cs) {
        for (String s : lines) {
            if (isContained(s, cs))
                return true;
        }
        return false;
    }

    public int count(char ... cs) {
        int counter = 0;
        for (int i = 0; i < cs.length - 2; i++) {
            for (int j = i+1; j < cs.length - 1; j++) {
                for (int k = j+1; k < cs.length; k++) {
                    if (isOnLine(cs[i],cs[j])
                        && isOnLine(cs[j], cs[k])
                            && isOnLine(cs[k], cs[i])
                                && !isOnLine(cs[i],cs[j],cs[k])) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }
}
