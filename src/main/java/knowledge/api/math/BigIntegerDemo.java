package knowledge.api.math;

import org.junit.Test;

import java.math.BigInteger;

/**
 * BigInteger
 * BigInteger 不可变的任意精度的整数。所有操作中，都以二进制补码形式表示 BigInteger（如 Java 的基本整数类型）。
 * BigInteger 提供所有 Java 的基本整数操作符的对应物，并提供 java.lang.Math 的所有相关方法。
 * 另外，BigInteger 还提供以下运算：模算术、GCD 计算、质数测试、素数生成、位操作以及一些其他操作。
 * <p>
 * BigInteger   and(BigInteger val)             this & val      当且仅当 this 和 val 同时为负时，此方法返回一个负 BigInteger
 * BigInteger   andNot(BigInteger val)          this & ~val     当且仅当 this 为负且 val 为正时，此方法返回一个负 BigInteger
 * BigInteger   or(BigInteger val)              this | val      当且仅当 this 和 val 之一为负时，此方法返回一个负 BigInteger
 * BigInteger   xor(BigInteger val)             this ^ val      当且仅当 this 和 val 中只有一个为负时，此方法返回一个负 BigInteger
 * BigInteger   not()                           ~this           当且仅当此 BigInteger 为非负时，此方法返回一个负值
 * BigInteger	clearBit(int n)                 this & ~(1<<n)  返回其值与清除了指定位的此 BigInteger 等效的 BigInteger
 * BigInteger	setBit(int n)                   this | (1<<n)   返回其值与设置了指定位的此 BigInteger 等效的 BigInteger
 * BigInteger	flipBit(int n)                  this ^ (1<<n)   返回其值与对此 BigInteger 进行指定位翻转后的值等效的 BigInteger
 * BigInteger	shiftLeft(int n)                this << n       位移距离 n 可以为负，在此情况下，此方法执行右移操作。（计算 floor(this * 2n)。）
 * BigInteger	shiftRight(int n)               this >> n       位移距离 n 可以为负，在此情况下，此方法执行左移操作。（计算 floor(this / 2n)。）
 * BigInteger	add(BigInteger val)             this + val
 * BigInteger	subtract(BigInteger val)        this - val
 * BigInteger	multiply(BigInteger val)        this * val
 * BigInteger	divide(BigInteger val)          this / val
 * BigInteger[]	divideAndRemainder(BI val)      this / val，this % val
 * BigInteger	negate()                        -this
 * BigInteger	pow(int exponent)               this^exponent
 * BigInteger	mod(BigInteger m)               this mod m，结果始终是负数
 * BigInteger	remainder(BigInteger val)       this % val，结果可以是负数
 * BigInteger	modInverse(BigInteger m)        this^-1 mod m
 * BigInteger	modPow(BI exponent, BI m)       this^exponent mod m
 * <p>
 * double	    doubleValue()                   BigInteger → double
 * float	    floatValue()                    BigInteger → float
 * long	        longValue()                     BigInteger → long
 * int	        intValue()                      BigInteger → int
 * byte[]	    toByteArray()                   返回一个 byte 数组，该数组包含此 BigInteger 的二进制补码表示形式
 * static BI	valueOf(long val)               返回其值等于指定 long 的值的 BigInteger
 * <p>
 * int	        compareTo(BigInteger val)       将此 BigInteger 与指定的 BigInteger 进行比较
 * <p>
 * int	        signum()                        返回此 BigInteger 的正负号函数
 */
public class BigIntegerDemo {

    @Test
    public void BigInteger() {
        // BigInteger(String val[, int radix])
        // String → BigInteger
        BigInteger bi = new BigInteger("100");
        p(bi);

        // 更多构造方法请查看 API
    }

    /**
     * BigInteger	gcd(BigInteger val)
     * 返回一个 BigInteger，其值是 abs(this) 和 abs(val) 的最大公约数
     */
    @Test
    public void gcd() {
        BigInteger bi1 = new BigInteger("64");
        BigInteger bi2 = new BigInteger("56");
        p(bi1.gcd(bi2)); // 8
    }

    /**
     * BigInteger	nextProbablePrime()
     * 返回大于此 BigInteger 的可能为素数的第一个整数
     *
     * boolean	    isProbablePrime(int certainty)  ???
     * 如果此 BigInteger 可能为素数，则返回 true，如果它一定为合数，则返回 false
     */
    @Test
    public void prime() {
        BigInteger bi = new BigInteger("9");
        p(bi.nextProbablePrime()); // 11
        p(bi.isProbablePrime(1));   // false
        p(bi.isProbablePrime(-1));  // true
    }

    /**
     * boolean	    testBit(int n)
     * 当且仅当设置了指定的位时，返回 true。（计算 ((this & (1<<n)) != 0)。）
     */
    @Test
    public void testBit() {
        BigInteger bi = new BigInteger("4");
        p(bi.testBit(0)); // false
        p(bi.testBit(1)); // false
        p(bi.testBit(2)); // true
        p(bi.testBit(3)); // false
    }

    private static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }

}
