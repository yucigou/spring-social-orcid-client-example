package com.credera.springsocial;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import com.credera.user.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class MyWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String REMEMBER_SERVICE_KEY = "myUniqueApplicationKey";
    
    @Autowired
    DataSource dataSource;
    
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public TokenBasedRememberMeServices myRememberMeServices() {
        TokenBasedRememberMeServices service = new TokenBasedRememberMeServices(REMEMBER_SERVICE_KEY, userDetailsServiceImpl);
        service.setAlwaysRemember(true);
        service.setParameter("remember-me");
        return service;
    };
    
    /**
     * https://docs.spring.io/spring-security/site/docs/3.0.x/reference/remember-me.html
     * 
     * @return
     */
    /*
    @Bean
    public RememberMeAuthenticationProvider myRememberMeAuthenticationProvider() {
        RememberMeAuthenticationProvider provider = new RememberMeAuthenticationProvider(REMEMBER_SERVICE_KEY);
        return provider;
    }
    */
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
        // Spring Security should completely ignore URLs starting with /resources/
                .antMatchers("/resources/**");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/auth/login_check")
                .defaultSuccessUrl("/")
                .and()
            .rememberMe()
                .key(REMEMBER_SERVICE_KEY)
                .rememberMeServices(myRememberMeServices());
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

}