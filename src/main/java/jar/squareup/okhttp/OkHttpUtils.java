package jar.squareup.okhttp;

/**
 * OkHttpUtils
 * OkHttp 的封装使用：https://blog.csdn.net/qq_40543575/article/details/79054996
 * OkHttp 的简单使用和封装：https://www.cnblogs.com/wjtaigwh/p/6210534.html
 * OkHttp 封装进阶：https://www.jianshu.com/p/219ee2afb4f3
 *
 * @author ljh
 * created on 2021/8/3 16:28
 */
public class OkHttpUtils {

    private static volatile OkHttpUtils instance;

    private OkHttpUtils() {
    }

    public static OkHttpUtils getInstance() {
        if (instance == null) {
            synchronized (OkHttpUtils.class) {
                if (instance == null) {
                    instance = new OkHttpUtils();
                }
            }
        }
        return instance;
    }
}
