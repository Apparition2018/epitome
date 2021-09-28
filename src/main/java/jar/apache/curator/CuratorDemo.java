package jar.apache.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.transaction.CuratorOp;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Curator
 * Apache Curator 是分布式协调服务 Apache Zookeeper 的 Java 客户端库
 * <p>
 * 应用：
 * 1.选举 {@link CuratorElection}
 * 2.缓存 {@link CuratorCacheDemo}
 * 3.锁 {@link CuratorDistributedLock}
 * 4.服务发现：https://github.com/apache/curator/tree/master/curator-examples/src/main/java/discovery
 * 5.发布和订阅：https://github.com/apache/curator/tree/master/curator-examples/src/main/java/pubsub
 * 6.异步：https://github.com/apache/curator/blob/master/curator-examples/src/main/java/async/AsyncExamples.java
 * 7.modeled：https://github.com/apache/curator/tree/master/curator-examples/src/main/java/modeled
 * <p>
 * Apache Curator：http://curator.apache.org/
 *
 * @author ljh
 * created on 2021/9/26 11:06
 */
public class CuratorDemo {

    private static final String PATH = "/test";

    /**
     * framework：https://github.com/apache/curator/tree/master/curator-examples/src/main/java/framework
     */
    @Test
    public void testCurator() throws Exception {
        try (CuratorFramework client = CuratorUtils.getCuratorFramework()) {
            // 创建节点
            String path = client.create()
                    // 创建不存在的父节点
                    .creatingParentsIfNeeded()
                    // 创建模式，默认持久
                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    // 路径和数据
                    .forPath(PATH, "data1".getBytes(StandardCharsets.UTF_8));

            // 判断节点是否存在
            Stat stat = client.checkExists().forPath(path);

            // 设置数据
            client.setData()
                    .withVersion(stat.getVersion())
                    .forPath(path, "data2".getBytes(StandardCharsets.UTF_8));

            // 获取数据
            System.out.println(new String(client.getData().forPath(path)));

            // 获取子节点
            List<String> children = client.getChildren().forPath("/");

            // 删除节点
            for (String child : children) {
                client.delete()
                        // 删除子节点
                        .deletingChildrenIfNeeded()
                        // 异步后台操作完成时执行
                        .inBackground(new Background(), Executors.newFixedThreadPool(2))
                        .forPath("/" + child);
            }
            TimeUnit.SECONDS.sleep(6);
        }
    }

    private static class Background implements BackgroundCallback {
        @Override
        public void processResult(CuratorFramework client, CuratorEvent event) {
            System.err.printf("eventType: %s, resultCode: %s%n", event.getType(), event.getResultCode());
        }
    }

    /**
     * 事务
     */
    @Test
    public void testTransaction() throws Exception {
        try (CuratorFramework client = CuratorUtils.getCuratorFramework()) {
            CuratorOp createOp = client.transactionOp().create().forPath(PATH, "some data".getBytes(StandardCharsets.UTF_8));
            CuratorOp setDataOp = client.transactionOp().setData().forPath(createOp.getTypeAndPath().getForPath(), "other data".getBytes(StandardCharsets.UTF_8));
            CuratorOp deleteOp = client.transactionOp().delete().forPath(createOp.getTypeAndPath().getForPath());

            List<CuratorTransactionResult> resultList = client.transaction().forOperations(createOp, setDataOp, deleteOp);
            resultList.forEach(result -> System.err.printf("%s - %s%n", result.getForPath(), result.getType()));
        }
    }

}
