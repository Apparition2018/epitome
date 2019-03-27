package knowledge.异常和错误.exception;

import org.junit.Test;

public class BeanCreationExceptionDemo {

    @Test
    public void test() {
        /*
         * BeanCreationException: error creating bean with name 'xxx' ...... cause: Failed to bind to: /0.0.0.0:20880
         *
         * 20880端口被占用
         * https://jingyan.baidu.com/article/3c48dd34491d47e10be358b8.html
         */
    }

}
