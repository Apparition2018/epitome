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
@MapperScan(basePackages = "springboot.dao.master", sqlSessionTemplateRef = "druidMasterSqlSessionTemplate")
public class DruidMasterDataSourceConfig {

    @Primary
    @Bean(name = "druidMasterDataSource", destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DruidDataSource druidMasterDataSource() {
        return new DruidDataSource();
    }

    @Primary
    @Bean(name = "druidMasterSqlSessionFactory")
    public SqlSessionFactory druidMasterSqlSessionFactory(@Qualifier("druidMasterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(ResourceUtils.CLASSPATH_URL_PREFIX + "mapper/master/*.xml"));
        bean.setTypeAliasesPackage("springboot.domain.master");
        return bean.getObject();
    }

    @Primary
    @Bean(name = "druidMasterTransactionManager")
    public DataSourceTransactionManager druidMasterTransactionManager(@Qualifier("druidMasterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "druidMasterSqlSessionTemplate")
    public SqlSessionTemplate druidMasterSqlSessionTemplate(@Qualifier("druidMasterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
