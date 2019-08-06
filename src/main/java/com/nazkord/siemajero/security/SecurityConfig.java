package com.nazkord.siemajero.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(encoder().encode("adminPass")).roles(String.valueOf(Role.ADMIN))
                .and()
                .withUser("user").password(encoder().encode("userPass")).roles(String.valueOf(Role.USER));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers("/bets/**", "/matches/**").hasAnyRole(String.valueOf(Role.ADMIN), String.valueOf(Role.USER))
            .antMatchers("/users/**").hasRole(String.valueOf(Role.ADMIN))
            .and()
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
            .logout();
    }

    //TODO: logout!!!

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}

