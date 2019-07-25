// 默认导出
export default function operation() {
    var plus = function (a, b) { return a + b; };
    var subtract = function (a, b) { return a - b; };
    var multiply = function (a, b) { return a * b; };
    var divide = function (a, b) { return a / b; };
    return {
        plus: plus,
        subtract: subtract,
        multiply: multiply,
        divide: divide
    };
};