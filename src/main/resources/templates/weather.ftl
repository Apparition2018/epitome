<html lang="en">
<head>
    <meta name="description" content="https://github.com/defunkt/jquery-pjax/#pjax--pushstate--ajax">
    <meta name="description" content="https://blog.csdn.net/weixin_44776993/article/details/107502380">
    <title>${city!''}天气</title>
    <link href="https://unpkg.com/nprogress/nprogress.css" rel="stylesheet">
    <script src="https://unpkg.com/nprogress/nprogress.js"></script>
    <style>
        body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
            font-size: 14px;
            line-height: 1.5;
            color: #333;
            background-color: #f4f4f4;
        }

        header {
            position: fixed;
            top: 30px;
            height: 50px;
            width: 800px;
            border: 1px solid #777;
            left: 0;
            right: 0;
            margin: auto;
            background-color: #f3f3f3;
            border-radius: 3px;
        }

        header > span {
            width: 200px;
            text-align: center;
            line-height: 40px;
            padding: 0;
            float: left;
        }

        header > span > a {
            display: block;
            text-decoration: none;
            width: 100%;
            height: 100%;
            border-right: 1px solid #777;
            color: #333;
        }

        header > span > a:hover {
            background: #777;
            color: #fff;
        }

        header > span:last-child {
            border-right: 0;
        }

        section {
            margin-top: 90px;
        }

        .container {
            width: 802px;
            height: 70%;
            margin: auto;
        }

        .container > textarea {
            border: 1px solid #777;
            border-radius: 3px;
            width: 100%;
            height: 100%;
            background-color: #f3f3f3;
            overflow-y: hidden;
            color: #333;
        }

        .footer {
            margin-left: auto;
            margin-right: auto;
            margin-top: 40px;
            text-align: center;
        }
    </style>
</head>
<body>
<header>
    <span><a href="/weather/北京">北京</a></span>
    <span><a href="/weather/上海">上海</a></span>
    <span><a href="/weather/广州">广州</a></span>
    <span><a href="/weather/中山">中山</a></span>
</header>
<section>
    <div class="container" id="container">
        ${weather!''}
    </div>
</section>
<footer class="footer">
    <h3>数据由 wthrcdn.etouch.cn 提供</h3>
</footer>
<script src="https://unpkg.com/jquery@^1/dist/jquery.min.js"></script>
<script src="https://unpkg.com/jquery.pjax/jquery.pjax.js"></script>
<script type="text/javascript">
    $.pjax({
        selector: 'a',
        container: '#container',
        show: 'fade',
        cache: false,
        storage: false,
        timeout: 0,
        titleSuffix: '天气'
    });
    NProgress.configure({showSpinner: false});
    $('#container').bind('pjax.start', function () {
        NProgress.start();
    });
    $('#container').bind('pjax.end', function () {
        NProgress.done();
    })
</script>
</body>
</html>
