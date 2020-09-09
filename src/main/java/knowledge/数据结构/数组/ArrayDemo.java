package knowledge.数据结构.数组;

import l.demo.Demo;
import org.junit.Test;

import java.lang.reflect.Array;

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
    }

    /**
     * 多维数组
     */
    @Test
    public void multidimensionalArray() {
        int[][] arr = new int[2][3];

        for (int i = 0, len1 = arr.length; i < len1; i++) {
            for (int j = 0, len2 = arr[i].length; j < len2; j++) {
                arr[i][j] = j;
            }
        }

        for (int[] ints : arr) {
            p(ints);
            // [0, 1, 2]
            // [0, 1, 2]
        }

    }

    /**
     * 数组扩容
     * <p>
     * System.arraycopy()
     */
    @Test
    public void arraycopy() {
        int[] arr1 = new int[]{1, 2, 3};
        int[] arr2 = new int[arr1.length + 1];

        System.arraycopy(arr1, 0, arr2, 0, 3);
        p(arr2);
    }


    /**
     * static Object	newInstance(Class<?> componentType, int... dimensions)
     * 创建一个具有指定的组件类型和维度的新数组
     * static Object	newInstance(Class<?> componentType, int length)
     * 创建一个具有指定的组件类型和长度的新数组
     */
    @Test
    public void newInstance() {
        int[][] ints = (int[][]) Array.newInstance(int.class, 2, 3);
        p(ints);
    }

}
