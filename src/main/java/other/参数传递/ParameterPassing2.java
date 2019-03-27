package other.参数传递;

// Java只存在值传递
public class ParameterPassing2 {
	public static void main(String[] args) {
		int[] a = { 10, 20 };
		System.out.println(a[0]); // 10
		change(a); // 实际传递的是引用的地址值，也就是值传递
		System.out.println(a[0]); // 50
	}

	public static void change(int[] a) {
		a[0] = 50;
	}

}
