package com.nazkord.siemajero.security;

import com.nazkord.siemajero.repositories.RepoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource betServiceDataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                jdbcAuthentication().dataSource(betServiceDataSource)
                .usersByUsernameQuery("select name,password, 1 enabled from USER where name=?")
                .authoritiesByUsernameQuery("select name, roleName from USER where name=?");




//                inMemoryAuthentication()
//                .withUser("admin").password(encoder().encode("adminPass")).roles(String.valueOf(Role.ADMIN))
//                .and()
//                .withUser("user").password(encoder().encode("userPass")).roles(String.valueOf(Role.USER));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }

    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .httpBasic()
//            .and()
//            .authorizeRequests()
//            .antMatchers("/bets/**", "/matches/**").hasAnyRole(String.valueOf(Role.ADMIN), String.valueOf(Role.USER))
//            .antMatchers("/users/**").hasRole(String.valueOf(Role.ADMIN))
//            .and()
//            .csrf().disable()
//            .headers().frameOptions().disable()
//            .and()
//            .logout();
//    }

    //TODO: logout!!!

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}


