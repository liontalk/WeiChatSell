package cn.vitalking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 日志测试类
 * @date 2018-08-19 9:03
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest {

    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test() {
        String password = "Hello World!";
        String name="zhouzhe";
        logger.info("name:{},password:{}",name,password);
        logger.info("我是测试类！");
        logger.debug("Debug!");
        logger.warn("嗯，有点问题！");
        logger.error("出错了");
    }
}
