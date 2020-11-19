package jar.hutool.swing;

import cn.hutool.core.swing.clipboard.ClipboardUtil;
import l.demo.Demo;
import org.junit.Test;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

/**
 * ClipboardUtil    剪贴板工具
 * https://hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/%E5%89%AA%E8%B4%B4%E6%9D%BF%E5%B7%A5%E5%85%B7-ClipboardUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/swing/clipboard/ClipboardUtil.html
 *
 * @author ljh
 * created on 2020/11/19 12:00
 */
public class ClipboardUtilDemo extends Demo {
    
    @Test
    public void testClipboardUtil() {
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
