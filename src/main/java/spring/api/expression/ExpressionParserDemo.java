package spring.api.expression;

import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * ExpressionParser
 *
 * @author ljh
 * @since 2022/2/7 9:07
 */
public class ExpressionParserDemo {

    @Test
    public void testExpressionParser() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("1+2+3+4");
        System.out.println(expression.getValue());
    }
}
