package jar.hutool.setting;

import cn.hutool.setting.Setting;
import l.demo.Demo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * <a href="https://doc.hutool.cn/pages/setting/example/">Setting</a>   设置文件
 * <pre>
 * 1 兼容 .properties
 * 2 编码支持
 * 3 变量支持 ${key}
 * 4 分组支持
 * </pre>
 * <a href="https://plus.hutool.cn/apidocs/cn/hutool/setting/Setting.html">Setting api</a>
 *
 * @author ljh
 * @since 2020/11/20 1:03
 */
public class SettingDemo extends Demo {

    public static void main(String[] args) {
        Setting setting = new Setting(new File(HU_DEMO_DIR_PATH + "example.setting"), StandardCharsets.UTF_8, true);
        // 在配置文件变更时自动加载
        setting.autoLoad(true);


        // getStr(String key, [[String group,] [String defaultValue]])
        p(setting.getStr("user", "demo", MY_NAME));
        // 带有日志提示的 get，如果没有定义指定的 key，则打印 debug 日志
        p(setting.getByGroupWithLog("user2", "demo"));
        // 获得所有分组名
        p(setting.getGroups());
        // 获取 group 分组下所有配置键值对
        Setting demoSetting = setting.getSetting("demo");

        // Setting → Bean
        p(demoSetting.toBean(new SettingBean()));

        // 设置值
        setting.setByGroup("max-active", "demo", "50");
        // 持久化
        setting.store(HU_DEMO_DIR_ABSOLUTE_PATH + "example_copy.setting");
    }

    @ToString
    @Getter
    @Setter
    private static class SettingBean {
        private String driver;
        private String url;
        private String user;
        private String pass;
    }
}
