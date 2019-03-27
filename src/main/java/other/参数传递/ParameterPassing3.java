package other.参数传递;

public class ParameterPassing3 {
	private int a;

	public int get() {
		return a;
	}

	public void set(int d) {
		a = d;
	}

	public static void modify(ParameterPassing3 item, int desc) {
		// item = new Item();
		item.set(desc);
	}

	public static void main(String[] args) {
		ParameterPassing3 it = new ParameterPassing3();
		it.set(2);

		modify(it, 4);

		System.out.println(it.get()); // 4

	}

}
