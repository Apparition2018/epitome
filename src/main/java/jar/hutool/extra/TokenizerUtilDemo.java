package jar.hutool.extra;

import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import cn.hutool.extra.tokenizer.Word;
import org.apache.commons.lang3.StringUtils;

/**
 * <a href="https://hutool.cn/docs/#/extra/中文分词/中文分词封装-TokenizerUtil">TokenizerUtil</a>     中文分词封装
 * <p>需要引入 org.apache.lucene:lucene-analyzers-smartcn
 * <pre>
 * <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/extra/tokenizer/TokenizerUtil.html">TokenizerUtil api</a>
 * <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/extra/tokenizer/TokenizerEngine.html">TokenizerEngine api</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/11/21 14:54
 */
public class TokenizerUtilDemo {

    public static void main(String[] args) {
        // 根据用户引入的分词引擎，自动创建对应的分词引擎对象
        TokenizerEngine tokenizerEngine = TokenizerUtil.createEngine();
        Result parse = tokenizerEngine.parse("我来自中国，是中国人");

        for (Word word : parse) {
            System.out.print(word.getText() + StringUtils.SPACE); // 我 来自 中国 是 中国 人
        }
    }
}
