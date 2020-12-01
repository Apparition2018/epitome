package knowledge.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock
 * StampedLock 为了解决读操作很多，写操作很少的情况下，可能长时间存在读锁而无法获取写锁的线程饥饿问题
 * StampedLock 支持读写锁互相转换；ReentrantReadWriteLock 写锁可以降级成读锁，但反过来则不行
 * <p>
 * StampedLock 有三种模式：
 * 1.writing：独占写锁
 * 2.reading：悲观读锁，与独占写互斥，与乐观读共享
 * 3.optimistic reading：乐观读，没有加锁
 * <p>
 * 注意事项：
 * 1.是不可重入锁，如果当前线程已经获取了写锁，再次重复获取的话就会死锁
 * 2.不支持 Condition 条件将线程等待
 * 3.写锁和悲观读锁加锁成功之后，都会返回一个 stamp；然后解锁的时候，需要传入这个 stamp
 * <p>
 * 高性能解决线程饥饿的利器 StampedLock：https://zhuanlan.zhihu.com/p/257118211?utm_source=wechat_timeline
 * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/StampedLock.html
 *
 * @author ljh
 * created on 2020/12/1 10:37
 */
public class StampedLockDemo {

    private static StampedLock lock = new StampedLock();
    private static Map<Integer, String> idMap = new HashMap<>();

    /**
     * 独占写
     */
    public void write(Integer key, String value) {
        long stamp = lock.writeLock();
        try {
            idMap.put(key, value);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    /**
     * 乐观读
     */
    public String optimisticRead(Integer key) {
        // 1. 尝试通过乐观读模式读取数据，非阻塞
        long stamp = lock.tryOptimisticRead();
        // 2. 读取数据到当前线程栈
        String currentValue = idMap.get(key);
        // 3. 校验是否被其他线程修改过,true 表示未修改，否则需要加悲观读锁
        if (!lock.validate(stamp)) {
            // 4. 上悲观读锁，并重新读取数据到当前线程局部变量
            stamp = lock.readLock();
            try {
                currentValue = idMap.get(key);
            } finally {
                lock.unlockRead(stamp);
            }
        }
        // 5. 若校验通过，则直接返回数据
        return currentValue;
    }

    /**
     * 悲观读
     */
    public String read(Integer key) {
        long stamp = lock.readLock();
        String currentValue;
        try {
            currentValue = idMap.get(key);
        } finally {
            lock.unlockRead(stamp);
        }
        return currentValue;
    }

    /**
     * 如果数据不存在则从数据库读取添加到 map 中，锁升级运用
     */
    public String writeIfNotExist(Integer key, String value) {
        // 获取读锁，也可以直接调用 get 方法使用乐观读
        long stamp = lock.readLock();
        String currentValue = idMap.get(key);
        // 缓存为空则尝试上写锁从数据库读取数据并写入缓存
        try {
            while (Objects.isNull(currentValue)) {
                // 尝试升级写锁
                long wl = lock.tryConvertToWriteLock(stamp);
                // 不为 0 升级写锁成功
                if (wl != 0L) {
                    // 模拟从数据库读取数据, 写入缓存中
                    stamp = wl;
                    currentValue = value;
                    idMap.put(key, currentValue);
                    break;
                } else {
                    // 升级失败，释放之前加的读锁并上写锁，通过循环再试
                    lock.unlockRead(stamp);
                    stamp = lock.writeLock();
                }
            }
        } finally {
            // 释放最后加的锁
            lock.unlock(stamp);
        }
        return currentValue;
    }
}
