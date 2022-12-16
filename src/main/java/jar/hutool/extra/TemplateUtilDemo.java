package jar.hutool.extra;

import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import org.junit.jupiter.api.Test;

/**
 * TemplateUtil     模板引擎封装
 * 需要引入 org.freemarker:freemarker
 * https://hutool.cn/docs/#/extra/%E6%A8%A1%E6%9D%BF%E5%BC%95%E6%93%8E/%E6%A8%A1%E6%9D%BF%E5%BC%95%E6%93%8E%E5%B0%81%E8%A3%85-TemplateUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/extra/template/TemplateUtil.html
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/extra/template/TemplateEngine.html
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/extra/template/Template.html
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
