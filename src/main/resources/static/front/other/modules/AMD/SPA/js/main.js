// 配置 require.js
require.config({
    // 各个模块的相对路径，js 文件省略后缀名
    paths: {
        "jquery": "https://unpkg.com/jquery@^1/dist/jquery.min",
        "text": "https://unpkg.com/requirejs-text/text",
        "html1": "../template/test1.html",
        "html2": "../template/test2.html",
        "css1": "../style/test1.css",
        "css2": "../style/test2.css"
    }
});

// text! 代表文本资源
require(['jquery', 'text!html1', 'text!html2', 'text!css1', 'text!css2'], function ($, html1, html2, css1, css2) {
    // 进入页面先设置为页面 test1.html 内容
    $(".css-attribute").html(css1);
    $(".page").html(html1);

    // 点击 skip1 按钮设置为页面 test2.html 内容
    $(".skip1").click(function () {
        $(".css-attribute").html(css2);
        $(".page").html(html2);
    });

    // 点击 skip2 按钮设置为页面 test1.html 内容 ??? 无效
    $(".skip2").click(function() {
        $(".css-attribute").html(css1);
        $(".page").html(html1);
    });
});