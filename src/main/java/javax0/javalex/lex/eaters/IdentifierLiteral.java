package javax0.javalex.lex.eaters;

import javax0.javalex.lex.LexEater;
import javax0.javalex.lex.LexicalElement;

/**
 * Fetch an identifier from the input and return the identifier as a lexical element.
 */
public class IdentifierLiteral implements LexEater {
    @Override
    public LexicalElement.Identifier apply(StringBuilder sb) {
        if (sb.length() == 0 || !Character.isJavaIdentifierStart(sb.charAt(0))) {
            return null;
        }
        final var output = new StringBuilder();
        while (sb.length() > 0 && Character.isJavaIdentifierPart(sb.charAt(0))) {
            output.append(sb.charAt(0));
            sb.deleteCharAt(0);
        }
        return new LexicalElement.Identifier(output.toString());
    }
}
