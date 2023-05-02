package javax0.javalex.matchers;

import javax0.javalex.lex.LexicalElement;
import javax0.javalex.JavaLexed;
import javax0.javalex.MatchResult;

import java.util.function.Predicate;

public class NumberMatcher extends LexMatcher {
    private final Predicate<Number> predicate;

    public NumberMatcher(Lexpression factory, JavaLexed javaLexed, Predicate<Number> predicate) {
        super(factory, javaLexed);
        this.predicate = predicate;
    }

    public NumberMatcher(Lexpression factory, JavaLexed javaLexed) {
        this(factory, javaLexed, null);
    }

    public MatchResult matchesAt(int i) {
        if (consumed()) {
            return MatchResult.NO_MATCH;
        }
        int start = skipSpacesAndComments(i);
        if (javaLexed.get(start).getType() != javax0.javalex.LexicalElement.Type.INTEGER && javaLexed.get(start).getType() != javax0.javalex.LexicalElement.Type.FLOAT) {
            return MatchResult.NO_MATCH;
        }
        if (predicate != null) {
            if (javaLexed.get(start).getType() == javax0.javalex.LexicalElement.Type.INTEGER) {
                if (predicate.test(((LexicalElement.IntegerLiteral) javaLexed.get(start)).value)) {
                    return matching( start, start + 1);
                } else {
                    return MatchResult.NO_MATCH;
                }
            } else {
                if (predicate.test(((LexicalElement.FloatLiteral) javaLexed.get(start)).value)) {
                    return matching( start, start + 1);
                } else {
                    return MatchResult.NO_MATCH;
                }
            }
        } else {
            return matching( start, start + 1);
        }
    }
}
