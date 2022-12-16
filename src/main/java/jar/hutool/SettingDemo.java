package jar.hutool;

import cn.hutool.setting.Setting;
import l.demo.Demo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * Setting      设置文件
 * 1.兼容 .properties
 * 2.编码支持
 * 3.变量支持 ${key}
 * 4.分组支持
 * https://hutool.cn/docs/#/setting/%E8%AE%BE%E7%BD%AE%E6%96%87%E4%BB%B6-Setting
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/setting/Setting.html
 *
 * @author ljh
 * @since 2020/11/20 1:03
 */
public class SettingDemo extends Demo {

    @Test
    public void testSetting() {
        Setting setting = new Setting(new File(HU_DEMO_PATH + "example.setting"), StandardCharsets.UTF_8, true);
        // 在配置文件变更时自动加载
        setting.autoLoad(true);


        // getStr(String key, [[String group,] [String defaultValue]])
        p(setting.getStr("user", "demo", "ljh"));
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
        setting.store(HU_DEMO_ABSOLUTE_PATH + "example_copy.setting");
    }

    @ToString
    @Getter
    @Setter
    static class SettingBean {
        private String driver;
        private String url;
        private String user;
        private String pass;
    }
}
