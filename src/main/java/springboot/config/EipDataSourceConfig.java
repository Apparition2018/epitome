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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "springboot.dao.eip", sqlSessionTemplateRef = "eipSqlSessionTemplate")
public class EipDataSourceConfig {

    @Bean(name = "eipDataSource", destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.eip")
    public DruidDataSource eipDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "eipSqlSessionFactory")
    public SqlSessionFactory eipSqlSessionFactory(@Qualifier("eipDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/eip/*.xml"));
        bean.setTypeAliasesPackage("springboot.domain.eip");
        return bean.getObject();
    }

    @Bean(name = "eipTransactionManager")
    public DataSourceTransactionManager eipTransactionManager(@Qualifier("eipDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "eipSqlSessionTemplate")
    public SqlSessionTemplate eipSqlSessionTemplate(@Qualifier("eipSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

