package org.ljh.mybatis.mapper.dynamic;

import java.math.BigDecimal;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class CountryLanguageDynamicSqlSupport {
    public static final CountryLanguage countryLanguage = new CountryLanguage();

    public static final SqlColumn<String> countryCode = countryLanguage.countryCode;

    public static final SqlColumn<String> language = countryLanguage.language;

    public static final SqlColumn<String> isOfficial = countryLanguage.isOfficial;

    public static final SqlColumn<BigDecimal> percentage = countryLanguage.percentage;

    public static final class CountryLanguage extends AliasableSqlTable<CountryLanguage> {
        public final SqlColumn<String> countryCode = column("CountryCode", JDBCType.CHAR).withJavaProperty("countryCode");

        public final SqlColumn<String> language = column("Language", JDBCType.CHAR).withJavaProperty("language");

        public final SqlColumn<String> isOfficial = column("IsOfficial", JDBCType.CHAR).withJavaProperty("isOfficial");

        public final SqlColumn<BigDecimal> percentage = column("Percentage", JDBCType.DECIMAL).withJavaProperty("percentage");

        public CountryLanguage() {
            super("countrylanguage", CountryLanguage::new);
        }
    }
}