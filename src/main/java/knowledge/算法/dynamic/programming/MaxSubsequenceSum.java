package knowledge.算法.dynamic.programming;

/**
 * 最大子段和
 * <p>
 * 给定n个整数（可能为负数）组成的序列a[1],a[2],a[3],…,a[n],求该序列如a[i]+a[i+1]+…+a[j]的子段和的最大值。
 * 当所给的整数均为负数时定义子段和为0，依此定义，所求的最优值为： Max{0,a[i]+a[i+1]+…+a[j]},1<=i<=j<=n。
 * 例如，当（a[1],a[2],a[3],a[4],a[5],a[6]）=(-2,11,-4,13,-5,-2)时，最大子段和为20。
 * <p>
 * https://blog.csdn.net/m0_37889238/article/details/79558467
 */
public class MaxSubsequenceSum {

    /**
     * 使用动态规划算法
     * 动态规划算法的基本思想就是讲待求解的问题分成若干个小问题，先求解子问题的解，然后由这些子问题的解得到原问题的解。
     * <p>
     * 我们定义b[j]，它的含义是以数组元素a[j]为尾元素的最大子段和。
     * 如果b[j-1]>0，那么显然b[j]=b[j-1]+a[j]
     * 如果b[j-1]<=0，那么显然b[j]=a[j]，因为加上前面的负数或0肯定不能变得更大。
     * <p>
     * 下标   0   1   2   3   4   5
     * 数组a  -2  11  -4  13  -5  -2
     * 数组b  -2  11  7   20  15  13
     */
    public static void main(String[] args) {
        int[] arr = {-2, 11, -4, 13, -5, -2};
        System.out.println(maxSubsequenceSum(arr));
    }

    private static int maxSubsequenceSum(int[] arr) {
        int sum = 0;
        int b = 0;
        for (int a : arr) {
            b = b > 0 ? b + a : a;
            if (b > sum) {
                sum = b;
            }
        }

        return sum;
    }
}
