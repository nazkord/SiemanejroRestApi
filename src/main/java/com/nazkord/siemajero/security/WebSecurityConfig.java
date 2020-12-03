package com.nazkord.siemajero.security;

import com.nazkord.siemajero.controllers.HealthCheckController;
import com.nazkord.siemajero.controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource betServiceDataSource;

    @Autowired
    public WebSecurityConfig(DataSource betServiceDataSource) {
        this.betServiceDataSource = betServiceDataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(UserController.TOKEN_LOGIN, HealthCheckController.HEALTH_CHECK).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                jdbcAuthentication().dataSource(betServiceDataSource)
                //TODO: this should be done by token !
                .usersByUsernameQuery("select name,password, 1 enabled from users where name=?")
                .authoritiesByUsernameQuery("select name, roleName from users where name=?");
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}


