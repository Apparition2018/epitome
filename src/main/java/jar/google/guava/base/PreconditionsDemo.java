package jar.google.guava.base;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://github.com/google/guava/wiki/PreconditionsExplained">Preconditions</a>  先决条件
 * <pre>
 * 静态便利方法，帮助方法或构造函数检查调用是否正确(即是否满足其先决条件)。
 * 如果不满足先决条件，Preconditions方法将抛出指定类型的未检查异常，这将帮助抛出异常的方法通知调用者犯了错误。
 * </pre>
 *
 * @author ljh
 * @see <a href="http://www.ibloger.net/article/3298.html">Guava Preconditions</a>
 * @see <a href="https://guava.dev/releases/snapshot-jre/api/docs/com/google/common/base/Preconditions.html">Preconditions api</a>
 * @since 2019/8/8 19:39
 */
public class PreconditionsDemo {

    private record User(String userName, String password) {
    }

    /**
     * checkArgument:
     * <p>static void	checkArgument(boolean expression[, Object errorMessage])
     * <p>检查 expression 是否为真，用于检查方法中参数，抛 IllegalArgumentException 异常
     * <p>
     * <p>checkNotNull:
     * <p>static <T> T	checkNotNull(T reference[, Object errorMessage])
     * <p>检查 reference 是否为 null，抛 NullPointerException 异常
     * <p>
     * <p>checkState:
     * <p>static void	checkState(boolean expression[, Object errorMessage])
     * <p>检查 expression 是否为真，但不涉及方法中的任何参数，抛 IllegalStateException 异常
     */
    @Test
    public void check() {
        this.login(null, "123");
    }

    private void login(String userName, String password) {
        Preconditions.checkArgument(!(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)), "用户名或密码不能为空");
        User user = queryUserByUserNameAndPassword(userName, password); // 模拟数据库按用户名和密码查询用户
        Preconditions.checkNotNull(user, "用户名或密码错误");
    }

    private User queryUserByUserNameAndPassword(String userName, String password) {
        return new User(userName, password);
    }

    /**
     * static int	checkElementIndex(int index, int size[, Object errorMessage])
     * <p>检查 index 作为索引值对某个列表、字符串或数组是否有效
     * <p>
     * <p>static int	checkPositionIndex(int index, int size[, Object errorMessage])
     * <p>检查 index 作为位置值对某个列表、字符串或数组是否有效
     * <p>
     * <p>static int	checkPositionIndexes(int start, int end, int size)
     * <p>检查 [start, end] 表示的位置范围对某个列表、字符串或数组是否有效
     */
    @Test
    public void checkIndex() {
        ImmutableList<String> list = ImmutableList.of("A", "B", "C", "E", "F");

        Preconditions.checkElementIndex(6, list.size());    // index (6) must be less than size (5)
        Preconditions.checkPositionIndex(6, list.size());   // index (6) must not be greater than size (5)
        Preconditions.checkPositionIndexes(2, 6, list.size());  // end index (6) must not be greater than size (5)
    }
}
