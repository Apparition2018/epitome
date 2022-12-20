package jar.apache.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * <a href="https://curator.apache.org/curator-recipes/curator-cache.html">Curator Cache</a>
 *
 * @author ljh
 * @since 2021/9/27 9:16
 */
public class CuratorCacheDemo {

    private static final String PATH = "/cache";

    /**
     * <a href="https://github.com/apache/curator/blob/master/curator-examples/src/main/java/cache/CuratorCacheExample.java">CuratorCache Example</a>
     */
    public static void main(String[] args) throws Exception {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        try (TestingServer testingServer = new TestingServer()) {
            try (CuratorFramework client = CuratorFrameworkFactory.newClient(testingServer.getConnectString(), new ExponentialBackoffRetry(1000, 3))) {
                client.start();
                try (CuratorCache cache = CuratorCache.build(client, PATH)) {
                    CuratorCacheListener listener = CuratorCacheListener.builder()
                            .forInitialized(() -> System.err.println("Cache initialized"))
                            .forCreates(node -> System.err.printf("Node created: [%s]%n", node))
                            .forChanges((oldNode, node) -> System.err.printf("Node changed. Old: [%s] New: [%s]%n", oldNode, node))
                            .forDeletes(oldNode -> System.err.printf("Node deleted. Old value: [%s]%n", oldNode))
                            .build();

                    cache.listenable().addListener(listener);
                    cache.start();

                    // 随机 新增/改变/删除 节点
                    for (int i = 0; i < 100; ++i) {
                        int depth = random.nextInt(1, 4);
                        String path = makeRandomPath(random, depth);
                        if (random.nextBoolean()) {
                            client.create().orSetData().creatingParentsIfNeeded().forPath(path, Long.toString(random.nextLong()).getBytes());
                        } else {
                            client.delete().quietly().deletingChildrenIfNeeded().forPath(path);
                        }
                        TimeUnit.MILLISECONDS.sleep(5);
                    }
                }
            }
        }
    }

    private static String makeRandomPath(ThreadLocalRandom random, int depth) {
        if (depth == 0) {
            return PATH;
        }
        return makeRandomPath(random, depth - 1) + "/" + random.nextInt(3);
    }
}
