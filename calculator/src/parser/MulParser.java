package parser;

import context.Context;
import expr.Expr;
import parser.IParser;

/**
 * Created by Julian on 2017/5/28.
 */
public class MulParser implements IParser {
    public static final MulParser INS = new MulParser();

    private MulParser() {

    }
    @Override
    public int parser(Context context) {
        return Expr.getNum(context);
    }
}
