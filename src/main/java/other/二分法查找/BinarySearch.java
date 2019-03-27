package other.二分法查找;

/**
 * 当数据量很大适宜采用该方法
 * 采用二分法查找时，数据需是有序不重复的
 */
public class BinarySearch {
	private static int idx = 1;
	private static void find(int leftIndex, int rightIndex, int val, int[] arr) {
		int midIndex = (rightIndex + leftIndex) / 2;
		int midVal = arr[midIndex];// 找到中间的数
		if (rightIndex >= leftIndex) {
			// 如果要找的数比midVal大
			if (midVal > val) {
				idx++;
				find(leftIndex, midIndex - 1, val, arr); // 在arr左边数中找
			} else if (midVal < val) {
				idx++;
				find(midIndex + 1, rightIndex, val, arr); // 在arr的右边去查找
			} else if (midVal == val) {
				System.out.println("共查找了" + idx + "次" + "，在下标" + midIndex + "找到");
			}
		} else {
			System.out.println("没有该数!");
		}
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 100, 300, 500, 800, 1000, 2000, 3000 };
		find(0, arr.length - 1, 1000, arr);
	}
}