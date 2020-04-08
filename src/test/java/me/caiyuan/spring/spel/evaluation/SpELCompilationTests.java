package me.caiyuan.spring.spel.evaluation;

import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpELCompilationTests {

    @Test
    void test() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        SpelCompilerMode immediate = SpelCompilerMode.IMMEDIATE;
        SpelParserConfiguration config = new SpelParserConfiguration(immediate, classLoader);
        SpelExpressionParser parser = new SpelExpressionParser(config);

        Expression exp = parser.parseExpression("payload");
        Simple simple = new Simple();

        Object payload = exp.getValue(simple);
        assertEquals(payload, "Hello");
    }

    class Simple {
        public String payload = "Hello";
    }
}
