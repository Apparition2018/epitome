package jar.hutool.io;

import cn.hutool.core.io.watch.SimpleWatcher;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.watchers.DelayWatcher;
import cn.hutool.core.lang.Console;
import l.demo.Demo;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * WatchMonitor 文件监听
 * WatchMonitor 主要针对 WatchService 做了封装
 * 1.支持多级目录的监听（WatchService 只支持一级目录），可自定义监听目录深度
 * 2.延迟合并触发支持（文件变动时可能触发多次 modify，支持在某个时间范围内的多次修改事件合并为一个修改事件）
 * 3.简洁易懂的 API 方法，一个方法即可搞定监听，无需理解复杂的监听注册机制
 * 4.多观察者实现，可以根据业务实现多个 Watcher 来响应同一个事件（通过 WatcherChain）
 * https://hutool.cn/docs/#/core/IO/%E6%96%87%E4%BB%B6%E7%9B%91%E5%90%AC-WatchMonitor
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/io/watch/WatchMonitor.html
 *
 * @author ljh
 * @since 2020/10/30 15:48
 */
public class WatchMonitorDemo extends Demo {

    public static void main(String[] args) {
        // crete(File/Path/path/URI/URL[, maxDepth], WatchEvent.Kind<?>...)     创建并初始化监听
        // createAll(File/Path/path/URI/URL, Watcher)                           创建并初始化监听，监听所有事件
        WatchMonitor.createAll(DEMO_PATH, 
                // DelayWatcher: 此类通过维护一个 Set 将短时间内相同文件多次 modify 的事件合并处理触发
                new DelayWatcher(new SimpleWatcher() {

            // 重写需要监听的事件的方法
            @Override
            public void onModify(WatchEvent<?> event, Path currentPath) {
                Console.log("EVENT modify");
            }
        }, 500)).start();
    }
}
