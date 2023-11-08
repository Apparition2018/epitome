seajs.config({
    alias: {
        "jquery": "https://unpkg.com/jquery@^1/dist/jquery.min.js"
    }
});

define(function(require, exports, module) {
    require('jquery');
    const math = require('./math');
    const product = math.multiply(9, 9);
    $(".sea").html("9 * 9 = " + product);
});