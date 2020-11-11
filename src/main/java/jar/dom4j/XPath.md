# XPath
XPath 是一门在 XML 文档中查找信息的语言。XPath 可用来在 XML 文档中对元素和属性进行遍历。  
XPath 是 W3C XSLT 标准的主要元素，并且 XQuery 和 XPointer 都构建于 XPath 表达之上。  
因此，对 XPath 的理解是很多高级 XML 应用的基础。
---
## 参考网站
1. [XPath 教程](https://www.w3school.com.cn/xpath/index.asp)
2. [XML 命名空间（XML Namespaces）](https://www.w3school.com.cn/xml/xml_namespaces.asp)
3. [XPath、XQuery 以及 XSLT 函数](https://www.w3school.com.cn/xpath/xpath_functions.asp)
---
## XPath 术语
1. 节点 (Node)
    1. 元素
    2. 属性
    3. 文本
    4. 命名空间
    5. 处理指令
    6. 注释
    7. 文档节点（根节点）
2. 基本值/原子值 (Atomic value)：无父或无子的节点
3. 项目 (Item)：基本值或节点
---
## XML 实例
```
<?xml version="1.0" encoding="ISO-8859-1"?>
<bookstore>
   <book>
       <title lang="eng">Harry Potter</title>
       <price>29.99</price>
   </book>
   <book>
       <title lang="eng">Learning XML</title>
       <price>39.95</price>
   </book>
</bookstore>
>```
---
## XPath 语法
>### 选取节点
>|表达式|描述|
>|:----|:---|
>|NodeName|选取 NodeName 的所有子节点|
>|/|从根节点选取|
>|//|从匹配选择的当前节点选择文档中的节点，而不考虑它们的位置|
>|.|选取当前节点|
>|..|选取当前节点的父节点|
>|@|选取属性|
>
>|路径表达式|结果|
>|:-------|:---|
>|bookstore|选取 bookstore 元素的所有子节点|
>|/bookstore|选取根元素 bookstore。注释：假如路径起始于正斜杠(/)，则此路径始终代表到某元素的绝对路径|
>|bookstore/book|选取属于 bookstore 的子元素的所有 book 元素|
>|//book|选取所有 book 子元素，而不管它们在文档中的位置|
>|bookstore//book|选择属于 bookstore 元素的后代的所有 book 元素，而不管它们位于 bookstore 之下的什么位置|
>|//@lang|选取名为 lang 的所有属性|
>### 谓语 (Predicates)
>谓语用来查找某个特定的节点或者包含某个指定的值的节点。谓语被嵌在方括号中。  
>
>|路径表达式|结果|
>|:-------|:---|
>|/bookstore/book[1]|选取属于 bookstore 子元素的第一个 book 元素|
>|/bookstore/book[last()]|选取属于 bookstore 子元素的最后一个 book 元素|
>|/bookstore/book[last()-1]|选取属于 bookstore 子元素的倒数第二个 book 元素|
>|/bookstore/book[position()<3]|选取最前面的两个属于 bookstore 元素的子元素的 book 元素|
>|//title[@lang]|选取所有拥有名为 lang 的属性的 title 元素|
>|//title[@lang='eng']|选取所有 title 元素，且这些元素拥有值为 eng 的 lang 属性|
>|/bookstore/book[price>35.00]|选取 bookstore 元素的所有 book 元素，且其中的 price 元素的值须大于 35.00|
>|/bookstore/book[price>35.00]/title|选取 bookstore 元素中的 book 元素的所有 title 元素，且其中的 price 元素的值须大于 35.00|
>### 通配符
>|路径表达式|结果|
>|:-------|:---|
>|/bookstore/*|选取 bookstore 元素的所有子元素|
>|//*|选取文档中的所有元素|
>|//title[@*]|选取所有带有属性的 title 元素|
---
## XPath Step
Step 的语法：轴名称::节点[谓语]
>### XPath 轴
>轴可定义相对于当前节点的节点集  
>
>|轴名称|结果|
>|:----|:---|
>|ancestor|选取当前节点的所有先辈（父、祖父等）|
>|ancestor-or-self|选取当前节点的所有先辈（父、祖父等）以及当前节点本身|
>|attribute|选取当前节点的所有属性|
>|child|选取当前节点的所有子元素|
>|descendant|选取当前节点的所有后代元素（子、孙等）|
>|descendant-or-self|选取当前节点的所有后代元素（子、孙等）以及当前节点本身|
>|following|选取文档中当前节点的结束标签之后的所有节点|
>|namespace|选取当前节点的所有命名空间节点|
>|parent|选取当前节点的父节点|
>|preceding|选取文档中当前节点的开始标签之前的所有节点|
>|preceding-sibling|选取当前节点之前的所有同级节点|
>|self|选取当前节点|
>
>|例子|结果|
>|:---|:---|
>|child::text()|选取当前节点的所有文本子节点|
>|child::node()|选取当前节点的所有子节点|
>|descendant::book|选取当前节点的所有 book 后代|
>|ancestor-or-self::book|选取当前节点的所有 book 先辈以及当前节点（如果此节点是 book 节点）|
>|child::*/child::price|选取当前节点的所有 price 孙节点|
---
## XPath 运算符
>|运算符|描述|运算符|描述|
>|:---|:---:|:---|:---:|
>|+|加法|&#124;|返回两个节点集|
>|-|减法|==|等于|
>|*|乘法|!=|不等于|
>|div|除法|&lt;|小于|
>|or|与|≤|小于等于|
>|and|或|&gt;|大于|
>|mod|取余|≥|大于等于|
---