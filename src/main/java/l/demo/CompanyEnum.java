package l.demo;

import lombok.Getter;

import java.util.Objects;

/**
 * CompanyEnum
 *
 * @author ljh
 * created on 2020/11/2 14:04
 */
@Getter
public enum CompanyEnum implements BaseEnum {
    SF("顺丰速运", 1001),
    YTO("圆通速递", 1002),
    STO("申通物流", 1003),
    EMS("中国邮政", 1004),
    DHL("中外运敦豪", 1005);

    private final String company;
    private final Integer code;

    CompanyEnum(String company, Integer code) {
        this.company = company;
        this.code = code;
    }

    /**
     * 根据物流公司名字获取对应的编码
     */
    public static int getCodeByCompany(String company) {
        for (CompanyEnum c : CompanyEnum.values()) {
            if (Objects.equals(c.getCompany(), company.trim())) return c.code;
        }
        return 0;
    }

    /**
     * 根据物流公司编码获取对应的名字
     */
    public static String getCompanyByCode(int code) {
        for (CompanyEnum c : CompanyEnum.values()) {
            if (c.getCode() == code) {
                return c.getCompany();
            }
        }
        return null;
    }

    /**
     * 遍历
     */
    public void traversal() {
        for (CompanyEnum companyEnum : CompanyEnum.values()) {
            System.out.println("name: " + companyEnum.name());
            System.out.println("ordinal: " + companyEnum.ordinal());
            System.out.println("company: " + companyEnum);
        }
    }
}
