package other.设计模式.singleton;

/**
 * 单例：双重校验锁
 * @author Arsenal
 *
 */
public class Singleton {

	private volatile static Singleton singleton; // volatile, 禁止指令重排序优化
	private Singleton() {}
	public static Singleton getSingleton() {
		if (singleton == null) {
			synchronized (Singleton.class) {
				if (singleton == null) {
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}


	/** 静态内部类 **/
//	private static class Holder {
//		private static final Singleton INSTANCE = new Singleton();
//	}
//	public static Singleton getInstance() {
//		return Holder.INSTANCE;
//	}
}

