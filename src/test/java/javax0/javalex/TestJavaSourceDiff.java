package javax0.javalex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestJavaSourceDiff {

    @Test
    @DisplayName("Two empty source code equals")
    void testEmpty(){
        final var s1 = "";
        final var s2 = "";
        Assertions.assertFalse( new JavaSourceDiff().test(s1, s2));
    }

    @Test
    @DisplayName("Changed code does not equals")
    void testSimple(){
        final var s1 = "int i";
        final var s2 = "float i";
        Assertions.assertTrue( new JavaSourceDiff().test(s1, s2));
    }

    @Test
    @DisplayName("Reformatted code equals")
    void testReformatted(){
        // snippet testReformatted
        final var s1 = "    void test(){\n" +
                "        final var s1 = \"\";\n" +
                "        final var s2 = \"\";\n" +
                "        Assertions.assertTrue( new Comparator().test(Arrays.asList(s1.split(\"\\n\",-1)),Arrays.asList(s2.split(\"\\n\",-1))));\n" +
                "    }";
        final var s2 = "    void test(){\n" +
                "                                 final var s1 = \"\";\n" +
                "        final var \n" +
                "                s2 = \"\";\n" +
                "        Assertions.assertTrue( new Comparator().test(Arrays.asList(s1.split(\"\\n\",-1)),Arrays.asList(s2.split(\"\\n\",-1))));\n" +
                "    }";
        Assertions.assertFalse( new JavaSourceDiff().test(s1, s2));
        // end snippet
    }


    @Test
    @DisplayName("Code comparison does not care the radix of numbers")
    void testNumberDifference(){
        final var s1 = "    @Test\n" +
                "    void method(){\n" +
                "        var x = 13;\n" +
                "    }";
        final var s2 = "    @Test\n" +
                "    void method(){\n" +
                "        var x = 0xd;\n" +
                "    }";
        Assertions.assertFalse( new JavaSourceDiff().test(s1, s2));
    }

    @Test
    @DisplayName("Code comparison does not care about comments by default.")
    void testCommentDifference(){
        final var s1 = "    @Test\n" +
            "    void method(){\n" +
            "    }";
        final var s2 = "    @Test\n" +
            "/** This is some \n comment \n*/"+
            "    void method(){\n" +
            "    }";
        Assertions.assertFalse( new JavaSourceDiff().test(s1, s2));
    }

    @Test
    @DisplayName("Code comparison cares about comments when explicitly told.")
    void testCommentDifferenceSensitive(){
        final var s1 = "    @Test\n" +
            "    void method(){\n" +
            "    }";
        final var s2 = "    @Test\n" +
            "/** This is some \n comment \n*/"+
            "    void method(){\n" +
            "    }";
        Assertions.assertTrue( new JavaSourceDiff().commentSensitive().test(s1, s2));
    }

    @Test
    @DisplayName("Code comparison handles character constants that have new line.")
    void testCompareCharacterLiteralsNewLine(){
        final var s1 = "final var sb = new StringBuilder();\n" +
                "        sb.append('\\n');\n" +
                "        sb.append(FAILED).append('\\n');";
        final var s2 = "final var sb = new StringBuilder();\n" +
                "        sb.append('\\n');\n" +
                "        sb.append(FAILED).append('\\n');";
        Assertions.assertFalse( new JavaSourceDiff().commentSensitive().test(s1, s2));
    }
}
