package javax0.javalex;

import javax0.javalex.lex.LexicalElement;
import javax0.javalex.matchers.Lexpression;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class LexpressionBuilder {

    private static javax0.javalex.matchers.LexMatcher X(BiFunction<JavaLexed, Lexpression, LexMatcher> matcher, JavaLexed jLex, Lexpression e) {
        return (javax0.javalex.matchers.LexMatcher) matcher.apply(jLex, e);
    }

    private static javax0.javalex.matchers.LexMatcher[] X(BiFunction<JavaLexed, Lexpression, LexMatcher>[] matchers, JavaLexed jLex, Lexpression e) {
        return
                Arrays.stream(matchers).map(matcher -> X(matcher, jLex, e)).toArray(javax0.javalex.matchers.LexMatcher[]::new);
    }


    public static class GroupNameWrapper {
        private final String name;

        public GroupNameWrapper(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static GroupNameWrapper group(String name) {
        return new GroupNameWrapper(name);
    }

    //<editor-fold id="Builder Methods">


    // snippet BuilderMethods
    //{%@define STORED=The lexical element matched will be stored under the group name.%}
    //{%@define REGSTORED=The regular expression groups will also be stored under the name.%}
    public static BiFunction<JavaLexed, Lexpression, LexMatcher> modifier(int mask) {
        // one modifier. `mask` is an integer as defined in the JDK class `Modifier`.
        return (jLex, e) -> e.modifier(mask);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> keyword(String id) {
        // match a keyword.
        return (jLex, e) -> e.keyword(id);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> oneOf(BiFunction<JavaLexed, Lexpression, LexMatcher>... matchers) {
        // match one of the matchers.
        return (jLex, e) -> e.oneOf(X(matchers, jLex, e));
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> zeroOrMore(BiFunction<JavaLexed, Lexpression, LexMatcher> matcher) {
        // match zero or more times the matcher.
        return (jLex, e) -> e.zeroOrMore(X(matcher, jLex, e));
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> zeroOrMore(String string) {
        // match zero or more times the matcher created from the string.
        return (jLex, e) -> e.zeroOrMore(string);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> optional(BiFunction<JavaLexed, Lexpression, LexMatcher> matcher) {
        // match zero or one times the matcher.
        return (jLex, e) -> e.optional(X(matcher, jLex, e));
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> optional(String string) {
        // match zero or one times the matcher created from the string.
        return (jLex, e) -> e.optional(string);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> oneOrMore(BiFunction<JavaLexed, Lexpression, LexMatcher> matcher) {
        // match one or more times the matcher.
        return (jLex, e) -> e.oneOrMore(X(matcher, jLex, e));
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> oneOrMore(String string) {
        // match one or more times the matcher created from the string.
        return (jLex, e) -> e.oneOrMore(string);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> repeat(BiFunction<JavaLexed, Lexpression, LexMatcher> matcher, int times) {
        // match the matcher exactly `times` times.
        return (jLex, e) -> e.repeat(X(matcher, jLex, e), times);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> repeat(BiFunction<JavaLexed, Lexpression, LexMatcher> matcher, int min, int max) {
        // match the matcher at least `min` times and at most `max` times.
        return (jLex, e) -> e.repeat(X(matcher, jLex, e), min, max);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> integerNumber() {
        // match an integer number.
        return (jLex, e) -> e.integerNumber();
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> integerNumber(Predicate<Long> predicate) {
        // match an integer number that satisfies the predicate.
        return (jLex, e) -> e.integerNumber(predicate);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> number() {
        // match a number, either integer or float.
        return (jLex, e) -> e.number();
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> number(Predicate<Number> predicate) {
        // match a number, either integer or float that satisfies the predicate.
        return (jLex, e) -> e.number(predicate);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> floatNumber() {
        // match a float number
        return (jLex, e) -> e.floatNumber();
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> floatNumber(Predicate<Double> predicate) {
        // match a float number that satisfies the predicate.
        return (jLex, e) -> e.floatNumber(predicate);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> list(String... strings) {
        // match a list of matchers created from the strings.
        // The matchers are matched in the order they are listed.
        return (jLex, e) -> e.list(strings);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> list(BiFunction<JavaLexed, Lexpression, LexMatcher>... matchers) {
        // match a list of matchers.
        return (jLex, e) -> e.list(X(matchers, jLex, e));
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> match(String string) {
        // create a list of lexical elements from the string and match the list.
        return (jLex, e) -> e.match(string);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> unordered(BiFunction<JavaLexed, Lexpression, LexMatcher>... matchers) {
        // match a list of matchers.
        // The actual lexical elements can be in any order.
        return (jLex, e) -> e.unordered(X(matchers, jLex, e));
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> unordered(LexicalElement... elements) {
        // match a list of lexical elements.
        // The actual lexical elements can be in any order.
        return (jLex, e) -> e.unordered(elements);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> unordered(String string) {
        // create a list of lexical elements from the string and match the list.
        // The actual lexical elements can be in any order.
        return (jLex, e) -> e.unordered(string);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> group(String name, BiFunction<JavaLexed, Lexpression, LexMatcher> matcher) {
        // match the matcher and group the matched lexical elements under the name.
        return (jLex, e) -> e.group(name, X(matcher, jLex, e));
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> oneOf(String... strings) {
        // create matchers from the strings and match lexical elements that match one of them.
        return (jLex, e) -> e.oneOf(strings);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> not(BiFunction<JavaLexed, Lexpression, LexMatcher>... matchers) {
        // match lexical elements that do not match any of the matchers.
        return (jLex, e) -> e.not(X(matchers, jLex, e));
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> not(LexicalElement... elements) {
        // match lexical elements that do not match any of the lexical elements.
        return (jLex, e) -> e.not(elements);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> not(String string) {
        // create a matcher from the string and match lexical elements that do not match it.
        return (jLex, e) -> e.not(string);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> anyTill(BiFunction<JavaLexed, Lexpression, LexMatcher>... matchers) {
        // match lexical elements until one of the matchers matches.
        return (jLex, e) -> e.anyTill(X(matchers, jLex, e));
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> anyTill(LexicalElement... elements) {
        // match lexical elements until one of the lexical elements matches.
        return (jLex, e) -> e.anyTill(elements);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> anyTill(String string) {
        // create a matcher from the string and match lexical elements until it matches.
        return (jLex, e) -> e.anyTill(string);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> modifier(GroupNameWrapper nameWrapper, int mask) {
        // match a modifier.
        // {%STORED%}
        return (jLex, e) -> e.modifier(nameWrapper, mask);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> keyword(GroupNameWrapper nameWrapper, String id) {
        // match a keyword.
        return (jLex, e) -> e.keyword(nameWrapper, id);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> oneOf(GroupNameWrapper nameWrapper, BiFunction<JavaLexed, Lexpression, LexMatcher>... matchers) {
        // match one of the matchers.
        // {%STORED%}
        return (jLex, e) -> e.oneOf(nameWrapper, X(matchers, jLex, e));
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> zeroOrMore(GroupNameWrapper nameWrapper, BiFunction<JavaLexed, Lexpression, LexMatcher> matcher) {
        // match zero or more of the matchers.
        // The lexical elements matched will be stored under the name.
        return (jLex, e) -> e.zeroOrMore(nameWrapper, X(matcher, jLex, e));
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> zeroOrMore(GroupNameWrapper nameWrapper, String string) {
        // match zero or more of the lexical elements.
        // The lexical elements matched will be stored under the name.
        return (jLex, e) -> e.zeroOrMore(nameWrapper, string);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> optional(GroupNameWrapper nameWrapper, BiFunction<JavaLexed, Lexpression, LexMatcher> matcher) {
        // match zero or one of the matcher.
        // {%STORED%}
        return (jLex, e) -> e.optional(nameWrapper, X(matcher, jLex, e));
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> optional(GroupNameWrapper nameWrapper, String string) {
        // match zero or one of the lexical elements.
        // {%STORED%}
        return (jLex, e) -> e.optional(nameWrapper, string);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> oneOrMore(GroupNameWrapper nameWrapper, BiFunction<JavaLexed, Lexpression, LexMatcher> matcher) {
        // match one or more of the matcher.
        // {%STORED%}
        return (jLex, e) -> e.oneOrMore(nameWrapper, X(matcher, jLex, e));
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> oneOrMore(GroupNameWrapper nameWrapper, String string) {
        // match one or more of the lexical elements converted from the string.
        // {%STORED%}
        return (jLex, e) -> e.oneOrMore(nameWrapper, string);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> repeat(GroupNameWrapper nameWrapper, BiFunction<JavaLexed, Lexpression, LexMatcher> matcher, int times) {
        // match the matcher the specified number of times.
        // The lexical elements matched will be stored under the name.
        return (jLex, e) -> e.repeat(nameWrapper, X(matcher, jLex, e), times);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> repeat(GroupNameWrapper nameWrapper, BiFunction<JavaLexed, Lexpression, LexMatcher> matcher, int min, int max) {
        // match the matcher the specified number of times, minimum and maximum.
        // The lexical elements matched will be stored under the name.
        return (jLex, e) -> e.repeat(nameWrapper, X(matcher, jLex, e), min, max);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> integerNumber(GroupNameWrapper nameWrapper) {
        // match an integer number.
        // {%STORED%}
        return (jLex, e) -> e.integerNumber(nameWrapper);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> integerNumber(GroupNameWrapper nameWrapper, Predicate<Long> predicate) {
        // match an integer number and check it against the predicate.
        // {%STORED%}
        return (jLex, e) -> e.integerNumber(nameWrapper, predicate);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> number(GroupNameWrapper nameWrapper) {
        // match a number either integer or float.
        //
        return (jLex, e) -> e.number(nameWrapper);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> number(GroupNameWrapper nameWrapper, Predicate<Number> predicate) {
        // match a number either integer or float and check it against the predicate.
        // {%STORED%}
        return (jLex, e) -> e.number(nameWrapper, predicate);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> floatNumber(GroupNameWrapper nameWrapper) {
        // match a float number.
        // {%STORED%}
        return (jLex, e) -> e.floatNumber(nameWrapper);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> floatNumber(GroupNameWrapper nameWrapper, Predicate<Double> predicate) {
        // match a float number and check it against the predicate.
        // {%STORED%}
        return (jLex, e) -> e.floatNumber(nameWrapper, predicate);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> list(GroupNameWrapper nameWrapper, String... strings) {
        // match a list of lexical elements converted from the strings.
        // The lexical elements matched will be stored under the name.
        return (jLex, e) -> e.list(nameWrapper, strings);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> list(GroupNameWrapper nameWrapper, BiFunction<JavaLexed, Lexpression, LexMatcher>... matchers) {
        // match a list of matchers.
        // The lexical elements matched will be stored under the name.
        return (jLex, e) -> e.list(nameWrapper, X(matchers, jLex, e));
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> match(GroupNameWrapper nameWrapper, String string) {
        // match lexical elements converted from the string.
        // The lexical elements matched will be stored under the name.
        return (jLex, e) -> e.match(nameWrapper, string);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> unordered(GroupNameWrapper nameWrapper, BiFunction<JavaLexed, Lexpression, LexMatcher>... matchers) {
        // match the matchers in any order.
        // The lexical elements matched will be stored under the name.
        return (jLex, e) -> e.unordered(nameWrapper, X(matchers, jLex, e));
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> unordered(GroupNameWrapper nameWrapper, LexicalElement... elements) {
        // match the lexical elements in any order.
        // The lexical elements matched will be stored under the name.
        return (jLex, e) -> e.unordered(nameWrapper, elements);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> unordered(GroupNameWrapper nameWrapper, String string) {
        // match the lexical elements converted from the string in any order.
        // The lexical elements matched will be stored under the name.
        return (jLex, e) -> e.unordered(nameWrapper, string);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> oneOf(GroupNameWrapper nameWrapper, String... strings) {
        // match one of the lexical elements converted from the strings.
        // The lexical elements matched will be stored under the name.
        return (jLex, e) -> e.oneOf(nameWrapper, strings);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> not(GroupNameWrapper nameWrapper, BiFunction<JavaLexed, Lexpression, LexMatcher>... matchers) {
        // create a matcher that does not match the list of matchers.
        // The process goes through the matchers taking the lexical elements one by one as consumed by the matchers, and if any of them matches, then this matcher does not.
        // The lexical elements "matched" will be stored under the name.
        return (jLex, e) -> e.not(nameWrapper, X(matchers, jLex, e));
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> not(GroupNameWrapper nameWrapper, LexicalElement... elements) {
        // create a matcher that does not match the list of lexical elements.
        // The lexical elements "matched" will be stored under the name.
        return (jLex, e) -> e.not(nameWrapper, elements);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> not(GroupNameWrapper nameWrapper, String string) {
        // create a matcher that does not match the lexical elements converted from the string.
        // The lexical elements "matched" will be stored under the name.
        return (jLex, e) -> e.not(nameWrapper, string);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> anyTill(GroupNameWrapper nameWrapper, BiFunction<JavaLexed, Lexpression, LexMatcher>... matchers) {
        // match any lexical elements until the list of matchers matches.
        // The lexical elements matched will be stored under the name.
        return (jLex, e) -> e.anyTill(nameWrapper, X(matchers, jLex, e));
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> anyTill(GroupNameWrapper nameWrapper, LexicalElement... elements) {
        // match any lexical elements until the list of lexical elements matches.
        // The lexical elements matched will be stored under the name.
        return (jLex, e) -> e.anyTill(nameWrapper, elements);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> anyTill(GroupNameWrapper nameWrapper, String string) {
        // match any lexical elements until the lexical elements converted from the string matches.
        // The lexical elements matched will be stored under the name.
        return (jLex, e) -> e.anyTill(nameWrapper, string);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> identifier(GroupNameWrapper nameWrapper) {
        // match an identifier.
        // {%STORED%}
        return (jLex, e) -> e.identifier(nameWrapper);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> identifier(GroupNameWrapper nameWrapper, String text) {
        // match an identifier with the given text.
        // {%STORED%}
        // This is a bit superficial, since the name was already given in the parameter, but it is here for consistency.
        return (jLex, e) -> e.identifier(nameWrapper, text);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> identifier(GroupNameWrapper nameWrapper, Pattern pattern) {
        // match an identifier with the given pattern.
        // {%STORED%}
        // Note that this is not the regular expression groups.
        return (jLex, e) -> e.identifier(nameWrapper, pattern);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> identifier(GroupNameWrapper nameWrapper, String name, Pattern pattern) {
        // match an identifier with the given name and pattern.
        // {%STORED%}
        return (jLex, e) -> e.identifier(nameWrapper, name, pattern);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> character(GroupNameWrapper nameWrapper) {
        // match a character literal. This is a single character enclosed in single quotes.
        // {%STORED%}
        return (jLex, e) -> e.character(nameWrapper);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> character(GroupNameWrapper nameWrapper, String text) {
        // match a character literal with the given text.
        // {%STORED%}
        return (jLex, e) -> e.character(nameWrapper, text);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> character(GroupNameWrapper nameWrapper, Pattern pattern) {
        // match a character literal with the given pattern.
        // {%STORED%}
        return (jLex, e) -> e.character(nameWrapper, pattern);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> character(GroupNameWrapper nameWrapper, String name, Pattern pattern) {
        // match a character literal with the given name and pattern.
        // {%STORED%}
        // {%REGSTORED%}
        return (jLex, e) -> e.character(nameWrapper, name, pattern);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> string(GroupNameWrapper nameWrapper) {
        // match a string literal. This is a sequence of characters enclosed in double quotes.
        // The mather also matches multi-line strings.
        // {%STORED%}
        return (jLex, e) -> e.string(nameWrapper);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> string(GroupNameWrapper nameWrapper, String text) {
        // match a string literal with the given text.
        // The matcher also matches multi-line strings.
        // {%STORED%}
        return (jLex, e) -> e.string(nameWrapper, text);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> string(GroupNameWrapper nameWrapper, Pattern pattern) {
        // match a string literal with the given pattern.
        // The matcher also matches multi-line strings.
        // {%STORED%}
        return (jLex, e) -> e.string(nameWrapper, pattern);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> string(GroupNameWrapper nameWrapper, String name, Pattern pattern) {
        // match a string literal with the given name and pattern.
        // The matcher also matches multi-line strings.
        // {%STORED%}
        // {%REGSTORED%}
        return (jLex, e) -> e.string(nameWrapper, name, pattern);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> type(GroupNameWrapper nameWrapper) {
        // match a Java type declaration.
        // This can be a single name, or generics' enhanced type declaration.
        // {%STORED%}
        return (jLex, e) -> e.type(nameWrapper);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> type(GroupNameWrapper nameWrapper, String text) {
        // match a Java type declaration with the given text.
        // This can be a single name, or generics' enhanced type declaration.
        // {%STORED%}
        return (jLex, e) -> e.type(nameWrapper, text);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> type(GroupNameWrapper nameWrapper, Pattern pattern) {
        // match a Java type declaration with the given pattern.
        // This can be a single name, or generics' enhanced type declaration.
        // {%STORED%}
        return (jLex, e) -> e.type(nameWrapper, pattern);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> type(GroupNameWrapper nameWrapper, String name, Pattern pattern) {
        // match a Java type declaration with the given name and pattern.
        // This can be a single name, or generics' enhanced type declaration.
        // {%STORED%}
        // {%REGSTORED%}
        return (jLex, e) -> e.type(nameWrapper, name, pattern);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> comment(GroupNameWrapper nameWrapper) {
        // match a Java comment.
        // {%STORED%}
        return (jLex, e) -> e.comment(nameWrapper);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> comment(GroupNameWrapper nameWrapper, String text) {
        // match a Java comment with the given text.
        // {%STORED%}
        return (jLex, e) -> e.comment(nameWrapper, text);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> comment(GroupNameWrapper nameWrapper, Pattern pattern) {
        // match a Java comment with the given pattern.
        // {%STORED%}
        return (jLex, e) -> e.comment(nameWrapper, pattern);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> comment(GroupNameWrapper nameWrapper, String name, Pattern pattern) {
        // match a Java comment with the given name and pattern.
        // {%STORED%}
        // {%REGSTORED%}
        return (jLex, e) -> e.comment(nameWrapper, name, pattern);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> identifier() {
        // match a Java identifier.
        return (jLex, e) -> e.identifier();
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> identifier(String text) {
        // match a Java identifier with the given text.
        return (jLex, e) -> e.identifier(text);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> identifier(Pattern pattern) {
        // match a Java identifier with the given pattern.
        return (jLex, e) -> e.identifier(pattern);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> identifier(String name, Pattern pattern) {
        // match a Java identifier with the given name and pattern.
        // {%REGSTORED%}
        return (jLex, e) -> e.identifier(name, pattern);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> character() {
        // match a Java character literal.
        return (jLex, e) -> e.character();
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> character(String text) {
        // match a Java character literal with the given text.
        return (jLex, e) -> e.character(text);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> character(Pattern pattern) {
        // match a Java character literal with the given pattern.
        return (jLex, e) -> e.character(pattern);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> character(String name, Pattern pattern) {
        // match a Java character literal with the given pattern.
        // {%REGSTORED%}
        return (jLex, e) -> e.character(name, pattern);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> string() {
        // match a Java string literal.
        // The matcher also matches multi-line strings.
        return (jLex, e) -> e.string();
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> string(String text) {
        // match a Java string literal with the given text.
        // The matcher also matches multi-line strings.
        return (jLex, e) -> e.string(text);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> string(Pattern pattern) {
        // match a Java string literal with the given pattern.
        // The matcher also matches multi-line strings.
        return (jLex, e) -> e.string(pattern);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> string(String name, Pattern pattern) {
        // match a Java string literal with the given name and pattern.
        // The matcher also matches multi-line strings.
        // {%REGSTORED%}
        return (jLex, e) -> e.string(name, pattern);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> type() {
        // match a Java type declaration.
        // This can be a single name, or generics' enhanced type declaration.
        return (jLex, e) -> e.type();
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> type(String text) {
        // match a Java type declaration with the given text.
        // This can be a single name, or generics' enhanced type declaration.
        return (jLex, e) -> e.type(text);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> type(Pattern pattern) {
        // match a Java type declaration with the given pattern.
        // This can be a single name, or generics' enhanced type declaration.
        return (jLex, e) -> e.type(pattern);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> type(String name, Pattern pattern) {
        // match a Java type declaration with the given pattern.
        // This can be a single name, or generics' enhanced type declaration.
        // {%REGSTORED%}
        return (jLex, e) -> e.type(name, pattern);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> comment() {
        // match a Java comment.
        return (jLex, e) -> e.comment();
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> comment(String text) {
        // match a Java comment with the given text.
        return (jLex, e) -> e.comment(text);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> comment(Pattern pattern) {
        // match a Java comment with the given pattern.
        return (jLex, e) -> e.comment(pattern);
    }

    public static BiFunction<JavaLexed, Lexpression, LexMatcher> comment(String name, Pattern pattern) {
        // match a Java comment with the given name and pattern.
        // {%REGSTORED%}
        return (jLex, e) -> e.comment(name, pattern);
    }
    // end snippet
    //</editor-fold>


}
