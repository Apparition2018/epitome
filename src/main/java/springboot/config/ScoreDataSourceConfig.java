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

/**
 * @author ljh
 * created on 2019/8/8 19:39
 */
@Configuration
@MapperScan(basePackages = "springboot.dao.score", sqlSessionTemplateRef = "scoreSqlSessionTemplate")
public class ScoreDataSourceConfig {

    @Bean(name = "scoreDataSource", destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.score")
    public DruidDataSource scoreDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "scoreSqlSessionFactory")
    public SqlSessionFactory scoreSqlSessionFactory(@Qualifier("scoreDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/score/*.xml"));
        bean.setTypeAliasesPackage("springboot.domain.score");
        return bean.getObject();
    }

    @Bean(name = "scoreTransactionManager")
    public DataSourceTransactionManager scoreTransactionManager(@Qualifier("scoreDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "scoreSqlSessionTemplate")
    public SqlSessionTemplate scoreSqlSessionTemplate(@Qualifier("scoreSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

