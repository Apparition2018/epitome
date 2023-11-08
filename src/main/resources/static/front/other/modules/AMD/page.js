require.config({
    paths: {
        'jquery': 'https://unpkg.com/jquery@^1/dist/jquery.min'
    }
});

require(['jquery', 'math'], function($, math) {
    var product = math.multiply(9, 9);
    $(".require").html("9 * 9 = " + product);
});