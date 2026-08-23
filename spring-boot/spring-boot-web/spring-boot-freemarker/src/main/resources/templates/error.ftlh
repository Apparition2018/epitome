<#-- 统一错误页：由 Boot 的 BasicErrorController 或自定义 ErrorController 渲染
     可用模型属性：status、error、message、path、trace、timestamp、exception -->
<#import "/macros.ftlh" as m>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>出错了</title>
    <style>
        body { font-family: sans-serif; margin: 2em; color: #333; }
        .nav a { margin-right: 1em; }
        .card { border: 1px solid #e00; border-left-width: 6px; padding: 1em 1.5em; background: #fff5f5; }
        .status { font-size: 2em; color: #c00; }
        code { background: #f5f5f5; padding: 2px 6px; border-radius: 4px; }
    </style>
</head>
<body>
<@m.nav/>

<div class="card">
    <h1>服务出错了</h1>
    <p class="status">HTTP ${status!500}</p>
    <p>错误：<code>${error!'Internal Server Error'}</code></p>
    <#if message??><p>信息：<code>${message}</code></p></#if>
    <p>路径：<code>${path!'/'}</code></p>
    <p>时间：<code>${.now?datetime}</code></p>
</div>

<p><a href="/">返回首页</a></p>
</body>
</html>
