package org.ljh.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 演示 spring-boot-starter-cache 的三个核心注解。
 */
@Service
public class UserCacheService {

    private static final Logger log = LoggerFactory.getLogger(UserCacheService.class);

    /** 模拟数据库 */
    private final Map<Long, String> userStore = new ConcurrentHashMap<>();

    /** 记录 getUser 真正执行（未命中缓存）的次数，用于测试中验证缓存命中效果 */
    private final AtomicLong loadCount = new AtomicLong();

    private final CacheManager cacheManager;

    public UserCacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * 先查缓存（cacheNames=users，key=#id）：命中直接返回，不执行方法体；
     * 未命中则执行方法体，并把返回值写入缓存。
     */
    @Cacheable(cacheNames = "users", key = "#id")
    public String getUser(Long id) {
        long n = loadCount.incrementAndGet();
        log.info("==> 未命中缓存，第 {} 次真正加载 user, id={}", n, id);
        String name = userStore.get(id);
        return name != null ? name : "user-" + id;
    }

    /**
     * 无论是否命中都会执行方法体，并把返回值写回缓存（用于数据更新后同步缓存）。
     */
    @CachePut(cacheNames = "users", key = "#id")
    public String updateUser(Long id, String name) {
        log.info("==> updateUser(id={}, name={}) 执行并同步缓存", id, name);
        userStore.put(id, name);
        return name;
    }

    /**
     * 删除缓存中的指定 key（数据删除后同步清理缓存）。
     */
    @CacheEvict(cacheNames = "users", key = "#id")
    public void deleteUser(Long id) {
        log.info("==> deleteUser(id={}) 删除用户并清空缓存", id);
        userStore.remove(id);
    }

    public long getLoadCount() {
        return loadCount.get();
    }

    /**
     * 重置内部状态，供测试在每个用例前隔离使用：
     * 清空 loadCount 计数、模拟数据库以及 Spring 缓存。
     */
    public void reset() {
        loadCount.set(0);
        userStore.clear();
        Objects.requireNonNull(cacheManager.getCache("users")).clear();
    }
}
