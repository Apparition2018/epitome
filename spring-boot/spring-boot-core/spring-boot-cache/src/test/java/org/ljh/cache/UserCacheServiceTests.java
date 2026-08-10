package org.ljh.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ljh.cache.service.UserCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserCacheServiceTests {

    @Autowired
    private UserCacheService userCacheService;

    @BeforeEach
    void resetState() {
        userCacheService.reset();
    }

    @Test
    @DisplayName("@Cacheable 相同 key 第二次调用命中缓存")
    void cacheableHit() {
        String first = userCacheService.getUser(1L);
        String second = userCacheService.getUser(1L);

        assertThat(second).isEqualTo(first);
        assertThat(userCacheService.getLoadCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("@Cacheable 不同 key 互不影响")
    void cacheableDifferentKey() {
        userCacheService.getUser(1L);
        userCacheService.getUser(2L);

        assertThat(userCacheService.getLoadCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("@CachePut 更新后缓存立即同步")
    void cachePutSync() {
        userCacheService.getUser(1L);
        userCacheService.updateUser(1L, "张三");

        String cached = userCacheService.getUser(1L);

        assertThat(cached).isEqualTo("张三");
        assertThat(userCacheService.getLoadCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("@CacheEvict 清缓存后重新加载")
    void cacheEvictReload() {
        userCacheService.getUser(1L);
        userCacheService.deleteUser(1L);

        userCacheService.getUser(1L);

        assertThat(userCacheService.getLoadCount()).isEqualTo(2);
    }
}
