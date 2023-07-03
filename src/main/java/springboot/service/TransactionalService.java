package springboot.service;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import springboot.domain.master.Demo;
import springboot.mapper.master.DemoMapper;

import java.io.Serial;
import java.sql.SQLException;

import static l.demo.Demo.pe;

/**
 * TransactionalService
 *
 * @author ljh
 * @since 2021/11/18 9:10
 */
@Service
@AllArgsConstructor
public class TransactionalService {

    private final DemoMapper demoMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    /**
     * 回滚 Checked Exception
     * <pre>
     * SQLException 属于 Checked Exception，
     * 如需对其生效，设置 rollbackFor = Exception.class 或 rollbackFor = SQLException.class
     * </pre>
     */
    @Transactional(rollbackFor = SQLException.class)
    public void rollbackCheckedException() throws SQLException {
        demoMapper.insert(new Demo().setName("a"));
        throw new SQLException("Mock SQLException");
    }

    /**
     * <a href="https://mp.weixin.qq.com/s/vp7EOwmuK_o9CxSg5r-wBg">@TransactionalEventListener</a>
     */
    @Transactional
    public void transactionalEventListener() {
        applicationEventPublisher.publishEvent(new MyEvent(this));
        demoMapper.insert(new Demo().setName("a"));
        throw new RuntimeException("Mock RuntimeException");
    }

    /**
     * TransactionSynchronizationManager
     * <pre>
     * beforeCommit         事务提交之前调用。例如：刷新事务会话或将会话映射到数据库
     * beforeCompletion     事务提交/回滚之前调用。可以在事务完成之前执行资源清理
     * afterCommit          事务提交之后调用。可以在主事务成功提交后立即执行进一步的操作。例如：确认消息或电子邮件。
     * afterCompletion      事务提交/回滚之后调用。可以在事务完成后执行资源清理
     * </pre>
     */
    @Transactional
    public void transactionSynchronizationManager() {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void beforeCommit(boolean readOnly) {
                    pe("beforeCommit: " + readOnly);
                }

                @Override
                public void beforeCompletion() {
                    pe("beforeCompletion");
                }

                @Override
                public void afterCommit() {
                    pe("afterCommit");
                }

                @Override
                public void afterCompletion(int status) {
                    pe("afterCompletion: " + status);
                }
            });
        }
        demoMapper.insert(new Demo().setName("a"));
        throw new RuntimeException("Mock RuntimeException");
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void beforeCommit(MyEvent event) {
        pe("before commit: " + event.getSource());
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void afterCompletion(MyEvent event) {
        pe("after completion: " + event.getSource());
    }

    private static class MyEvent extends ApplicationEvent {
        @Serial
        private static final long serialVersionUID = 4600119726191005175L;

        public MyEvent(Object source) {
            super(source);
        }
    }
}
