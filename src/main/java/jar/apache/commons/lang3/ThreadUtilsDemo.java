package jar.apache.commons.lang3;

import org.apache.commons.lang3.ThreadUtils;

/**
 * <a href="http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/ThreadUtils.html">ThreadUtils</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class ThreadUtilsDemo {

    public static void main(String[] args) {
        System.out.println(ThreadUtils.getAllThreads());        // [Thread[Reference Handler,10,system], Thread[Finalizer,8,system], Thread[Signal Dispatcher,9,system], Thread[Attach Listener,5,system], Thread[main,5,main], Thread[Monitor Ctrl-Break,5,main]]
        System.out.println(ThreadUtils.getSystemThreadGroup()); // java.lang.ThreadGroup[name=system,maxpri=10]
        System.out.println(ThreadUtils.getAllThreadGroups());   // [java.lang.ThreadGroup[name=main,maxpri=10]]
    }
}
