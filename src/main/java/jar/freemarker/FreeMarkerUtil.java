package jar.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;

/**
 * FreeMarkerUtil
 * <p>步骤：
 * <pre>
 * 1 创建Configuration实例
 *   Configuration cfg = new FreeMarkerConfigurer().getConfiguration();
 *   设置Freemarker的模板文件位置:
 *      1.1 cfg.setClassForTemplateLoading(Class resourceLoaderClass, String basePackagePath);
 *      1.2 cfg.setDirectoryForTemplateLoading(File dir);
 *      1.3 cfg.setServletContextForTemplateLoading(Object servletContextM, String path);
 * 2 生成Template实例，并指定使用的模板文件
 *   Template template = cfg.getTemplate(String fName);
 * 3 调用Template实例的process完成数据模型和模板的合并
 *   template.process(Object dataModel, Writer out);
 * </pre>
 * 参考：
 * <pre>
 * <a href="http://blog.csdn.net/zhanggnol/article/details/6324090">FreeMarker 使用详解</a>
 * <a href="http://blog.csdn.net/u012759397/article/details/54092092">FreeMarker .ftl 换成 .htm 或 .jsp</a>
 * </pre>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class FreeMarkerUtil {
    private static FreeMarkerConfigurer fmcf;
    private static FreeMarkerUtil util;
    private static Configuration cfg;

    private FreeMarkerUtil() {

    }

    public static FreeMarkerUtil getInstance(String pName) {
        if (null == util) {
            cfg = fmcf.getConfiguration(); // 依赖 spring-context-support
            cfg.setClassForTemplateLoading(FreeMarkerUtil.class, pName);
            util = new FreeMarkerUtil();
        }
        return util;
    }

    private Template getTemplate(String fName) {
        try {
            return cfg.getTemplate(fName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过标准输出流输出模板的结果
     *
     * @param params 数据对象
     * @param fName  模板文件
     */
    public void sPrint(Map<String, Object> params, String fName) {
        try {
            Objects.requireNonNull(getTemplate(fName)).process(params, new PrintWriter(System.out));
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 基于文件的输出
     *
     * @param params 数据对象
     * @param fName  模板文件
     */
    public void fPrint(Map<String, Object> params, String fName, String outPath) {
        try {
            Objects.requireNonNull(getTemplate(fName)).process(params, new FileWriter(outPath));
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
