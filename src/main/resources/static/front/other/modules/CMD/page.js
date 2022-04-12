seajs.config({
    alias: {
        "jquery": "https://cdn.bootcdn.net/ajax/libs/jquery/1.12.4/jquery.min.js"
    }
});

define(function(require, exports, module) {
    require('jquery');
    const math = require('./math');
    const product = math.multiply(9, 9);
    $(".sea").html("9 * 9 = " + product);
});