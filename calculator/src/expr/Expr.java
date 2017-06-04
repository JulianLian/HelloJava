package expr;

import context.Context;
import rule.AddRule;
import rule.IRule;
import rule.MulRule;

/**
 * Created by Julian on 2017/5/28.
 */
public class Expr {

    public static int getNum(Context context) {
        return context.getChar() - '0';
    }

    private int add(Context context) {
        return chain(context, AddRule.INS);
    }

    public int expr(String expression) {
        Context context = new Context(expression);
        return add(context);
    }

    private static int chain(Context context, IRule rule) {
        int result = rule.parser(context);
        char op;

        while (rule.predicate(op = context.curChar())) {
            context.skipChar();
            result = calc(result, op, rule.parser(context));
        }

        return result;
    }

    public static int mul(Context context) {
        return chain(context, MulRule.INS);
    }

    private static int calc(int oprdl, char op, int oprdr) {
        if ('+' == op)
            return oprdl + oprdr;
        else if ('-' == op)
            return oprdl - oprdr;
        else if ('*' == op)
            return oprdl * oprdr;
        else
            return oprdl / oprdr;
    }
}
