<#-- 用户列表：遍历、条件、空值兜底、格式化 -->
<#import "/macros.ftlh" as m>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
    <style>
        body { font-family: sans-serif; margin: 2em; color: #333; }
        .nav a { margin-right: 1em; }
        .nav a.active { font-weight: bold; color: #c00; }
        table { border-collapse: collapse; margin: 1em 0; }
        th, td { border: 1px solid #ccc; padding: 6px 12px; text-align: left; }
        .admin { color: #c00; font-weight: bold; }
        .empty { color: #999; }
    </style>
</head>
<body>
<@m.nav active="users"/>

<h1>用户列表</h1>

<h2>1. &lt;#list&gt; 遍历 + 序号</h2>
<table>
    <tr><th>#</th><th>id</th><th>姓名</th><th>邮箱</th><th>角色</th><th>余额</th><th>管理员</th></tr>
    <#list users as u>
    <tr>
        <td>${u?index + 1}</td>
        <td>${u.id}</td>
        <td>${u.name}</td>
        <td>${u.email}</td>
        <td>${m.roleLabel(u.roles)}（${u.roles?join(", ")}）</td>
        <td>¥${u.balance?string("0.00")}</td>
        <td>${u.admin?then('是', '否')}</td>
    </tr>
    </#list>
</table>

<h2>2. &lt;#list&gt; 空列表兜底（&lt;#else&gt;）</h2>
<ul>
    <#list emptyList as e>
    <li>${e}</li>
    <#else>
    <li class="empty">（emptyList 为空，这里是 &lt;#else&gt; 兜底内容）</li>
    </#list>
</ul>

<h2>3. 日期格式化</h2>
<p>第一个用户创建日期：<code>${users[0].createdAt?date}</code>（date_format 控制格式）</p>

<h2>4. 内置函数合集</h2>
<p>用户数：<code>${users?size}</code></p>
<p>第一个姓名大写：<code>${users[0].name?upper_case}</code></p>
<p>是否包含「李四」：<code>${users?map(u -> u.name)?seq_contains('李四')?then('是', '否')}</code></p>
<p>所有姓名（?join）：<code>${users?map(u -> u.name)?join("、")}</code></p>
</body>
</html>
