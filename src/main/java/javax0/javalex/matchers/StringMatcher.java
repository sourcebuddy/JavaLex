package javax0.javalex.matchers;

import javax0.javalex.LexicalElement;
import javax0.javalex.JavaLexed;
import javax0.javalex.MatchResult;

import java.util.regex.Pattern;

public class StringMatcher extends AbstractPatternedMatcher {

    public StringMatcher(Lexpression factory, JavaLexed javaLexed, Pattern pattern, String name) {
        super(factory, javaLexed, pattern, name);
    }

    public StringMatcher(Lexpression factory, JavaLexed javaLexed, Pattern pattern) {
        super(factory, javaLexed, pattern);
    }

    public StringMatcher(Lexpression factory, JavaLexed javaLexed, String text) {
        super(factory, javaLexed, text);
    }

    public StringMatcher(Lexpression factory, JavaLexed javaLexed) {
        super(factory, javaLexed);
    }

    @Override
    public MatchResult matchesAt(int i) {
        return match(i, LexicalElement.Type.STRING);
    }
}
