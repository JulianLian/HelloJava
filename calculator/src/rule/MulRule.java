package rule;

import context.Context;
import expr.Expr;
import rule.IRule;

/**
 * Created by Julian on 2017/5/28.
 */
public class MulRule implements IRule {
    public static final MulRule INS = new MulRule();

    private MulRule() {}

    private boolean isMul(char ch) {
        return ('*' == ch || '/' == ch);
    }

    @Override
    public boolean predicate(char ch) {
        return isMul(ch);
    }

    @Override
    public int parser(Context context) {
        return Expr.getNum(context);
    }
}
