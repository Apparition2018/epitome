package springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;

/**
 * @author ljh
 * created on 2019/8/8 19:39
 */
@Configuration
@MapperScan(basePackages = "springboot.dao.demo", sqlSessionTemplateRef = "demoSqlSessionTemplate")
public class DemoDataSourceConfig {

    @Bean(name = "demoDataSource", destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.demo")
    @Primary
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "demoSqlSessionFactory")
    @Primary
    public SqlSessionFactory demoSqlSessionFactory(@Qualifier("demoDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(ResourceUtils.CLASSPATH_URL_PREFIX + "mapper/demo/*.xml"));
        bean.setTypeAliasesPackage("springboot.domain.demo");
        return bean.getObject();
    }

    @Bean(name = "demoTransactionManager")
    @Primary
    public DataSourceTransactionManager demoTransactionManager(@Qualifier("demoDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "demoSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate demoSqlSessionTemplate(@Qualifier("demoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

