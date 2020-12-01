package knowledge.throwable;

import org.junit.Test;

/**
 * Exception
 *
 * @author ljh
 * created on 2020/9/4 13:59
 */
public class ExceptionDemo {

    /**
     * BeanCreationException
     * BeanCreationException: error creating bean with name 'xxx' ...... cause: Failed to bind to: /0.0.0.0:20880
     * 20880端口被占用
     * https://jingyan.baidu.com/article/3c48dd34491d47e10be358b8.html
     *
     * BindException
     * BindException: Address already in use: bind
     * 端口被占用
     * <p>
     * IllegalArgumentException
     * IllegalArgumentException: Result Maps collection does not contain value for java.lang.String
     * mapper 文件 resultType 写成了 resultMap
     * <p>
     * InstantiationException
     * 当应用程序试图使用 Class 类中的 newInstance 方法创建一个类的实例，而指定的类对象无法被实例化时，抛出该异常
     * 实例化失败有很多原因，包括但不仅限于以下原因：
     * 1.类对象表示一个抽象类、接口、数组类、基本类型、void
     * 2.类没有无参构造方法
     * <p>
     * RpcException
     * RpcException: Invoke remote method timeout. method: ...
     * 调用远程方法超时
     * 增加超时时间：@Reference(version = "1.0.0", timeout = 10000)
     * <p>
     * SecurityException
     * SecurityException: Prohibited package name: java
     * 被禁止的包名：在src/main/java下不能直接起名字为java的包
     */
    @Test
    public void testException() {
    }
}
