package knowledge.异常和错误.error;

import org.junit.Test;

public class StartingApplicationContextDemo {

    @Test
    public void test() {
        /*
         * error starting ApplicationContext. To display the auto-configuration report re-run your application with 'debug' enabled.
         * ***************************
         * APPLICATION FAILED TO START
         * ***************************
         * Description:
         * Cannot determine embedded database driver class for database type NONE
         *
         *
         * 引入了spring-boot-starter-jdbc或mybatis-spring-boot-starter依赖
         * 却没有配置datasource
         *
         * 解决方法1：配置datasource
         * 解决方法2：删除相关依赖
         */
    }

}
