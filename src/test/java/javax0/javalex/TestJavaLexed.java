package javax0.javalex;

import javax0.javalex.lex.LexicalElement.Identifier;
import javax0.javalex.lex.LexicalElement.Spacing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TestJavaLexed {

    // snippet testNullTransformation
    void testNullTransformation(String lines) {
        final var expected = lines;
        final String result;
        try (final var sut = new JavaLexed(lines)) {
            // do nothing, no transformation on lexical level
            result = sut.toString();
        }
        Assertions.assertEquals(expected, result);
    }
    // end snippet

    // snippet toLexicalString
    private String toLexicalString(JavaLexed javaLexed) {
        StringBuilder sb = new StringBuilder();
        for (final var le : javaLexed.lexicalElements()) {
            sb.append(le.getType().name()).append("[")
                    .append(le.getFullLexeme()).append("]").append("\n");
        }
        return sb.toString();
    }
    // end snippet

    @Test
    void testEmptyNullTransformation() {
        testNullTransformation("");
    }

    @Test
    void testOneLinerNullTransformation() {
        testNullTransformation("private final var apple= \"appleee\";");
    }

    @Test
    void testMultiLinerNullTransformation() {
        testNullTransformation(
                "private final var apple= \"appleee\";\n" +
                        "private final final 13 'aaaaa' ");
    }

    @Test
    void testMultiLinerSpaceNewLineSpaceNullTransformation() {
        testNullTransformation(
                "private final var apple= \"appleee\";   \n" +
                        "    private final final 13 'aaaaa' ");
    }


    @Test
    void iterateThrough() {
        // snippet iterateThrough
        final var source =
                "private final var apple= \"appleee\";   \n" +
                        "    private final final 13 'aaaaa' ";
        final String lexed;
        try (final var sut = new JavaLexed(source)) {
            lexed = toLexicalString(sut);
        }
        Assertions.assertEquals("IDENTIFIER[private]\n" +
                "SPACING[ ]\n" +
                "IDENTIFIER[final]\n" +
                "SPACING[ ]\n" +
                "IDENTIFIER[var]\n" +
                "SPACING[ ]\n" +
                "IDENTIFIER[apple]\n" +
                "SYMBOL[=]\n" +
                "SPACING[ ]\n" +
                "STRING[\"appleee\"]\n" +
                "SYMBOL[;]\n" +
                "SPACING[   \n" +
                "    ]\n" +
                "IDENTIFIER[private]\n" +
                "SPACING[ ]\n" +
                "IDENTIFIER[final]\n" +
                "SPACING[ ]\n" +
                "IDENTIFIER[final]\n" +
                "SPACING[ ]\n" +
                "INTEGER[13]\n" +
                "SPACING[ ]\n" +
                "CHARACTER['aaaaa']\n" +
                "SPACING[ ]\n", lexed);
        //end snippet
    }

    @Test
    @DisplayName("JavaLexed can't be used after being closed.")
    void cantBeUsedOutOfScope() {
        final var source = "";
        final var sut = new JavaLexed(source);
        try {
        } finally {
            sut.close();
        }
        Assertions.assertThrows(IllegalArgumentException.class, sut::lexicalElements);
    }

    @Test
    @DisplayName("Can't remove range with bigger start than end.")
    void removeRangeThrowsExceptionWhenWrongStartEndOrder() {
        final var source = "";
        try (final var sut = new JavaLexed(source)) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> sut.removeRange(2, 1));
        }
    }

    @Test
    @DisplayName("Can't remove range if end is out of bounds.")
    void removeRangeThrowsExceptionWhenEndOutOfBounds() {
        final var source = "public final";
        try (final var sut = new JavaLexed(source)) {
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> sut.removeRange(1, 5));
        }
    }

    @Test
    @DisplayName("Can't remove range if start is out of bounds.")
    void removeRangeThrowsExceptionWhenStartOutOfBounds() {
        final var source = "public final";
        try (final var sut = new JavaLexed(source)) {
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> sut.removeRange(-1, 2));
        }
    }

    @Test
    @DisplayName("Can remove a single lexical element.")
    void removesElement() {
        // snippet removeElement
        final var source = "public static final";
        final String lexed;
        try (final var sut = new JavaLexed(source)) {
            sut.remove(2);
            lexed = toLexicalString(sut);
        }
        Assertions.assertEquals("IDENTIFIER[public]\n" +
                "SPACING[ ]\n" +
                "SPACING[ ]\n" +
                "IDENTIFIER[final]\n", lexed);
        // end snippet
    }

    @Test
    @DisplayName("Can't remove element from out of bounds.")
    void removeElementThrowsExceptionOutOfBounds() {
        final var source = "public final";
        try (final var sut = new JavaLexed(source)) {
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(-1));
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(5));
        }
    }

    @Test
    @DisplayName("Can get a single lexical element by position.")
    void getsElement() {
        // snippet getsElement
        final var source = "public static final";
        try (final var sut = new JavaLexed(source)) {
            final var lexicalElement = sut.get(2);
            Assertions.assertEquals(LexicalElement.Type.IDENTIFIER, lexicalElement.getType());
            Assertions.assertEquals("static", lexicalElement.getFullLexeme());
        }
        // end snippet
    }

    @Test
    @DisplayName("Can remove a range of lexical elements.")
    void removesRange() {
        // snippet removesRange
        final var source = "final static private";
        final String lexed;
        try (final var sut = new JavaLexed(source)) {
            sut.removeRange(1, 3);
            lexed = toLexicalString(sut);
        }
        Assertions.assertEquals("IDENTIFIER[final]\n" +
                "SPACING[ ]\n" +
                "IDENTIFIER[private]\n", lexed);
        // end snippet
    }

    @Test
    @DisplayName("Can replace a range of lexical elements at the start.")
    void replacesRangeStart() {
        // snippet replacesRangeStart
        final var source = "final private";
        final String lexed;
        try (final var sut = new JavaLexed(source)) {
            sut.replace(0, 2, Arrays.asList(new Identifier("static"), new Spacing("   ")));
            lexed = toLexicalString(sut);
        }
        Assertions.assertEquals("IDENTIFIER[static]\n" +
                "SPACING[   ]\n" +
                "IDENTIFIER[private]\n", lexed);
        //end snippet
    }

    @Test
    @DisplayName("Can replace a range of lexical elements between the start and the end.")
    void replacesRangeMiddle() {
        final var source = "public final static var";
        final String lexed;
        try (final var sut = new JavaLexed(source)) {
            sut.replace(2, 4, Arrays.asList(new Identifier("static"), new Spacing("   ")));
            lexed = toLexicalString(sut);
        }
        Assertions.assertEquals("IDENTIFIER[public]\n" +
                "SPACING[ ]\n" +
                "IDENTIFIER[static]\n" +
                "SPACING[   ]\n" +
                "IDENTIFIER[static]\n" +
                "SPACING[ ]\n" +
                "IDENTIFIER[var]\n", lexed);
    }

    @Test
    @DisplayName("Can replace a range of lexical elements at the end.")
    void replacesRangeEnd() {
        final var source = "public final static";
        final String lexed;
        try (final var sut = new JavaLexed(source)) {
            sut.replace(2, 5, Arrays.asList(new Identifier("static"), new Spacing("   ")));
            lexed = toLexicalString(sut);
        }
        Assertions.assertEquals("IDENTIFIER[public]\n" +
                "SPACING[ ]\n" +
                "IDENTIFIER[static]\n" +
                "SPACING[   ]\n", lexed);
    }

    @Test
    @DisplayName("Can replace a range of lexical elements with the Lex utility class.")
    void replacesRangeWithLex() {
        // snippet replacesRangeWithLex
        final var source = "final private";
        final String lexed;
        try (final var sut = new JavaLexed(source)) {
            sut.replace(0, 2, Lex.of("static   "));
            lexed = toLexicalString(sut);
        }
        Assertions.assertEquals("IDENTIFIER[static]\n" +
                "SPACING[   ]\n" +
                "IDENTIFIER[private]\n", lexed);
        //end snippet
    }
}
