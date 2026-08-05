package knowledge.suggestions;

import java.util.Objects;

/**
 * 建议40：匿名类的构造函数
 *
 * @author ljh
 * @since 2020/10/10 19:23
 */
class Calculator {
    enum Ops {
        ADD, SUB
    }

    private int i, j, result;

    public Calculator() {
    }

    Calculator(int _i, int _j) {
        i = _i;
        j = _j;
    }

    // 设置符号，是加法运算还是减法运算
    protected void setOperator(Ops _ops) {
        result = Objects.equals(_ops, Ops.ADD) ? i + j : i - j;
    }

    // 取得运算结果
    public int getResult() {
        return result;
    }
}
