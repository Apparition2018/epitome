package knowledge.types;

import l.demo.CompanyEnum;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

/**
 * Enum
 * 优点：
 * 1.枚举有更多灵活的用法
 * 2.有效的提高代码的整洁性、可读性
 * 3.限制非法值的传入
 * <p>
 * 枚举类名带上 Enum 后缀，枚举成员名称需要全大写，单词间用下划线隔开（阿里编程规约）
 * <p>
 * JAVA 常量类的实现方式_枚举类＿项目实践：https://www.cnblogs.com/lihaoyang/p/6913295.html
 * JAVA 枚举常用场景小结：https://blog.csdn.net/cndmss/article/details/51441617
 * https://tool.oschina.net/uploads/apidocs/jdk-zh/java/lang/Enum.html
 *
 * @author ljh
 * created on 2020/11/2 19:39
 */
public class EnumDemo extends Demo {

    @Test
    public void companyEnum() {
        p(CompanyEnum.SF.getCode());                // 1001
        p(CompanyEnum.SF.getCompany());             // 顺丰速运

        p(CompanyEnum.getCodeByCompany("顺丰速运")); // 1001
        p(CompanyEnum.getCompanyByCode(1001));      // 顺丰速运
    }

    /**
     * 一些其它用法
     */
    @Test
    public void companyEnum2() {
        // 返回此枚举常量的名称,继承自java.lang.Enum类,通常重写该方法以实现相关操作
        p(CompanyEnum.SF.toString());               // CompanyEnum{CompanyEnum='顺丰速运', code=1001}

        // 返回此枚举常量的名称
        p(CompanyEnum.SF.name());                   // SF

        // 返回枚举常量的序数（它在枚举声明中的位置，其中初始常量序数为零）
        p(CompanyEnum.YTO.ordinal());               // 1

        // 获取枚举中的常量个数
        p(CompanyEnum.values().length);             // 5

        // 返回带指定名称的指定枚举类型的枚举常量
        p(CompanyEnum.valueOf(CompanyEnum.class, "SF").getCompany()); // 顺丰速运

        // 比较此枚举与指定对象的顺序;SF位置是0,YTO位置是1,结果为 0-1=-1
        p(CompanyEnum.SF.compareTo(CompanyEnum.YTO));   // -1

        // 返回与此枚举常量的枚举类型相对应的 Class 对象
        // 建议使用前先见检验枚举是否包含此项
        p(CompanyEnum.SF.getDeclaringClass());      // class l.demo.CompanyEnum

        // equals()
        p(CompanyEnum.SF.name().equals("SF"));      // true
        p(CompanyEnum.SF.equals(CompanyEnum.YTO));      // false

    }

    /**
     * EnumSet, EnumMap 的使用
     */
    @Test
    public void enumSetAndEnumMap() {
        // EnumSet
        EnumSet<CompanyEnum> companySet = EnumSet.allOf(CompanyEnum.class);
        for (CompanyEnum CompanyEnum : companySet) {
            p(CompanyEnum.getCode() + ":" + CompanyEnum.getCompany());
        }

        // EnumMap
        EnumMap<CompanyEnum, String> companyMap = new EnumMap<>(CompanyEnum.class);
        companyMap.put(CompanyEnum.SF, "顺丰map");
        companyMap.put(CompanyEnum.YTO, "圆通map");
        for (Map.Entry<CompanyEnum, String> entry : companyMap.entrySet()) {
            p(entry.getKey().name() + ":" + entry.getValue() + ":" + entry.getKey());
        }
    }

}
