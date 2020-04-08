package me.caiyuan.spring.spel.evaluation;

import org.junit.jupiter.api.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TypeConversionTests {

    @Test
    void test() {
        Simple simple = new Simple();
        simple.booleanList.add(true);

        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("booleanList[0]");

        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        exp.setValue(context, simple, "false");

        Boolean b = simple.booleanList.get(0);
        assertFalse(b);
    }

    class Simple {
        public List<Boolean> booleanList = new ArrayList<>();
    }
}
