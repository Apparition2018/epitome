// 配置 require.js
require.config({
    // 各个模块的相对路径，js 文件省略后缀名
    paths: {
        "jquery": "https://cdn.bootcss.com/jquery/1.12.4/jquery.min",
        "text": "https://cdn.bootcss.com/require-text/2.0.12/text.min", // require-text
        "text1": "../template/test1.html",
        "text2": "../template/test2.html",
        "css1": "../style/test1.css",
        "css2": "../style/test2.css"
    }
});

// text! 代表文本资源
require(['jquery', 'text!text1', 'text!text2', 'text!css1', 'text!css2'], function ($, template1, template2, css1, css2) {
    // 进入页面先设置为页面 test1.html 内容
    $(".css-attribute").html(css1);
    $(".page").html(template1);

    // 点击 skip1 按钮设置为页面 test2.html 内容
    $(".skip1").click(function () {
        $(".css-attribute").html(css2);
        $(".page").html(template2);
    });

    // 点击 skip2 按钮设置为页面 test1.html 内容 ??? 无效
    $(".skip2").click(function() {
        $(".css-attribute").html(css1);
        $(".page").html(template1);
    });
});