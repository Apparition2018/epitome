package jar.hutool.swing;

import cn.hutool.core.swing.clipboard.ClipboardUtil;
import l.demo.Demo;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

/**
 * <a href="https://doc.hutool.cn/pages/ClipboardUtil/">ClipboardUtil</a>   剪贴板工具
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/swing/clipboard/ClipboardUtil.html">ClipboardUtil api</a>
 *
 * @author ljh
 * @since 2020/11/19 12:00
 */
public class ClipboardUtilDemo extends Demo {

    public static void main(String[] args) {
        // 获取系统剪贴板
        Clipboard clipboard = ClipboardUtil.getClipboard();

        // 设置字符串文本到剪贴板
        ClipboardUtil.setStr(HELLO_WORLD);
        // 获取剪贴板文本
        p(ClipboardUtil.getStr());

        Transferable transferable = new StringSelection(HELLO_WORLD);
        // 设置内容到剪贴板
        ClipboardUtil.set(transferable);
        // 获取剪贴板内容
        p(ClipboardUtil.get(DataFlavor.stringFlavor));
    }
}
