package org.ljh;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.broker.BrokerController;
import org.apache.rocketmq.common.BrokerConfig;
import org.apache.rocketmq.common.TopicConfig;
import org.apache.rocketmq.common.constant.PermName;
import org.apache.rocketmq.common.namesrv.NamesrvConfig;
import org.apache.rocketmq.namesrv.NamesrvController;
import org.apache.rocketmq.remoting.netty.NettyClientConfig;
import org.apache.rocketmq.remoting.netty.NettyServerConfig;
import org.apache.rocketmq.remoting.protocol.body.ClusterInfo;
import org.apache.rocketmq.store.config.MessageStoreConfig;
import org.apache.rocketmq.tools.admin.DefaultMQAdminExt;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 进程内启动嵌入式 RocketMQ（NameServer + Broker），供集成测试使用，无需依赖外部部署。
 * <pre>
 * NameServer：Topic路由注册中心 ①Broker管理 ②路由信息管理
 * Broker：消息的存储、投递和查询以及服务高可用保证
 * </pre>
 *
 * @author ljh
 * @see <a href="https://github.com/apache/rocketmq/blob/master/docs/cn/architecture.md">architecture</a>
 * @since 2026/5/5
 */
@Slf4j
public class EmbeddedRocketMQServer {

    private final NamesrvController namesrvController;
    private final BrokerController brokerController;
    private final String namesrvAddr;

    public EmbeddedRocketMQServer() throws Exception {
        // ========== 启动嵌入式 NameServer ==========
        String baseDir = System.getProperty("java.io.tmpdir") + File.separator + "rocketmq-" + UUID.randomUUID();
        NamesrvConfig namesrvConfig = new NamesrvConfig();
        namesrvConfig.setKvConfigPath(baseDir + File.separator + "namesrv" + File.separator + "kvConfig.json");
        namesrvConfig.setConfigStorePath(baseDir + File.separator + "namesrv" + File.separator + "namesrv.properties");
        NettyServerConfig nameServerNettyConfig = new NettyServerConfig();
        nameServerNettyConfig.setListenPort(0);

        namesrvController = new NamesrvController(namesrvConfig, nameServerNettyConfig);
        namesrvController.initialize();
        namesrvController.start();

        namesrvAddr = "127.0.0.1:" + nameServerNettyConfig.getListenPort();

        // ========== 启动嵌入式 Broker ==========
        BrokerConfig brokerConfig = new BrokerConfig();
        brokerConfig.setBrokerName("EmbeddedBroker");
        brokerConfig.setBrokerIP1("127.0.0.1");
        brokerConfig.setNamesrvAddr(namesrvAddr);
        brokerConfig.setEnablePropertyFilter(true);
        // 开启自动创建 Topic（Producer 发消息时动态创建）
        brokerConfig.setAutoCreateTopicEnable(true);
        // 增大线程池，避免内嵌环境请求堆积
        brokerConfig.setSendMessageThreadPoolNums(16);
        brokerConfig.setPullMessageThreadPoolNums(32);

        MessageStoreConfig storeConfig = new MessageStoreConfig();
        storeConfig.setStorePathRootDir(baseDir + File.separator + "store");
        storeConfig.setStorePathCommitLog(baseDir + File.separator + "store" + File.separator + "commitlog");
        storeConfig.setHaListenPort(0);
        storeConfig.setMappedFileSizeCommitLog(1024 * 1024 * 100);

        NettyServerConfig brokerNettyConfig = new NettyServerConfig();
        brokerNettyConfig.setListenPort(0);

        brokerController = new BrokerController(brokerConfig, brokerNettyConfig, new NettyClientConfig(), storeConfig);
        brokerController.initialize();
        brokerController.start();

        waitForBrokerReady();
        createTopicIfNotExists("order-topic", 4);
    }

    /** 等待 Broker 自动注册到 NameServer（轮询，最多 5 秒） */
    private void waitForBrokerReady() throws Exception {
        /* 用 Admin API 验证 Broker 已就绪并注册到 NameServer */
        DefaultMQAdminExt admin = new DefaultMQAdminExt();
        admin.setNamesrvAddr(namesrvAddr);
        admin.setAdminExtGroup("verify-admin-group");
        admin.start();
        try {
            long deadline = System.currentTimeMillis() + 5_000;
            while (System.currentTimeMillis() < deadline) {
                // 查询集群信息，能查到就说明 Broker 已成功注册到 NameServer
                ClusterInfo info = admin.examineBrokerClusterInfo();
                if (info != null && !info.getBrokerAddrTable().isEmpty()) {
                    log.info("[TEST] Broker 已就绪，注册到 NameServer");
                    break;
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } finally {
            admin.shutdown();
        }
    }

    /** 预创建 Topic，确保消费者订阅时 Topic 已存在 */
    private void createTopicIfNotExists(String topic, int queueNum) {
        try {
            // 直接在 Broker 内存中创建 Topic 配置，不依赖 TBW102
            TopicConfig topicConfig = new TopicConfig();
            topicConfig.setTopicName(topic);
            topicConfig.setReadQueueNums(queueNum);
            topicConfig.setWriteQueueNums(queueNum);
            // 设置读写权限：PERM_READ | PERM_WRITE = 1 | 2 = 3
            topicConfig.setPerm(PermName.PERM_READ | PermName.PERM_WRITE);
            brokerController.getTopicConfigManager().updateTopicConfig(topicConfig);
            log.info("[TEST] Topic {} 创建成功", topic);
        } catch (Exception e) {
            log.warn("[TEST] 预创建 Topic {} 失败: {}", topic, e.getMessage());
        }
    }

    public String getNameServerAddr() {
        return namesrvAddr;
    }

    public void shutdown() {
        if (brokerController != null) {
            brokerController.shutdown();
            brokerController.getMessageStore().destroy();
        }
        if (namesrvController != null) {
            namesrvController.shutdown();
        }
    }
}
