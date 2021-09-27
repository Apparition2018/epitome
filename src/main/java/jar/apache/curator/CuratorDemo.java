package jar.apache.curator;

import l.demo.Demo;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Curator
 * Apache Curator 是分布式协调服务 Apache Zookeeper 的 Java 客户端库
 * <p>
 * Apache Curator：http://curator.apache.org/
 *
 * @author ljh
 * created on 2021/9/26 11:06
 */
public class CuratorDemo extends Demo {

    private static final String PATH = "/test";
    private static final ExecutorService POOL = Executors.newCachedThreadPool(new MyThreadFactory());

    @Test
    public void testCurator() throws Exception {
        try (CuratorFramework curatorFramework = CuratorUtils.getCuratorFramework()) {
            // 创建节点
            String path = curatorFramework.create()
                    // 创建不存在的父节点
                    .creatingParentsIfNeeded()
                    // 创建模式，默认持久
                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    // 路径和数据
                    .forPath(PATH, "data1".getBytes(StandardCharsets.UTF_8));

            // 判断节点是否存在
            Stat stat = curatorFramework.checkExists().forPath(path);

            // 设置数据
            curatorFramework.setData()
                    .withVersion(stat.getVersion())
                    .forPath(path, "data2".getBytes(StandardCharsets.UTF_8));

            // 获取数据
            p(new String(curatorFramework.getData().forPath(path)));

            // 获取子节点
            List<String> children = curatorFramework.getChildren().forPath("/");

            // 删除节点
            for (String child : children) {
                curatorFramework.delete()
                        // 删除子节点
                        .deletingChildrenIfNeeded()
                        // 异步后台操作完成时执行
                        .inBackground(new Background(), POOL)
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
        try (CuratorFramework curatorFramework = CuratorUtils.getCuratorFramework()) {
            CuratorOp createOp = curatorFramework.transactionOp().create()
                    .forPath(PATH, "data".getBytes(StandardCharsets.UTF_8));

            CuratorOp setDataOp = curatorFramework.transactionOp().setData()
                    .forPath(createOp.getTypeAndPath().getForPath(), "data".getBytes(StandardCharsets.UTF_8));

            CuratorOp deleteOp = curatorFramework.transactionOp().delete()
                    .forPath(createOp.getTypeAndPath().getForPath());

            List<CuratorTransactionResult> resultList = curatorFramework.transaction().forOperations(createOp, setDataOp, deleteOp);
            resultList.forEach(result -> System.out.printf("%s-%s%n", result.getForPath(), result.getType()));
        }
    }

}
