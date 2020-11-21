package jar.hutool.other;

import cn.hutool.dfa.WordTree;
import l.demo.Demo;
import org.junit.Test;

/**
 * DFA (Deterministic Finite Automaton)     确定有穷自动机
 * https://hutool.cn/docs/#/dfa/%E6%A6%82%E8%BF%B0
 * https://hutool.cn/docs/#/dfa/DFA%E6%9F%A5%E6%89%BE
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/dfa/WordTree.html
 * 基于DFA敏感词查询的算法简析：https://www.cnblogs.com/naaoveGIS/archive/2016/10/14/5960352.html
 *
 * @author ljh
 * created on 2020/11/20 12:01
 */
public class DFADemo extends Demo {
    
    @Test
    public void testDFA() {
        String text = "我来自中国，是中国人";
        WordTree wordTree = new WordTree();
        wordTree.addWord("我");
        wordTree.addWord("我来自");
        wordTree.addWord("来自");
        wordTree.addWord("是中国人");
        wordTree.addWord("中国人");
        
        // 匹配到最短关键词，并跳过已经匹配的关键词
        p(wordTree.matchAll(text, -1, false, false));   // [我, 来自, 是中国人]
        // 匹配到最短关键词，不跳过已经匹配的关键词
        p(wordTree.matchAll(text, -1, true, false));    // [我, 来自, 是中国人, 中国人]
        // 匹配到最长关键词，跳过已经匹配的关键词
        p(wordTree.matchAll(text, -1, false, true));    // [我, 我来自, 是中国人]
        // 匹配到最长关键词，不跳过已经匹配的关键词
        p(wordTree.matchAll(text, -1, true, true));     // [我, 我来自, 来自, 是中国人, 中国人]
    }
}
