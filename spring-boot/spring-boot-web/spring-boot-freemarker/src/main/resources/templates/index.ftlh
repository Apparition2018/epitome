<#-- 首页：共享变量、表达式、内置函数 -->
<#import "/macros.ftlh" as m>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>FreeMarker 演示</title>
    <style>
        body { font-family: sans-serif; margin: 2em; color: #333; }
        .nav a { margin-right: 1em; }
        .nav a.active { font-weight: bold; color: #c00; }
        code { background: #f5f5f5; padding: 2px 6px; border-radius: 4px; }
    </style>
</head>
<body>
<@m.nav active="index"/>

<h1>${appName}</h1>

<h2>1. 输出表达式</h2>
<p>消息：<code>${message}</code></p>
<p>数值（number_format 控制精度）：<code>${count}</code></p>

<h2>2. 内置函数（Built-ins）</h2>
<p>字符串大写：<code>${message?upper_case}</code></p>
<p>字符串长度：<code>${message?length}</code></p>
<p>拼接：<code>${message + " · " + appName}</code></p>
<p>条件（?then）：<code>${(count > 100)?then('大于100', '不大于100')}</code></p>

<h2>3. 共享静态变量</h2>
<p>来自 FreeMarkerConfigurer 注入的 appName：<code>${appName}</code></p>

<p><a href="/users">查看用户列表 →</a></p>
</body>
</html>
