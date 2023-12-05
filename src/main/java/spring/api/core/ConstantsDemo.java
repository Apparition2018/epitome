package spring.api.core;

import l.demo.Demo;
import org.springframework.core.Constants;

/**
 * Constants
 * <p>解析指定类的 public static final 属性
 *
 * @author ljh
 * @since 2021/9/9 11:48
 */
public class ConstantsDemo {

    public static final int MAX = 999;
    public static final int MIN = 0;
    public static final String NAME = Demo.MY_NAME;

    public static void main(String[] args) {
        // 请改用枚举、映射或类似的自定义解决方案
        Constants constants = new Constants(ConstantsDemo.class);
        System.out.println(constants.getSize());                // 3
        System.out.println(constants.asNumber("MAX"));          // 999
        System.out.println(constants.asString("NAME"));         // ljh

        // 获取名称，前缀匹配
        System.out.println(constants.getNames("M"));            // [MIN, MAX]
        // 获取名称，后缀匹配
        System.out.println(constants.getNamesForSuffix("E"));   // [NAME]

        // 获取值，前缀匹配
        System.out.println(constants.getValues("M"));           // [0, 999]
        // 获取值，后缀匹配
        System.out.println(constants.getValuesForSuffix("E"));  // [ljh]
    }
}
