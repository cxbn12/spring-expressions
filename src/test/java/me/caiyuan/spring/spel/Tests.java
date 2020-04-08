package me.caiyuan.spring.spel;

import lombok.extern.log4j.Log4j2;
import me.caiyuan.spring.spel.pojo.Inventor;
import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

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
        log.info(numbers1);

        int[] numbers2 = (int[]) parser.parseExpression("new int[]{1,2,3}").getValue();
        log.info(numbers2);

        int[][] numbers3 = (int[][]) parser.parseExpression("new int[4][5]").getValue();
        log.info(numbers3);
    }

}
