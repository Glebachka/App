package com.saienko.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by gleb on 07.11.2015.
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //TODO: mistake can be called by using same datasource interface for userService and authentication
    @Autowired
    DataSource dataSourceAuth;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSourceAuth)
                .usersByUsernameQuery("select username, password, enabled form users where username=?")
                .authoritiesByUsernameQuery("select username,role from user_roles where username=?");
    }

    // Cross Site Request Forgery protect is realized in Spring
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/admin/**").access(("hasRole('ROLE_ADMIN')"))
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error")
                .usernameParameter("username").passwordParameter("userpassword")
                .and()
                .logout().logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling().accessDeniedPage("/403").and().csrf();

    }
}
