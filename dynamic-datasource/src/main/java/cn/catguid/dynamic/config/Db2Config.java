package cn.catguid.dynamic.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author liu.zhi
 * @date 2021/2/22 18:14
 */
@Configuration
@MapperScan(basePackages = {"cn.catguid.dynamic.mapper.db2"}, sqlSessionFactoryRef = "sqlSessionFactoryDb2")
public class Db2Config {

    @Autowired
    @Qualifier("db2")
    private DataSource dataSourceDb2;


    @Bean
    public SqlSessionFactory sqlSessionFactoryDb2() throws Exception {
        final MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceDb2);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:cn/catguid/dynamic/mapper/db2/*.xml"));
        //sessionFactory.setPlugins(new PaginationInterceptor());
        return sessionFactory.getObject();
        //SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        //factoryBean.setDataSource(dataSourceDb2);
        //factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:cn/catguid/dynamic/mapper/db2/*.xml"));
        //return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplateDb2() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactoryDb2());
    }

}
