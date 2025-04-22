package jar.hutool.http;

import cn.hutool.core.net.multipart.UploadFile;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpUtil;
import org.junit.jupiter.api.Test;

import static l.demo.Demo.DEMO_DIR_PATH;

/**
 * <a href="https://doc.hutool.cn/pages/SimpleServer/">SimpleServer</a>   简易 Http 服务器
 * <p>用于在不引入 Tomcat、Jetty 等容器的情况下，实现简单的 Http 请求处理
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/http/server/SimpleServer.html">SimpleServer api</a>
 *
 * @author ljh
 * @since 2020/11/2 23:40
 */
public class SimpleServerDemo {

    @Test
    public void addAction() {
        HttpUtil.createServer(6666)
                // 返回 JSON 数据测试
                .addAction("/restTest", (req, res) -> res.write("{\"id\": 1, \"msg\": \"OK\"}", ContentType.JSON.toString()))
                .start();

        HttpUtil.createServer(7777)
                // http://localhost:8888/formTest?a=1&a=2&b=3
                .addAction("/formTest", (req, res) ->
                        res.write(req.getParams().toString(), ContentType.TEXT_PLAIN.toString())
                ).start();

        // 文件上传
        HttpUtil.createServer(8888)
                .addAction("/file", (req, res) -> {
                            final UploadFile file = req.getMultipart().getFile("file");
                            // 传入目录，默认读取 HTTP 头中的文件名然后创建文件
                            file.write(DEMO_DIR_PATH);
                            res.write("OK!", ContentType.TEXT_PLAIN.toString());
                        }
                )
                .start();
    }

    /** 简单的文件服务器 */
    @Test
    public void setRoot() {
        HttpUtil.createServer(8888)
                // 设置默认根目录
                .setRoot(DEMO_DIR_PATH)
                .start();
    }
}
