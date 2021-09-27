package jar.apache.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * Curator Cache：https://curator.apache.org/curator-recipes/curator-cache.html
 *
 * @author ljh
 * created on 2021/9/27 9:16
 */
public class CuratorCacheDemo {

    private static final String PATH = "/cache";

    public static void main(String[] args) throws Exception {
        try (CuratorFramework client = CuratorUtils.getCuratorFramework()) {
            try (CuratorCache cache = CuratorCache.build(client, PATH)) {
                CuratorCacheListener listener = CuratorCacheListener.builder()
                        .forInitialized(() -> System.err.println("Cache initialized"))
                        .forCreates(node -> System.err.printf("Node created: [%s]%n", node))
                        .forChanges((oldNode, node) -> System.err.printf("Node changed. Old: [%s] New: [%s]%n", oldNode, node))
                        .forDeletes(oldNode -> System.err.printf("Node deleted. Old value: [%s]%n", oldNode))
                        .build();

                cache.listenable().addListener(listener);
                cache.start();

                client.create().creatingParentsIfNeeded().forPath("/cache/1");
                TimeUnit.SECONDS.sleep(3);
                client.setData().forPath("/cache/1", "data".getBytes(StandardCharsets.UTF_8));
                TimeUnit.SECONDS.sleep(3);
                client.setData().forPath("/cache/1", "data2".getBytes(StandardCharsets.UTF_8));
                TimeUnit.SECONDS.sleep(3);
                client.delete().deletingChildrenIfNeeded().forPath("/cache/1");
                TimeUnit.SECONDS.sleep(3);
            }
        }
    }
}
