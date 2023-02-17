package knowledge.io.nio.file;

import com.sun.nio.file.SensitivityWatchEventModifier;
import l.demo.Demo;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

/**
 * <a href="https://docs.oracle.com/javase/8/docs/api/index.html?java/nio/file/WatchService.html">WatchService</a>
 * <pre>
 * 监视已注册对象的更改和事件的监视服务。
 * 例如，文件管理器可以使用监视服务来监视目录的更改，以便在创建或删除文件时更新其显示的文件列表。
 * </pre>
 * <p>
 * <a href="https://docs.oracle.com/javase/8/docs/api/index.html?java/nio/file/WatchKey.html">WatchKey</a>
 * <p>监视对象的令牌
 * <p>
 * <a href="https://docs.oracle.com/javase/8/docs/api/index.html?java/nio/file/WatchEvent.html">WatchEvent</a>
 * <p>注册到 WatchService 的对象的事件或重复事件
 *
 * @author ljh
 * @since 2020/11/6 10:32
 */
public class WatchServiceDemo extends Demo {

    /**
     * <a href="https://www.zhihu.com/question/264700166">WatchService，重复触发 Modify 事件</a>
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        // 新建 WatchService
        WatchService watchService = FileSystems.getDefault().newWatchService();
        // 注册监听对象，监听对象的创建、修改、删除事件，高频率监听（2秒一次，默认10秒）
        Paths.get(DEMO_PATH).register(watchService,
                new WatchEvent.Kind[]{StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE},
                SensitivityWatchEventModifier.HIGH);

        int eventCount = 0;
        while (true) {
            // 检索并移除下一个 WatchKey，如果不存在则返回 null
            WatchKey watchKey = watchService.poll(3, TimeUnit.SECONDS);
            // 检索并移除下一个 WatchKey，如果不存在则等待
            // WatchKey watchKey = watchService.take();

            if (null == watchKey) {
                continue;
            }

            // 检索并删除此 WatchKey 的所有挂起事件，返回已检索的事件列表
            for (WatchEvent<?> watchEvent : watchKey.pollEvents()) {
                // 事件类型
                WatchEvent.Kind<?> kind = watchEvent.kind();

                switch (kind.name()) {
                    // 丢失或丢弃的特殊事件
                    case "OVERFLOW" -> p(++eventCount + ": OVERFLOW");
                    case "ENTRY_CREATE" -> p(++eventCount + ": File " + watchEvent.context() + " is created!");
                    case "ENTRY_MODIFY" -> p(++eventCount + ": File " + watchEvent.context() + " is changed!");
                    case "ENTRY_DELETE" -> p(++eventCount + ": File " + watchEvent.context() + " is deleted!");
                    default -> p(++eventCount + ": UNKNOWN EVENT!");
                }
                // 重置 WatchKey
                watchKey.reset();
            }
        }
    }
}
