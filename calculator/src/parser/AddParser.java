package parser;

import context.Context;
import expr.Expr;
import parser.IParser;

/**
 * Created by Julian on 2017/5/28.
 */
public class AddParser implements IParser {
    public static final AddParser INS = new AddParser();

    private AddParser() {

    }
    @Override
    public int parser(Context context) {
        return Expr.mul(context);
    }
}
