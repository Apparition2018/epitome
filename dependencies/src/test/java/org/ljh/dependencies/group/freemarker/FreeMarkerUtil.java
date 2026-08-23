package org.ljh.dependencies.group.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * FreeMarkerUtil
 * <p>步骤：
 * <pre>
 * 1 创建 Configuration 实例
 *   Configuration cfg = new Configuration(Configuration.VERSION_2_3_34);
 *   设置 Freemarker 的模板文件位置:
 *      1.1 cfg.setClassForTemplateLoading(Class resourceLoaderClass, String basePackagePath);
 *      1.2 cfg.setDirectoryForTemplateLoading(File dir);
 *      1.3 cfg.setServletContextForTemplateLoading(Object servletContextM, String path);
 * 2 生成 Template 实例，并指定使用的模板文件
 *   Template template = cfg.getTemplate(String fName);
 * 3 调用 Templat e实例的 process 完成数据模型和模板的合并
 *   template.process(Object dataModel, Writer out);
 * </pre>
 *
 * @author ljh
 * @see <a href="http://blog.csdn.net/zhanggnol/article/details/6324090">FreeMarker 使用详解</a>
 * @see <a href="http://blog.csdn.net/u012759397/article/details/54092092">FreeMarker .ftl 换成 .htm 或 .jsp</a>
 * @since 2019/8/8 19:39
 */
public class FreeMarkerUtil {

    private static final Configuration cfg = new Configuration(Configuration.VERSION_2_3_34);
    private static FreeMarkerUtil util;

    private FreeMarkerUtil() {

    }

    public static FreeMarkerUtil getInstance(String pName) {
        if (null == util) {
            try {
                // 模板与工具类同目录时，直接从源码目录加载，避免依赖 classpath 资源拷贝
                cfg.setDirectoryForTemplateLoading(new File(pName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
    public void printOut(Map<String, Object> params, String fName) {
        try {
            getTemplate(fName).process(params, new PrintWriter(System.out));
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
    public void printFile(Map<String, Object> params, String fName, String outPath) {
        try {
            getTemplate(fName).process(params, new FileWriter(outPath));
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
