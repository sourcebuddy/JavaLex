package javax0.javalex;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static javax0.javalex.LexpressionBuilder.*;

public class TestElexeer {


    @Test
    void testOrMoretMatchingMany() {
        final var source = "private private private final int z = 13;\npublic var h = \"kkk\"";
        try (final var javaLexed = new JavaLexed(source)) {
            javaLexed.match(list(oneOrMore(keyword("private")), optional(identifier("final"))
                    , optional(identifier("abraka dabra")), unordered("int")));
            final var result = javaLexed.fromIndex(0).result();
            Assertions.assertTrue(result.matches);
            Assertions.assertEquals(0, result.start);
            Assertions.assertEquals(9, result.end);
        }
    }

}
