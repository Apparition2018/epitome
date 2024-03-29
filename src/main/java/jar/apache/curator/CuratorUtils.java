package jar.apache.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * CuratorUtils
 *
 * @author ljh
 * @since 2021/9/27 9:30
 */
@Slf4j
public final class CuratorUtils {
    private CuratorUtils() {
        throw new AssertionError(String.format("No %s instances for you!", this.getClass().getName()));
    }

    public static CuratorFramework getCuratorFramework() {
        CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("localhost:2182,localhost:2183,localhost:2184")
            .connectionTimeoutMs(5000)
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 5))
            .namespace("test")
            .build();
        client.getConnectionStateListenable().addListener((curatorFramework, newState) -> {
            if (newState == ConnectionState.CONNECTED) log.info("连接成功");
        });
        log.info("连接中...");
        client.start();
        return client;
    }
}
