seajs.config({
    alias: {
        "jquery": "https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"
    }
});

define(function(require, exports, module) {
    require('jquery');
    var math = require('./math');
    var product = math.multiply(9, 9);
    $(".sea").html("9 * 9 = " + product);
});