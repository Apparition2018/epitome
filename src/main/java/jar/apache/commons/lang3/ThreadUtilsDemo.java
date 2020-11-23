package jar.apache.commons.lang3;

import l.demo.Demo;
import org.apache.commons.lang3.ThreadUtils;
import org.junit.Test;

/**
 * ThreadUtils
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/ThreadUtils.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class ThreadUtilsDemo extends Demo {

    @Test
    public void testThreadUtils() {
        p(ThreadUtils.getAllThreads());         // [Thread[Reference Handler,10,system], Thread[Finalizer,8,system], Thread[Signal Dispatcher,9,system], Thread[Attach Listener,5,system], Thread[main,5,main], Thread[Monitor Ctrl-Break,5,main]]
        p(ThreadUtils.getSystemThreadGroup());  // java.lang.ThreadGroup[name=system,maxpri=10]
        p(ThreadUtils.getAllThreadGroups());    // [java.lang.ThreadGroup[name=main,maxpri=10]]
    }
}
