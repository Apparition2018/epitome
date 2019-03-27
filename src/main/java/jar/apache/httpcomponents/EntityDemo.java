package jar.apache.httpcomponents;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 实体可以在一些请求和响应中找到，使用了实体的请求被称为封闭实体请求。HTTP规范定义了两种封闭实体的方法：POST和PUT。
 * 响应通常期望包含一个内容实体。这个规则也有特例，比如HEAD方法的响应和204 No Content，304 Not Modified和205 Reset Content响应。
 * <p>
 * HttpClient根据其内容出自何处区分三种类型的实体：
 * streamed流式：内容从流中获得，或者在运行中产生。特别是这种分类包含从HTTP响应中获取的实体。流式实体是不可重复生成的
 * self-contained自我包含式：内容在内存中或通过独立的连接或其它实体中获得。自我包含式的实体是可以重复生成的。这种类型的实体会经常用于封闭HTTP请求的实体
 * wrapping包装式：内容从另外一个实体中获得
 */
public class EntityDemo {
    public static void main(String[] args) throws ParseException, IOException {
        // 当为一个传出报文创建实体时，这个元数据不得不通过实体创建器来提供。
        StringEntity myEntity = new StringEntity("important message", "UTF-8");
        System.out.println(myEntity.getContentType());                  // Content-Type: text/plain; charset=UTF-8
        System.out.println(myEntity.getContentLength());                // 17
        // System.out.println(EntityUtils.getContentCharSet(myEntity)); // UTF-8
        System.out.println(EntityUtils.toString(myEntity));             // important message
        System.out.println(EntityUtils.toByteArray(myEntity).length);   // 17

        /*
         * 在一些情况下可能会不止一次的读取实体。此时实体内容必须以某种方式在内存或磁盘上被缓冲起来。
         * 最简单的方法是通过使用 BufferedHttpEntity 类来包装源实体完成。这会引起源实体内容被读取到内存的缓冲区中。
         * 在其它所有方式中，实体包装器将会得到源实体。
         */
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://localhost/");
        HttpResponse response = client.execute(httpget);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            entity = new BufferedHttpEntity(entity);
        }


    }

}
