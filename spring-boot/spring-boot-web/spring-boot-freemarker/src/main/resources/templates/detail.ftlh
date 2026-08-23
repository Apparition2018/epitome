<#-- 用户详情：路径变量、null 安全、! 默认值 -->
<#import "/macros.ftlh" as m>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>用户详情</title>
    <style>
        body { font-family: sans-serif; margin: 2em; color: #333; }
        .nav a { margin-right: 1em; }
        .nav a.active { font-weight: bold; color: #c00; }
        dl { display: grid; grid-template-columns: 80px 1fr; }
        dt { font-weight: bold; }
        .warn { color: #c00; }
    </style>
</head>
<body>
<@m.nav/>

<h1>用户详情</h1>

<#if user??>
    <#-- ?? 判空；! 提供默认值 -->
    <dl>
        <dt>id</dt><dd>${user.id}</dd>
        <dt>姓名</dt><dd>${user.name}</dd>
        <dt>邮箱</dt><dd>${user.email!}</dd>
        <dt>角色</dt><dd>${m.roleLabel(user.roles)}</dd>
        <dt>余额</dt><dd>¥${user.balance?string("0.00")}</dd>
        <dt>创建</dt><dd>${user.createdAt?date}</dd>
    </dl>
<#else>
    <p class="warn">未找到该用户（user 为 null），走 &lt;#if&gt; &lt;#else&gt; 分支。</p>
</#if>

<h2>null 安全示例</h2>
<p>undefined 变量 + 默认值：<code>${unknown!'默认值'}</code></p>
<p>undefined 变量（?default）：<code>${unknown?default('另一默认值')}</code></p>
</body>
</html>
