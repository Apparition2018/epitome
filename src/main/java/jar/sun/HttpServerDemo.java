package jar.sun;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import static l.demo.Demo.HELLO_WORLD;
import static l.demo.Demo.PORT;

/**
 * HttpServer
 * <p><a href="https://www.cnblogs.com/aspwebchh/p/8300945.html">使用 HttpServer 构建 Web 应用</a>
 *
 * @author ljh
 * @since 2023/2/9 9:54
 */
public class HttpServerDemo {

    /**
     * <a href="http://localhost:3333/test">test</a>
     */
    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(PORT), 0);
        httpServer.createContext("/test", new MyHandler());
        httpServer.start();
    }

    private static class MyHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try (OutputStream responseBody = exchange.getResponseBody()) {
                responseBody.write(HELLO_WORLD.getBytes());
            }
        }
    }
}
