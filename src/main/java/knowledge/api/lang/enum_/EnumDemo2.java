package knowledge.api.lang.enum_;

import org.junit.Test;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * Enum
 * String	name()          返回此枚举常量的名称，在其枚举声明中对其进行声明
 * int	    ordinal()       返回枚举常量的序数（它在枚举声明中的位置，其中初始常量序数为零）
 */
public class EnumDemo2 {

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
     * 演示枚举类型的遍历
     */
    @Test
    public void traversal() {
        // Java 编译器会自动在 enum 中插入 values() 方法
        // https://blog.csdn.net/u013469218/article/details/66476182
        Light[] allLight = Light.values();
        for (Light aLight : allLight) {
            System.out.println(" 当前灯 name ： " + aLight.name());
            System.out.println(" 当前灯 ordinal ： " + aLight.ordinal());
            System.out.println(" 当前灯 ： " + aLight);
        }
    }

    /**
     * EnumMap
     * <p>
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
            System.out.println("[key=" + aLight.name() + ",value=" + currEnumMap.get(aLight) + "]");
        }
    }

    /**
     * EnumSet
     * <p>
     * EnumSet 是一个抽象类，获取一个类型的枚举类型内容可以使用 allOf 方法
     */
    @Test
    public void enumSet() {
        EnumSet<Light> currEnumSet = EnumSet.allOf(Light.class);
        for (Light aLightSetElement : currEnumSet) {
            System.out.println(" 当前 EnumSet 中数据为： " + aLightSetElement);
        }

    }
}