package com.saienko.cofigurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by gleb on 30.10.2015.
 */
@Configuration
@EnableWebMvc
@Import({SecurityConfig.class})
@ComponentScan (basePackages = "com.saienko")
public class AppConfig extends WebMvcConfigurerAdapter{

    @Autowired
    RoleConverter roleConverter;

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver =  new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public MessageSource messageSource () {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return  messageSource;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    @Bean
    public DriverManagerDataSource dataSourceAuth(){
        DriverManagerDataSource dataSourceAuth = new DriverManagerDataSource();
        return dataSourceAuth;
    }

    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(roleConverter);
    }
}
