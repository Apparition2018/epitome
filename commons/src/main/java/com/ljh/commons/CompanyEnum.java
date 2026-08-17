package com.ljh.commons;

import lombok.Getter;

/**
 * CompanyEnum
 *
 * @author ljh
 * @since 2020/11/2 14:04
 */
@Getter
public enum CompanyEnum {

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

    /** 遍历 */
    public void traversal() {
        for (CompanyEnum companyEnum : CompanyEnum.values()) {
            System.out.println("name: " + companyEnum.name());
            System.out.println("ordinal: " + companyEnum.ordinal());
            System.out.println("company: " + companyEnum);
        }
    }
}
