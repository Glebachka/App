package com.saienko.cofigurations;

import com.saienko.cofigurations.converters.RoleConverter;
import com.saienko.cofigurations.converters.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.io.IOException;

/**
 * Created by gleb on 30.10.2015.
 */
@Configuration
@EnableWebMvc
@Import({SecurityConfig.class})
@ComponentScan(basePackages = "com.saienko")
public class AppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    RoleConverter roleConverter;

    @Autowired
    UserConverter userConverter;

    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(5242880);
        multipartResolver.setMaxInMemorySize(0);
        return multipartResolver;
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    @Bean
    public DriverManagerDataSource dataSourceAuth() {
        DriverManagerDataSource dataSourceAuth = new DriverManagerDataSource();
        return dataSourceAuth;
    }

    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(roleConverter);
        registry.addConverter(userConverter);
    }


}


