// 默认导出
export default function operation() {
    let plus = function (a, b) { return a + b; };
    let subtract = function (a, b) { return a - b; };
    let multiply = function (a, b) { return a * b; };
    let divide = function (a, b) { return a / b; };
    return {
        plus: plus,
        subtract: subtract,
        multiply: multiply,
        divide: divide
    };
};