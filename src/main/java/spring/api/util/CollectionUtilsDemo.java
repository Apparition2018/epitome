package spring.api.util;

import l.demo.Demo;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * CollectionUtils
 *
 * @author ljh
 * @since 2021/9/9 11:56
 */
public class CollectionUtilsDemo extends Demo {

    public static void main(String[] args) throws IOException {
        p("判断是否为空：" + CollectionUtils.isEmpty(list));
        p("判断一个集合是否包含另一个集合中的任意元素：" + CollectionUtils.containsAny(list, subList));
        p("判断一个集合中的所有元素是否都是唯一的：" + CollectionUtils.hasUniqueObject(list));

        p("查找给定公共元素类型：" + CollectionUtils.findCommonElementType(list));
        p("检索最后一个元素：" + CollectionUtils.lastElement(list));
        p("查找指定类型的第一个元素：" + CollectionUtils.findValueOfType(list, String.class));

        p("将数组转换为列表：" + CollectionUtils.arrayToList(arr));

        CollectionUtils.mergeArrayIntoCollection(arr, list);
        p("将数组合并到集合：" + list);

        Map<String, String> map = CollectionUtils.newHashMap(16);
        Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource(JDBC_PROPS_FILENAME));
        CollectionUtils.mergePropertiesIntoMap(properties, map);
        p("将 Properties 合并到 Map：" + map);

        Map<Integer, String> merged = CollectionUtils.compositeMap(Demo.map, Demo.map2);
        p("组合两个 Map：" + merged);
    }
}
