package jar.hutool.extra;

import cn.hutool.extra.emoji.EmojiUtil;

/**
 * <a href="https://doc.hutool.cn/pages/EmojiUtil/">EmojiUtil</a>
 * <p>需要引入 com.vdurmont:emoji-java
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/extra/emoji/EmojiUtil.html">EmojiUtil api</a>
 *
 * @author ljh
 * @since 2020/11/20 17:46
 */
public class EmojiUtilDemo {

    public static void main(String[] args) {
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
