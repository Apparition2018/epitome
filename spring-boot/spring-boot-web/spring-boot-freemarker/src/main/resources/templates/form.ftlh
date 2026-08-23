<#-- 表单回显 + HTML 转义（防 XSS） -->
<#import "/macros.ftlh" as m>
<#-- 模板级转义：本模板内所有 ${} 输出自动 HTML 转义 -->
<#escape x as x?html>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>表单回显</title>
    <style>
        body { font-family: sans-serif; margin: 2em; color: #333; }
        .nav a { margin-right: 1em; }
        .nav a.active { font-weight: bold; color: #c00; }
        label { display: block; margin: .6em 0; }
        input { padding: 6px; }
        pre { background: #f5f5f5; padding: 8px; }
        .danger { color: #c00; }
    </style>
</head>
<body>
<@m.nav active="form"/>

<h1>表单回显（HTML 转义演示）</h1>

<p>尝试访问：<code>/form?keyword=&lt;script&gt;alert(1)&lt;/script&gt;</code></p>

<form method="get" action="/form">
    <label>关键词：<input type="text" name="keyword" value="${keyword}"></label>
    <button type="submit">提交</button>
</form>

<h2>回显结果</h2>
<p>输入的关键词：<code>${keyword}</code></p>
<#if keyword?has_content>
    <p>你搜索的是：<strong>${keyword}</strong></p>
    <p class="danger">注意：上面的值已被自动转义，脚本不会执行。</p>
<#else>
    <p>（尚未输入关键词）</p>
</#if>

<h2>为什么需要转义</h2>
<pre>${'<script>alert("xss")</script>'} 会被转义成 &amp;lt;script&amp;gt;</pre>
<#-- 不要在模板中使用 ?no_esc 输出原始输入，否则会绕过 <#escape> 导致 XSS 漏洞 -->
</body>
</html>
</#escape>
