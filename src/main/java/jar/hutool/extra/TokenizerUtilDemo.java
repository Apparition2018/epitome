package jar.hutool.extra;

import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import cn.hutool.extra.tokenizer.Word;
import org.junit.Test;

/**
 * TokenizerUtil    中文分词封装
 * 需要引入 org.apache.lucene:lucene-analyzers-smartcn
 * https://hutool.cn/docs/#/extra/%E4%B8%AD%E6%96%87%E5%88%86%E8%AF%8D/%E4%B8%AD%E6%96%87%E5%88%86%E8%AF%8D%E5%B0%81%E8%A3%85-TokenizerUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/extra/tokenizer/TokenizerUtil.html
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/extra/tokenizer/TokenizerEngine.html
 *
 * @author Arsenal
 * created on 2020/11/21 14:54
 */
public class TokenizerUtilDemo {

    @Test
    public void testTokenizerUtil() {
        // 根据用户引入的分词引擎，自动创建对应的分词引擎对象
        TokenizerEngine tokenizerEngine = TokenizerUtil.createEngine();
        Result parse = tokenizerEngine.parse("我来自中国，是中国人");

        for (Word word : parse) {
            System.out.print(word.getText() + " "); // 我 来自 中国 是 中国 人 
        }
    }
}
