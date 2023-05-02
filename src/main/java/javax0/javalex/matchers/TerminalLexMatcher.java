package javax0.javalex.matchers;

import javax0.javalex.lex.LexicalElement;
import javax0.javalex.JavaLexed;
import javax0.javalex.MatchResult;

public class TerminalLexMatcher extends LexMatcher {
    private final LexicalElement le;

    public TerminalLexMatcher(Lexpression lexpression, JavaLexed javaLexed, LexicalElement le) {
        super(lexpression, javaLexed);
        this.le = le;
    }

    @Override
    public MatchResult matchesAt(int i) {
        if( consumed()){
            return MatchResult.NO_MATCH;
        }
        int j = skipSpacesAndComments(i);
        if (j < javaLexed.size()) {
            final var matches = super.javaLexed.get(j).equals(le);
            if (matches) {
                return matching( j, j + 1);
            }
        }
        return MatchResult.NO_MATCH;
    }
}
