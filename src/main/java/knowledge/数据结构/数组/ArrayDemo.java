package knowledge.数据结构.数组;

import l.demo.Demo;
import org.junit.Test;

import java.lang.reflect.Array;

/**
 * Array
 * 
 * Array 与 List 不同之处：
 * 1.List 不可以存基本数据类型
 * 2.Array 容量固定不可改变
 * 3.Array 效率更高
 *
 * @author ljh
 * created on 2020/10/16 16:23
 */
public class ArrayDemo extends Demo {

    /**
     * 创建数组
     */
    @Test
    public void createArray() {
        // 方式一
        int[] arr1 = new int[3];

        // 方式二
        int[] arr2 = {0, 0, 0};

        // 方式三
        int[] arr3 = new int[]{0, 0, 0};

        // 方式四
        // static Object	newInstance(Class<?> componentType, int... dimensions)
        // 创建一个具有指定的组件类型和维度的新数组
        // static Object	newInstance(Class<?> componentType, int length)
        // 创建一个具有指定的组件类型和长度的新数组
        int[][] intArrArr = (int[][]) Array.newInstance(int.class, 2, 3);
        for (int[] intArr : intArrArr) {
            p(intArr);
        }
    }

    /**
     * 多维数组
     */
    @Test
    public void multidimensionalArray() {
        // nt[][] arr = new int[2][3];
        int[][] arr = {{0, 1, 2}, {0, 1, 2}};

        for (int[] ints : arr) {
            p(ints);
            // [0, 1, 2]
            // [0, 1, 2]
        }
    }

    /**
     * 数组扩容
     * System.arraycopy()
     */
    @Test
    public void arraycopy() {
        int[] arr1 = new int[]{1, 2, 3};
        int[] arr2 = new int[arr1.length + 1];

        System.arraycopy(arr1, 0, arr2, 0, 3);
        p(arr2);
    }

}
