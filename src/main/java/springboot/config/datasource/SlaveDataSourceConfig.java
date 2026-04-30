package springboot.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot4.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.ResourceUtils;

import java.util.Objects;

/**
 * Mybatis + Druid 多数据源配置
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
@Profile("druid")
@Configuration
@MapperScan(basePackages = "springboot.mapper.slave", sqlSessionTemplateRef = "slaveSqlSessionTemplate")
public class SlaveDataSourceConfig {

    @Bean(name = "slaveDataSource", destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DruidDataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "slaveTransactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource());
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(ResourceUtils.CLASSPATH_URL_PREFIX + "mybatis/mapper/slave/*.xml"));
        bean.setTypeAliasesPackage("springboot.domain.slave");
        Objects.requireNonNull(bean.getObject()).getConfiguration().setMapUnderscoreToCamelCase(Boolean.TRUE);
        return bean.getObject();
    }

    @Bean(name = "slaveSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}
