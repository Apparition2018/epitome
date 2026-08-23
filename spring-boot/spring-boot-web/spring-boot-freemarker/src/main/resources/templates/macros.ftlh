<#--
  宏定义库：通过 <#import "/macros.ftlh" as m> 引入
-->
<#-- 通用导航宏 -->
<#macro nav active="">
<nav class="nav">
    <a href="/" class="${(active == 'index')?then('active', '')}">首页</a>
    <a href="/users" class="${(active == 'users')?then('active', '')}">用户列表</a>
    <a href="/form" class="${(active == 'form')?then('active', '')}">表单回显</a>
</nav>
</#macro>

<#-- 输出用户标签（演示函数式宏 + 嵌套条件） -->
<#function roleLabel roles>
    <#if roles?seq_contains('admin')>
        <#return '管理员'>
    <#elseif roles?seq_contains('user')>
        <#return '普通用户'>
    <#else>
        <#return '访客'>
    </#if>
</#function>
