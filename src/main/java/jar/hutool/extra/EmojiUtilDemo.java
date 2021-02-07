package jar.hutool.extra;

import cn.hutool.extra.emoji.EmojiUtil;
import org.junit.jupiter.api.Test;

/**
 * EmojiUtil
 * 需要引入 com.vdurmont:emoji-java
 * https://hutool.cn/docs/#/extra/emoji/Emoji%E5%B7%A5%E5%85%B7-EmojiUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/extra/emoji/EmojiUtil.html
 *
 * @author ljh
 * created on 2020/11/20 17:46
 */
public class EmojiUtilDemo {

    @Test
    public void testEmojiUtil() {
        String emoji = EmojiUtil.toUnicode(":smile:");
        System.out.println(emoji);      // 😄

        if (EmojiUtil.isEmoji(emoji)) {
            String alias = EmojiUtil.toAlias(emoji);
            System.out.println(alias);  // :smile:
        }

        String html = EmojiUtil.toHtml(emoji);
        System.out.println(html);       // &#x1f604;
    }
}
