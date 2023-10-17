package jar.jakarta.script;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import l.demo.Demo;
import l.demo.Person;
import org.junit.jupiter.api.Test;

import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Script
 * <p>脚本语言的三大特征：
 * <pre>
 * 1 灵活：脚本语言一般都是动态类型，可以不用声明变量类型而直接使用，可以再运行期改变类型。
 * 2 便捷：脚本语言是一种解释性语言，不需要编译成二进制代码，也不需要像 Java 一样生成字节码。它的执行时依靠解释器解释的，因此在运行期间变更代码很容易，而且不用停止应用。
 * 3 简单：只能说部分脚本语言简单，比如 Groovy，对于程序员来说，没有多大的门槛。
 * </pre>
 * Nashorn JS 引擎：JDK8 引入，JDK11 移除
 * <p>参考：<a href="https://blog.didispace.com/books/java8-tutorial/ch3.html">JDK8 Nashorn 教程</a>
 *
 * @author ljh
 * @since 2021/8/10 15:52
 */
public class ScriptDemo extends Demo {

    private final ScriptEngine engine = new ScriptEngineManager().getEngineByName("Nashorn");

    /** 脚本引擎工厂 */
    @Test
    public void scriptEngineFactory() {
        ScriptEngineManager manager = new ScriptEngineManager();
        List<ScriptEngineFactory> list = manager.getEngineFactories();
        for (ScriptEngineFactory f : list) {
            p(f.getEngineName());       // Oracle Nashorn
            p(f.getEngineVersion());    // 1.8.0_291
            p(f.getLanguageName());     // ECMAScript
            p(f.getLanguageVersion());  // ECMA - 262 Edition 5.1
            p(f.getNames());            // [nashorn, Nashorn, js, JS, JavaScript, javascript, ECMAScript, ecmascript]
            p(f.getMimeTypes());        // [application/javascript, application/ecmascript, text/javascript, text/ecmascript]
        }
    }

    /** 绑定参数和方法参数 */
    @Test
    public void bindingsAndFunctionArgs() throws FileNotFoundException, ScriptException, NoSuchMethodException {
        Bindings bindings = engine.createBindings();
        bindings.put("factor", 9);
        // ScriptContext.ENGINE_SCOPE: 单个引擎生命周期可见
        // ScriptContext.GLOBAL_SCOPE: 相同 ScriptEngineFactory 创建的所有引擎可见
        engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);

        int var1 = 1;
        int var2 = 99;

        // Object	    eval(Reader reader)                         除了脚本的源是以 Reader 形式提供的外，与 eval(String) 相同
        engine.eval(new FileReader(JAVA_PATH + "jar/jakarta/script/model.js"));
        if (engine instanceof Invocable invocable) {
            // Object	invokeFunction(String name, Object... args) 用于调用脚本中定义的顶层程序和函数
            Double result = (Double) invocable.invokeFunction("formula", var1, var2);
            p("运算结果是：" + result.intValue());
        }
    }

    /** 获取对象列表 */
    @Test
    public void getList() throws FileNotFoundException, ScriptException, NoSuchMethodException, JsonProcessingException {
        engine.eval(new FileReader(DEMO_DIR_PATH + "person.js"));
        if (engine instanceof Invocable invocable) {
            String json = jsonMapper.writeValueAsString(invocable.invokeFunction("listPerson"));
            List<Person> personList = new ArrayList<>(jsonMapper.readValue(json, new TypeReference<Map<Integer, Person>>() {
            }).values());
            p(personList);
        }
    }
}
