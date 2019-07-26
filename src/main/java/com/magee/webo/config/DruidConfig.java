package com.magee.webo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    //配置Druid的监控
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        //initParams.put("resetEnable", "false"); //禁用HTML页面上的“Rest All”功能
        initParams.put("loginUserename","magee");
        initParams.put("loginPassword", "123456");
        initParams.put("allowd","");          //默认拒绝访问所有访问
        initParams.put("deny","192.188.1.1");  //拒绝某些用户访问
        //配置初始化参数
        bean.setInitParameters(initParams);

        return bean;
    }

    //配置一个web监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());

        Map<String, String> initParams = new HashMap<>();
        //配置初始化参数
        initParams.put("exclusion", "*.js,*.css,/druid*");
        bean.setInitParameters(initParams);

        //设置拦截路径
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
    
}