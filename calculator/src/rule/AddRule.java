package rule;

import context.Context;
import expr.Expr;
import rule.IRule;

/**
 * Created by Julian on 2017/5/28.
 */
public class AddRule implements IRule {
    public static final AddRule INS = new AddRule();

    private AddRule() {}

    private boolean isAdd(char ch) {
        return ('+' == ch || '-' == ch);
    }

    @Override
    public boolean predicate(char ch) {
        return isAdd(ch);
    }

    @Override
    public int parser(Context context) {
        return Expr.mul(context);
    }
}
