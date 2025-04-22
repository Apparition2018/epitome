package jar.hutool.script;

import cn.hutool.script.ScriptUtil;

import javax.script.CompiledScript;
import javax.script.ScriptException;

/**
 * <a href="https://doc.hutool.cn/pages/ScriptUtil/">ScriptUtil</a>
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/script/ScriptUtil.html">ScriptUtil api</a>
 *
 * @author ljh
 * @since 2020/11/21 17:22
 */
public class ScriptUtilDemo {

    public static void main(String[] args) throws ScriptException {
        String script = "var a = 0;" +
                "var str = 'if (a) { 1 + 1; } else { 1 + 2; }';" +
                "var b = eval(str);" +
                "print(b);";
        // 方法一
        ScriptUtil.eval(script);

        // 方法二
        CompiledScript compiledScript = ScriptUtil.compile(script);
        compiledScript.eval();
    }
}
