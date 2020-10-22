package knowledge.api.lang.enum_;

import l.demo.Demo;
import org.junit.Test;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * Enum
 * https://jdk6.net/lang/Enum.html
 * <p>
 * 优点：
 * 1.枚举有更多灵活的用法
 * 2.有效的提高代码的整洁性、可读性
 * 3.限制非法值的传入
 * <p>
 * switch:
 * 1) jdk1.5-: byte, short, char, int
 * 2) jdk1.5:  Byte, Short, Character, Integer, enum
 * 3) jdk1.7:  String
 * <p>
 * int	                            ordinal()               返回枚举常量的序数（它在枚举声明中的位置，其中初始常量序数为零）
 * String	                        name()                  返回此枚举常量的名称，在其枚举声明中对其进行声明
 * Class<E>	                        getDeclaringClass()     返回与此枚举常量的枚举类型相对应的 Class 对象
 * static <T extends Enum<T>> T	    valueOf(Class<T> enumType, String name) 返回带指定名称的指定枚举类型的枚举常量
 */
public class EnumDemo extends Demo {

    // 定义枚举类型
    enum Light {
        // 利用构造函数传参
        RED(1), YELLOW(2), GREEN(3);

        // 定义私有变量
        private int nCode;

        // 构造函数默认为私有
        Light(int _nCode) {
            this.nCode = _nCode;
        }

        @Override
        public String toString() {
            return String.valueOf(this.nCode);
        }
    }

    /**
     * 遍历
     */
    @Test
    public void traversal() {
        // Java 编译器会自动在 enum 中插入 values() 方法
        // https://blog.csdn.net/u013469218/article/details/66476182
        Light[] allLight = Light.values();
        for (Light aLight : allLight) {
            p(" 当前灯 name ： " + aLight.name());
            p(" 当前灯 ordinal ： " + aLight.ordinal());
            p(" 当前灯 ： " + aLight);
        }
    }

    /**
     * EnumMap
     * EnumMap 跟 HashMap 的使用差不多，只不过 key 是枚举类型
     */
    @Test
    public void enumMap() {
        // 1. 演示定义 EnumMap 对象， EnumMap 对象的构造函数需要参数传入 , 默认是 key 的类的类型
        EnumMap<Light, String> currEnumMap = new EnumMap<>(Light.class);
        currEnumMap.put(Light.RED, " 红灯 ");
        currEnumMap.put(Light.YELLOW, " 黄灯 ");
        currEnumMap.put(Light.GREEN, " 绿灯 ");

        // 2. 遍历对象
        for (Light aLight : Light.values()) {
            p("[key=" + aLight.name() + ",value=" + currEnumMap.get(aLight) + "]");
        }
    }

    /**
     * EnumSet
     * EnumSet 是一个抽象类，获取一个类型的枚举类型内容可以使用 allOf 方法
     */
    @Test
    public void enumSet() {
        EnumSet<Light> currEnumSet = EnumSet.allOf(Light.class);
        for (Light aLightSetElement : currEnumSet) {
            p(" 当前 EnumSet 中数据为： " + aLightSetElement);
        }

    }
}