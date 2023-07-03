package springboot.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.ResourceUtils;

import java.util.Objects;

/**
 * SlaverDataSourceConfig
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
@Configuration
@MapperScan(basePackages = "springboot.mapper.slaver", sqlSessionTemplateRef = "slaverSqlSessionTemplate")
public class SlaverDataSourceConfig {

    @Bean(name = "slaverDataSource", destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.slaver")
    public DruidDataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "slaverSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource());
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(ResourceUtils.CLASSPATH_URL_PREFIX + "mybatis/mapper/slaver/*.xml"));
        bean.setTypeAliasesPackage("springboot.domain.slaver");
        Objects.requireNonNull(bean.getObject()).getConfiguration().setMapUnderscoreToCamelCase(Boolean.TRUE);
        return bean.getObject();
    }

    @Bean(name = "slaverTransactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "slaverSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}
