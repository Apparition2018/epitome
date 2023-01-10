package jar.hutool.extra;

import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://hutool.cn/docs/#/extra/模板引擎/模板引擎封装-TemplateUtil">TemplateUtil</a>   模板引擎封装
 * <p>需要引入 org.freemarker:freemarker
 * <pre>
 * <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/extra/template/TemplateUtil.html">TemplateUtil api</a>
 * <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/extra/template/TemplateEngine.html">TemplateEngine api</a>
 * <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/extra/template/Template.html">Template api</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/11/20 17:11
 */
public class TemplateUtilDemo {

    @Test
    public void testTemplateUtil() {
        // 根据用户引入的模板引擎，自动创建对应的模板引擎对象
        TemplateEngine templateEngine = TemplateUtil.createEngine(new TemplateConfig());

        // 获得模板
        Template template = templateEngine.getTemplate("Hello ${name}");

        // 将模板与绑定参数融合后返回为字符串
        System.out.println(template.render(MapUtil.of("name", "World!")));
    }
}
