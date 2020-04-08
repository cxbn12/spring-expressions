package me.caiyuan.spring.spel;

import lombok.extern.log4j.Log4j2;
import me.caiyuan.spring.spel.pojo.Inventor;
import org.junit.jupiter.api.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

import java.util.*;

@Log4j2
public class Tests {

    @Test
    void t1() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'");
        String message = (String) exp.getValue();
        log.info(message);
    }

    @Test
    void t2() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'.concat('!')");
        String message = (String) exp.getValue();
        log.info(message);
    }

    @Test
    void t3() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'.bytes");
        byte[] bytes = (byte[]) exp.getValue();
        log.info(bytes);
    }

    @Test
    void t4() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'.bytes.length");
        int length = (Integer) exp.getValue();
        log.info(length);
    }

    @Test
    void t5() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("new String('hello world').toUpperCase()");
        String message = exp.getValue(String.class);
        log.info(message);
    }

    @Test
    void t6() {
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, Calendar.AUGUST, 9);
        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

        ExpressionParser parser = new SpelExpressionParser();
        Expression exp;

        exp = parser.parseExpression("name");
        String name = (String) exp.getValue(tesla);
        log.info(name);

        exp = parser.parseExpression("name == 'Nikola Tesla'");
        boolean result = exp.getValue(tesla, Boolean.class);
        log.info(result);
    }

    @Test
    void t7() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("T(java.lang.Math).random() * 100.0");
        Object o = exp.getValue();
        log.info(o);
    }

    @Test
    void t8() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("{1,2,3}");
        List<Integer> o = exp.getValue(List.class);
        log.info(o);
    }

    @Test
    void t9() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("{{'a','b'},{'x','y'}}");
        List<List<String>> o = exp.getValue(List.class);
        log.info(o);
    }

    @Test
    void t10() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("{name:'Nikola',dob:'10-July-1856'}");
        Map<String, String> o = exp.getValue(Map.class);
        log.info(o);
    }

    @Test
    void t11() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("{name:{first:'Nikola',last:'Tesla'},dob:{day:10,month:'July',year:1856}}");
        Map<String, Map<String, String>> o = exp.getValue(Map.class);
        log.info(o);
    }

    @Test
    void t12() {
        ExpressionParser parser = new SpelExpressionParser();

        int[] numbers1 = (int[]) parser.parseExpression("new int[4]").getValue();
        log.info(Arrays.toString(numbers1));

        int[] numbers2 = (int[]) parser.parseExpression("new int[]{1,2,3}").getValue();
        log.info(Arrays.toString(numbers2));

        int[][] numbers3 = (int[][]) parser.parseExpression("new int[4][5]").getValue();
        log.info(Arrays.toString(numbers3));
    }

    @Test
    void t13() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("'abc'.substring(1, 3)");
        String o = expression.getValue(String.class);
        log.info(o);
    }

    @Test
    void t14() {
        ExpressionParser parser = new SpelExpressionParser();

        boolean trueValue1 = parser.parseExpression("2 == 2").getValue(Boolean.class);
        log.info(trueValue1);

        boolean falseValue = parser.parseExpression("2 < -5.0").getValue(Boolean.class);
        log.info(falseValue);

        boolean trueValue2 = parser.parseExpression("'black' < 'block'").getValue(Boolean.class);
        log.info(trueValue2);
    }

    @Test
    void t15() {
        ExpressionParser parser = new SpelExpressionParser();

        boolean falseValue1 = parser.parseExpression("'xyz' instanceof T(Integer)").getValue(Boolean.class);
        log.info(falseValue1);

        boolean trueValue = parser.parseExpression("'5.00' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);
        log.info(trueValue);

        boolean falseValue2 = parser.parseExpression("'5.0067' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);
        log.info(falseValue2);
    }

    @Test
    void t16() {
        ExpressionParser parser = new SpelExpressionParser();

        boolean falseValue1 = parser.parseExpression("true and false").getValue(Boolean.class);
        log.info(falseValue1);

        boolean trueValue = parser.parseExpression("true or false").getValue(Boolean.class);
        log.info(trueValue);

        boolean falseValue2 = parser.parseExpression("!true").getValue(Boolean.class);
        log.info(falseValue2);
    }

    @Test
    void t17() {
        List<Integer> primes = new ArrayList<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17));

        ExpressionParser parser = new SpelExpressionParser();

        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        context.setVariable("primes", primes);

        // The #this and #root Variables
        List<Integer> primesGreaterThanTen = (List<Integer>) parser.parseExpression("#primes.?[#this>10]").getValue(context);
        log.info(primesGreaterThanTen);
    }

    @Test
    void t18() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("false ? 'trueExp' : 'falseExp'");
        String falseString = expression.getValue(String.class);
        log.info(falseString);
    }

    @Test
    void t19() {
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, Calendar.AUGUST, 9);
        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("name?:'Unknown'");

        String n = expression.getValue(tesla, String.class);
        log.info(n);// 'Unknown'

        String name = "Elvis Presley";
        String displayName = (name != null ? name : "Unknown");
        log.info(displayName);
    }

}
