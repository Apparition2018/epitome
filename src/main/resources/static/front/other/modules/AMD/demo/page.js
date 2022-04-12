require.config({
    paths: {
        'jquery': 'https://cdn.bootcdn.net/ajax/libs/jquery/1.12.4/jquery.min.js'
    }
});

require(['jquery', 'static/front-end/other/modules/AMD/demo/math'], function($, math) {
    var product = math.multiply(9, 9);
    $(".require").html("9 * 9 = " + product);
});