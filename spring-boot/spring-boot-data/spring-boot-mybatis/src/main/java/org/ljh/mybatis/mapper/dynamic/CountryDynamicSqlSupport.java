package org.ljh.mybatis.mapper.dynamic;

import java.math.BigDecimal;
import java.sql.JDBCType;
import org.ljh.mybatis.entity.Continent;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class CountryDynamicSqlSupport {
    public static final Country country = new Country();

    public static final SqlColumn<String> code = country.code;

    public static final SqlColumn<String> name = country.name;

    public static final SqlColumn<Continent> continent = country.continent;

    public static final SqlColumn<String> region = country.region;

    public static final SqlColumn<BigDecimal> surfaceArea = country.surfaceArea;

    public static final SqlColumn<Short> indepYear = country.indepYear;

    public static final SqlColumn<Integer> population = country.population;

    public static final SqlColumn<BigDecimal> lifeExpectancy = country.lifeExpectancy;

    public static final SqlColumn<BigDecimal> GNP = country.GNP;

    public static final SqlColumn<BigDecimal> GNPOld = country.GNPOld;

    public static final SqlColumn<String> localName = country.localName;

    public static final SqlColumn<String> governmentForm = country.governmentForm;

    public static final SqlColumn<String> headOfState = country.headOfState;

    public static final SqlColumn<Integer> capital = country.capital;

    public static final SqlColumn<String> code2 = country.code2;

    public static final class Country extends AliasableSqlTable<Country> {
        public final SqlColumn<String> code = column("Code", JDBCType.CHAR).withJavaProperty("code");

        public final SqlColumn<String> name = column("Name", JDBCType.CHAR).withJavaProperty("name");

        public final SqlColumn<Continent> continent = column("Continent", JDBCType.VARCHAR, "org.ljh.mybatis.entity.handler.ContinentTypeHandler").withJavaProperty("continent");

        public final SqlColumn<String> region = column("Region", JDBCType.CHAR).withJavaProperty("region");

        public final SqlColumn<BigDecimal> surfaceArea = column("SurfaceArea", JDBCType.DECIMAL).withJavaProperty("surfaceArea");

        public final SqlColumn<Short> indepYear = column("IndepYear", JDBCType.SMALLINT).withJavaProperty("indepYear");

        public final SqlColumn<Integer> population = column("Population", JDBCType.INTEGER).withJavaProperty("population");

        public final SqlColumn<BigDecimal> lifeExpectancy = column("LifeExpectancy", JDBCType.DECIMAL).withJavaProperty("lifeExpectancy");

        public final SqlColumn<BigDecimal> GNP = column("GNP", JDBCType.DECIMAL).withJavaProperty("GNP");

        public final SqlColumn<BigDecimal> GNPOld = column("GNPOld", JDBCType.DECIMAL).withJavaProperty("GNPOld");

        public final SqlColumn<String> localName = column("LocalName", JDBCType.CHAR).withJavaProperty("localName");

        public final SqlColumn<String> governmentForm = column("GovernmentForm", JDBCType.CHAR).withJavaProperty("governmentForm");

        public final SqlColumn<String> headOfState = column("HeadOfState", JDBCType.CHAR).withJavaProperty("headOfState");

        public final SqlColumn<Integer> capital = column("Capital", JDBCType.INTEGER).withJavaProperty("capital");

        public final SqlColumn<String> code2 = column("Code2", JDBCType.CHAR).withJavaProperty("code2");

        public Country() {
            super("country", Country::new);
        }
    }
}