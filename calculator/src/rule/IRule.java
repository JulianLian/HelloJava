package rule;

import context.Context;

/**
 * Created by Julian on 2017/5/28.
 */
public interface IRule {
    boolean predicate(char ch);
    int parser(Context context);
}
