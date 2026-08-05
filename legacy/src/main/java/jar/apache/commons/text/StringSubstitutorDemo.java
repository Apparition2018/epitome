package jar.apache.commons.text;

import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

import static l.demo.Demo.ae;

/**
 * <a href="https://commons.apache.org/proper/commons-text/javadocs/api-release/org/apache/commons/text/StringSubstitutor.html">StringSubstitutor</a>
 * <p>用值替换字符串中的变量</p>
 *
 * @author ljh
 * @since 2025/8/11 10:49
 */
public class StringSubstitutorDemo {

    public static void main(String[] args) {
        // 准备替换数据
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("name", "Alice");
        valuesMap.put("age", "25");
        valuesMap.put("site", "Alibaba Cloud");

        // 执行替换
        String template = "Hello, ${name}! You are ${age} years old. Welcome to ${site}.";
        String result = StringSubstitutor.replace(template, valuesMap);
        ae(result, "Hello, Alice! You are 25 years old. Welcome to Alibaba Cloud.");

        // 自定义前缀、后缀，替换
        template = "Hello, #name#! You are #age# years old. Welcome to #site#.";
        result = StringSubstitutor.replace(template, valuesMap, "#", "#");
        ae(result, "Hello, Alice! You are 25 years old. Welcome to Alibaba Cloud.");
    }
}
