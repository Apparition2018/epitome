package jar.apache.curator;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.curator.utils.CloseableUtils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Curator 选举
 * <pre>
 * <a href="http://curator.apache.org/curator-recipes/leader-latch.html">Latch</a>      一直做 Leader，直到崩溃
 * <a href="http://curator.apache.org/curator-recipes/leader-election.html">Election</a>    轮流做 Leader
 * </pre>
 *
 * @author ljh
 * @since 2021/9/28 2:26
 */
public class CuratorElection {

    private static final int CLIENT_QTY = 10;
    private static final String LEADER_PATH = "/leader";

    /**
     * <a href="https://github.com/apache/curator/tree/master/curator-examples/src/main/java/leader">LeaderSelector Example</a>
     */
    public static void main(String[] args) throws Exception {
        List<CuratorFramework> clients = Lists.newArrayList();
        List<ExampleClient> exampleClients = Lists.newArrayList();
        TestingServer testingServer = new TestingServer();
        try {
            for (int i = 0; i < CLIENT_QTY; i++) {
                CuratorFramework client = CuratorFrameworkFactory.newClient(testingServer.getConnectString(), new ExponentialBackoffRetry(1000, 3));
                clients.add(client);
                client.start();

                ExampleClient exampleClient = new ExampleClient(client, LEADER_PATH, "Client #" + i);
                exampleClients.add(exampleClient);
                exampleClient.start();
            }
            System.out.println("Press enter/return to quit\n");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } finally {
            System.out.println("Shutting down...");
            for (ExampleClient exampleClient : exampleClients) {
                CloseableUtils.closeQuietly(exampleClient);
            }
            for (CuratorFramework client : clients) {
                CloseableUtils.closeQuietly(client);
            }
            CloseableUtils.closeQuietly(testingServer);
        }
    }

    static class ExampleClient extends LeaderSelectorListenerAdapter implements Closeable {
        private final String name;
        private final LeaderSelector leaderSelector;
        private final AtomicInteger leaderCount = new AtomicInteger();

        public ExampleClient(CuratorFramework client, String path, String name) {
            this.name = name;
            leaderSelector = new LeaderSelector(client, path, this);
            leaderSelector.autoRequeue();
        }

        public void start() throws IOException {
            leaderSelector.start();
        }

        public void close() throws IOException {
            leaderSelector.close();
        }

        @Override
        public void takeLeadership(CuratorFramework client) throws Exception {
            final int waitSeconds = new Random().nextInt(5) + 1;

            System.out.println(name + " is now the leader. Waiting " + waitSeconds + " seconds...");
            System.out.println(name + " has been leader " + leaderCount.getAndIncrement() + " time(s) before.");

            try {
                TimeUnit.SECONDS.sleep(waitSeconds);
            } catch (InterruptedException e) {
                System.err.println(name + " was interrupted.");
                Thread.currentThread().interrupt();
            } finally {
                System.out.println(name + " relinquishing leadership.\n");
            }
        }
    }
}
