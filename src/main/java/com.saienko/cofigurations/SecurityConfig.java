package com.saienko.cofigurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * Created by gleb on 07.11.2015.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    AppSuccessHandler appSuccessHandler;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/user/**").access("hasRole('USER') or hasRole('ADMIN') or hasRole('USER')")
//                .antMatchers("/admin/**").access("hasRole('ADMIN') or hasRole('DBA')")
//                .antMatchers("/dba/**").access("hasRole('DBA')")

//                .anyRequest().authenticated()
//                .and().formLogin().loginPage("/login").successHandler(appSuccessHandler)
//
//                .usernameParameter("userLogin").passwordParameter("password")
//                .and().csrf()
//                .and().exceptionHandling().accessDeniedPage("/accessDenied");
        .anyRequest().authenticated().and().csrf().disable().formLogin().and().httpBasic();

    }
}
