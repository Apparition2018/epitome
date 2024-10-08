package knowledge.algorithm.sort;

import l.demo.Demo;

import java.util.Arrays;

/**
 * 排序
 * <pre>
 * 1 数据规模较少：插入排序最佳，选择排序
 * 2 数据规模较大：快速排序最佳，堆排序、归并排序
 * 3 数据基本有序：插入排序、冒泡排序最佳，快速排序
 * 4 比较排序中被认为最好的排序：快速排序
 * 5 要求排序稳定，不要求空间：归并排序
 * 6 堆排序空间复杂度比快速排序低，且最坏时间幅度比快速排序低
 * </pre>
 *
 * @author ljh
 * @see <a href="https://www.cnblogs.com/chengxiao/p/6103002.html">图解排序算法(一)之3种简单排序(选择，冒泡，直接插入) </a>
 * @see <a href="https://www.cnblogs.com/onepixel/articles/7674659.html">十大经典排序算法（动图演示）</a>
 * @see <a href="https://www.cnblogs.com/end/archive/2011/10/22/2220995.html">排序算法性能和使用场景总结</a>
 * @since 2020/10/10 19:23
 */
public class Sort extends Demo {

    private static final int[] arr = new int[]{88, 23, 97, 19, 85, 35, 81, 54, 75, 50};

    /**
     * 交换排序
     */
    private static class ChangeSort {
        public static void main(String[] args) {
            bubbleSort(arr.clone());

            long t1 = System.nanoTime();
            p("快速排序：" + Arrays.toString(quickSort(arr.clone(), 0, arr.length - 1)));
            long t2 = System.nanoTime();
            p("消耗时间：" + (t2 - t1));
        }

        /**
         * 冒泡排序
         * <pre>
         * 从数组的第一个位置开始两两比较 arr[index] 和 arr[index+1]，如果 arr[index] 大于 arr[index+1]，则交换 arr[index] 和 arr[index+1] 的位置
         * 第一轮排序完毕后，最大的数会排到数组最后
         * 第二轮排序完毕后，倒数第二大的数会排到数组倒数第二位
         * ...
         * </pre>
         * <pre>
         * [88, 23, 97, 19, 85, 35, 81, 54, 75, 50]
         *
         * [23, 88, 19, 85, 35, 81, 54, 75, 50, 97]
         * [23, 19, 85, 35, 81, 54, 75, 50, 88, 97]
         * [19, 23, 35, 81, 54, 75, 50, 85, 88, 97]
         * [19, 23, 35, 54, 75, 50, 81, 85, 88, 97]
         * [19, 23, 35, 54, 50, 75, 81, 85, 88, 97]
         * [19, 23, 35, 50, 54, 75, 81, 85, 88, 97] 已排序完毕
         * [19, 23, 35, 50, 54, 75, 81, 85, 88, 97]
         * </pre>
         */
        private static void bubbleSort(int[] arr) {
            long t1 = System.nanoTime();
            int temp;
            for (int i = 0, len = arr.length; i < len - 1; i++) {
                // hasChanged 表示本轮是否有进行交换
                boolean hasChanged = false;
                for (int j = 0; j < len - 1 - i; j++) {
                    if (arr[j] > arr[j + 1]) {
                        temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                        hasChanged = true;
                    }
                }
                // 没有进行交换表示排序已完成
                if (!hasChanged) {
                    break;
                }
            }
            long t2 = System.nanoTime();
            p("冒泡排序：" + Arrays.toString(arr) + "\n消耗时间：" + (t2 - t1));
        }

        /**
         * <a href="https://blog.csdn.net/qq_36528114/article/details/78667034">挖坑法快速排序</a>
         */
        private static int[] quickSort(int[] arr, int l, int r) {
            if (l < r) {
                int index = quick1(arr, l, r);
                quickSort(arr, l, index - 1);
                quickSort(arr, index + 1, r);
            }
            return arr;
        }

        /**
         * 挖坑法
         */
        private static int quick1(int[] arr, int l, int r) {
            int key = arr[l];
            while (l < r) {
                while (l < r && arr[r] >= key) {
                    r--;
                }
                if (l < r) {
                    arr[l++] = arr[r];
                }
                while (l < r && arr[l] <= key) {
                    l++;
                }
                if (l < r) {
                    arr[r--] = arr[l];
                }
            }
            arr[l] = key;
            return l;
        }

        /**
         * 左右指针法
         */
        private static int quick2(int[] arr, int l, int r) {
            int index = l;
            int key = arr[l];
            while (l < r) {
                while (l < r && arr[r] >= key) {
                    --r;
                }
                while (l < r && arr[l] <= key) {
                    ++l;
                }
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
            }
            int temp = arr[index];
            arr[index] = arr[l];
            arr[l] = temp;
            return l;
        }
    }

    /**
     * 选择排序
     */
    private static class SelectionSort {
        public static void main(String[] args) {
            easySelectionSort(arr.clone());
        }

        /**
         * 简单选择排序
         * <pre>
         * 选择一个值 arr[0] 作为标杆，然后循环找到除这个值外最小的值（查找小于标杆的最小值），交换这两个值，
         * 这时最小值就被放到了arr[0]上，然后再将arr[1]作为标杆，从剩下未排序的值中找到最小值，并交换这两个值
         * </pre>
         * <pre>
         * [88, 23, 97, 19, 85, 35, 81, 54, 75, 50]
         *
         * [19, 23, 97, 88, 85, 35, 81, 54, 75, 50]
         * [19, 23, 97, 88, 85, 35, 81, 54, 75, 50]
         * [19, 23, 35, 88, 85, 97, 81, 54, 75, 50]
         * [19, 23, 35, 50, 85, 97, 81, 54, 75, 88]
         * [19, 23, 35, 50, 54, 97, 81, 85, 75, 88]
         * [19, 23, 35, 50, 54, 75, 81, 85, 97, 88]
         * [19, 23, 35, 50, 54, 75, 81, 85, 97, 88]
         * [19, 23, 35, 50, 54, 75, 81, 85, 97, 88]
         * [19, 23, 35, 50, 54, 75, 81, 85, 88, 97]
         * </pre>
         */
        private static void easySelectionSort(int[] arr) {
            long t1 = System.nanoTime();
            for (int i = 0, len = arr.length; i < len - 1; i++) {
                int min = i;
                for (int j = i + 1; j < len; j++) {
                    if (arr[min] > arr[j]) {
                        min = j;
                    }
                }
                if (min != i) {
                    int temp = arr[i];
                    arr[i] = arr[min];
                    arr[min] = temp;
                }
                p("选择排序：" + Arrays.toString(arr));
            }
            long t2 = System.nanoTime();
            p("选择排序：" + Arrays.toString(arr) + "\n消耗时间：" + (t2 - t1));
        }

        /**
         * <a href="https://www.cnblogs.com/guizimo/p/13454676.html#/cnblog/works/article/13454676">堆排序</a>
         */
        private static void heapSort(int[] arr) {
        }
    }

    /**
     * 插入排序
     */
    private static class InsertSort {
        public static void main(String[] args) {
            insertionSort(arr);
        }

        /**
         * 插入排序的思想是数组是部分有序的，然后将无序的部分循环插入到已有序的序列中
         * <pre>
         * [88, 23, 97, 19, 85, 35, 81, 54, 75, 50]
         *
         * [23, 88, 97, 19, 85, 35, 81, 54, 75, 50]
         * [23, 88, 97, 19, 85, 35, 81, 54, 75, 50]
         * [19, 23, 88, 97, 85, 35, 81, 54, 75, 50]
         * [19, 23, 85, 88, 97, 35, 81, 54, 75, 50]
         * [19, 23, 35, 85, 88, 97, 81, 54, 75, 50]
         * [19, 23, 35, 81, 85, 88, 97, 54, 75, 50]
         * [19, 23, 35, 54, 81, 85, 88, 97, 75, 50]
         * [19, 23, 35, 54, 75, 81, 85, 88, 97, 50]
         * [19, 23, 35, 50, 54, 75, 81, 85, 88, 97]
         * </pre>
         */
        private static void insertionSort(int[] arr) {
            long t1 = System.nanoTime();
            for (int i = 1, len = arr.length; i < len; i++) {
                // 被标记的值或者说是当前需要插入的值
                int temp = arr[i];
                int j = i;
                // 如果轮循值大于被标记值则往后移
                while (j > 0 && temp < arr[j - 1]) {
                    arr[j] = arr[j - 1];
                    j--;
                }
                // 将被标记值插入最终移除的空位置
                arr[j] = temp;
            }
            long t2 = System.nanoTime();
            p("插入排序：" + Arrays.toString(arr) + "\n消耗时间：" + (t2 - t1));
        }
    }

    /**
     * <a href="https://www.cnblogs.com/of-fanruice/p/7678801.html">归并排序</a>
     */
    private static class MergeSort {
        public static void main(String[] args) {
            long t1 = System.nanoTime();
            p("归并排序：" + Arrays.toString(mergeSort(arr, 0, arr.length - 1)));
            long t2 = System.nanoTime();
            p("消耗时间：" + (t2 - t1));
        }

        private static int[] mergeSort(int[] arr, int l, int r) {
            int m = (l + r) / 2;
            if (l < r) {
                mergeSort(arr, l, m);
                mergeSort(arr, m + 1, r);
                merge(arr, l, m, r);
            }
            return arr;
        }

        private static void merge(int[] arr, int l, int m, int r) {
            int[] t = new int[r - l + 1];
            int i = l;
            int j = m + 1;
            int k = 0; // t 数组坐标
            // 把较小的数先移到新数组中
            while (i <= m && j <= r) {
                if (arr[i] < arr[j]) {
                    t[k++] = arr[i++];
                } else {
                    t[k++] = arr[j++];
                }
            }
            // 把左边剩余的数移入数组
            while (i <= m) {
                t[k++] = arr[i++];
            }
            // 把右边边剩余的数移入数组
            while (j <= r) {
                t[k++] = arr[j++];
            }
            // 把 t 数组中的数覆盖 arr 数组
            for (int x = 0, len = t.length; x < len; x++) {
                arr[x + l] = t[x];
            }
        }
    }
}
