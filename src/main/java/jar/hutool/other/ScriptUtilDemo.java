package jar.hutool.other;

import cn.hutool.script.ScriptUtil;
import org.junit.jupiter.api.Test;

import javax.script.CompiledScript;
import javax.script.ScriptException;

/**
 * ScriptUtil
 * https://hutool.cn/docs/#/script/Script%E5%B7%A5%E5%85%B7-ScriptUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/script/ScriptUtil.html
 *
 * @author Arsenal
 * created on 2020/11/21 17:22
 */
public class ScriptUtilDemo {

    @Test
    public void testScriptUtil() throws ScriptException {
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
