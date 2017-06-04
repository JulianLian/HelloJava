package context;

/**
 * Created by Julian on 2017/5/28.
 */
public class Context {
    private String expression;
    private int position;

    public Context(String expression) {
        this.expression = expression;
    }

    public char getChar() {
        return expression.length() > position ? expression.charAt(position++) : '\0';
    }

    public char curChar() {
        return expression.length() > position ? expression.charAt(position) : '\0';
    }

    public void skipChar() {
        position++;
//            if (getChar() != ch){
//                throw new RuntimeException();
//            }
    }
}
