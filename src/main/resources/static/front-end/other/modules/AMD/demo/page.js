require.config({
    paths: {
        'jquery': 'https://cdn.bootcss.com/jquery/1.12.4/jquery.min'
    }
});

require(['jquery', 'static/front-end/other/modules/AMD/demo/math'], function($, math) {
    var product = math.multiply(9, 9);
    $(".require").html("9 * 9 = " + product);
});