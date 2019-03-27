package knowledge.api.lang.enum_;

import org.junit.Test;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

/**
 * https://blog.csdn.net/cndmss/article/details/51441617
 * https://blog.csdn.net/pushme_pli/article/details/7197573
 * <p>
 * 枚举比常量类有更多灵活的用法
 * 使用枚举，可以有效的提高代码的整洁性、可读性
 * <p>
 * switch:
 * jdk1.5-: byte, short, char, int
 * jdk1.5:  Byte, Short, Character, Integer, enum
 * jdk1.7:  String
 */
public class EnumDemo {

    // 作为普通的常量使用
    enum WeekEnum {
        MON, TUES, WED, THURS, FRI, SAT, SUN
    }

    public static void main(String[] args) {
        weekEnum(WeekEnum.TUES);
    }

    // 配合 switch 使用
    private static void weekEnum(WeekEnum week) {
        switch (week) {
            case MON:
                p("星期一");
                break;
            case TUES:
                p("星期二");
                break;
            case WED:
                p("星期三");
                break;
            case THURS:
                p("星期四");
                break;
            case FRI:
                p("星期五");
                break;
            case SAT:
                p("星期六");
                break;
            case SUN:
                p("星期天");
                break;
        }
    }

    /*
     * 往枚举中添加变量、构造函数、以达到灵活获取指定值的目的
     * 通常用于一些业务系统中定义一些固定值，如用于匹配db中的字段值等
     */
    enum CompanyEnum {
        SF("顺丰速运", 1001), YTO("圆通速递", 1002), STO("申通物流", 1003), YD("韵达快运", 1004), YZPY("中国邮政", 1005);

        private String company;

        private int code;

        CompanyEnum(String company, int code) {
            this.company = company;
            this.code = code;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        // 根据物流公司名字获取对应的编码
        public static int getCodeByCompany(String company) {
            for (CompanyEnum c : CompanyEnum.values()) {
                if (c.getCompany().equals(company.trim())) {
                    return c.code;
                }
            }
            return 0;
        }

        // 根据物流公司编码获取对应的名字
        public static String getCompanyByCode(int code) {
            for (CompanyEnum c : CompanyEnum.values()) {
                if (c.getCode() == code) {
                    return c.getCompany();
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return "CompanyEnum{" +
                    "company='" + company + '\'' +
                    ", code=" + code +
                    '}';
        }
    }

    @Test
    public void companyEnum() {
        p(CompanyEnum.SF.getCompany());
        p(CompanyEnum.YTO.getCode());
    }

    // 往枚举中添加自己特定方法，以实现自己所需的相关业务逻辑 -- 见上面的CompanyEnum
    @Test
    public void companyEnum2() {
        p(CompanyEnum.getCodeByCompany("顺丰速运"));
        p(CompanyEnum.getCompanyByCode(1003));
    }

    // 一些其它用法
    @Test
    public void companyEnum3() {
        // 返回此枚举常量的名称,继承自java.lang.Enum类,通常重写该方法以实现相关操作
        p(CompanyEnum.SF.toString());                  // CompanyEnum{company='顺丰速运', code=1001}

        // 返回此枚举常量的名称
        p(CompanyEnum.SF.name());                      // SF

        // 返回枚举常量的序数（它在枚举声明中的位置，其中初始常量序数为零）
        p(CompanyEnum.YTO.ordinal());                  // 1

        // 获取枚举中的常量个数
        p(CompanyEnum.values().length);                //5

        // 返回带指定名称的指定枚举类型的枚举常量
        p(CompanyEnum.valueOf(CompanyEnum.class, "SF").getCompany()); // 顺丰速运

        // 比较此枚举与指定对象的顺序;SF位置是0,YTO位置是1,结果为 0-1=-1
        p(CompanyEnum.SF.compareTo(CompanyEnum.YTO));  // -1

        // 返回与此枚举常量的枚举类型相对应的 Class 对象
        p(CompanyEnum.SF.getDeclaringClass());         // class knowledge.api.lang.enum_.EnumDemo$CompanyEnum

        // equals()
        p(CompanyEnum.SF.name().equals("SF"));         // true
        p(CompanyEnum.SF.equals(CompanyEnum.YTO));     // true

    }

    // EnumSet、EnumMap的使用
    @Test
    public void enumSetAndEnumMap() {
        // EnumSet的使用
        EnumSet<CompanyEnum> companySet = EnumSet.allOf(CompanyEnum.class);
        for (CompanyEnum company : companySet) {
            p(company.getCode() + ":" + company.getCompany());
        }

        // EnumMap的使用
        EnumMap<CompanyEnum, String> companyMap = new EnumMap<>(CompanyEnum.class);
        companyMap.put(CompanyEnum.SF, "顺丰map");
        companyMap.put(CompanyEnum.YTO, "圆通map");
        for (Map.Entry<CompanyEnum, String> entry : companyMap.entrySet()) {
            p(entry.getKey().name() + ":" + entry.getValue() + ":" + entry.getKey());
        }
    }
    
    public static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }

}
