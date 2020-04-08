package me.caiyuan.spring.spel.evaluation;

import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserConfigurationTests {

    @Test
    void test() {
        Simple simple = new Simple();

        boolean autoGrowNullReferences = true;
        boolean autoGrowCollections = true;
        SpelParserConfiguration config = new SpelParserConfiguration(autoGrowNullReferences, autoGrowCollections);
        ExpressionParser parser = new SpelExpressionParser(config);

        Expression exp = parser.parseExpression("list[3]");
        Object o = exp.getValue(simple);

        assertEquals(o, "");
        assertEquals(simple.list.size(), 4);
    }

    class Simple {
        public List<String> list;
    }

}
