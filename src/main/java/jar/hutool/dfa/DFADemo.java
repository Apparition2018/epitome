package jar.hutool.dfa;

import cn.hutool.dfa.WordTree;

/**
 * <a href="https://doc.hutool.cn/pages/dfa/index/">DFA</a> (Deterministic Finite Automaton)    确定有穷自动机
 * <p><a href="https://doc.hutool.cn/pages/dfa/">DFA 查找</a>
 * <pre>
 * <a href="https://plus.hutool.cn/apidocs/cn/hutool/dfa/WordTree.html">WordTree api</a>
 * <a href="https://www.cnblogs.com/naaoveGIS/archive/2016/10/14/5960352.html">基于DFA敏感词查询的算法简析</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/11/20 12:01
 */
public class DFADemo {

    public static void main(String[] args) {
        String text = "我来自中国，是中国人";
        WordTree wordTree = new WordTree();
        wordTree.addWord("我");
        wordTree.addWord("我来自");
        wordTree.addWord("来自");
        wordTree.addWord("是中国人");
        wordTree.addWord("中国人");

        // 匹配到最短关键词，并跳过已经匹配的关键词
        System.out.println(wordTree.matchAll(text, -1, false, false));  // [我, 来自, 是中国人]
        // 匹配到最短关键词，不跳过已经匹配的关键词
        System.out.println(wordTree.matchAll(text, -1, true, false));   // [我, 来自, 是中国人, 中国人]
        // 匹配到最长关键词，跳过已经匹配的关键词
        System.out.println(wordTree.matchAll(text, -1, false, true));   // [我, 我来自, 是中国人]
        // 匹配到最长关键词，不跳过已经匹配的关键词
        System.out.println(wordTree.matchAll(text, -1, true, true));    // [我, 我来自, 来自, 是中国人, 中国人]
    }
}
