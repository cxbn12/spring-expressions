package me.caiyuan.spring.spel;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Map;

@Log4j2
@SpringJUnitConfig(locations = "classpath:applicationContext.xml")
public class BeanExpressionTests {

    @Value("#{environment}")
    Environment env;

    @Value("#{items}")
    List<?> items;
    @Value("#{items[1]}")
    String item;

    @Value("#{systemProperties}")
    Map<?, ?> systemProperties;
    @Value("#{systemProperties['os.name']}")
    String value;

    @Test
    void test() {
        log.info(env);

        log.info(items);
        log.info(item);

        log.info(systemProperties);
        log.info(value);
    }

}
