package org.ljh.mybatis.mapper.dynamic;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class CityDynamicSqlSupport {
    public static final City city = new City();

    public static final SqlColumn<Integer> id = city.id;

    public static final SqlColumn<String> name = city.name;

    public static final SqlColumn<String> countryCode = city.countryCode;

    public static final SqlColumn<String> district = city.district;

    public static final SqlColumn<Integer> population = city.population;

    public static final class City extends AliasableSqlTable<City> {
        public final SqlColumn<Integer> id = column("ID", JDBCType.INTEGER).withJavaProperty("id");

        public final SqlColumn<String> name = column("Name", JDBCType.CHAR).withJavaProperty("name");

        public final SqlColumn<String> countryCode = column("CountryCode", JDBCType.CHAR).withJavaProperty("countryCode");

        public final SqlColumn<String> district = column("District", JDBCType.CHAR).withJavaProperty("district");

        public final SqlColumn<Integer> population = column("Population", JDBCType.INTEGER).withJavaProperty("population");

        public City() {
            super("city", City::new);
        }
    }
}