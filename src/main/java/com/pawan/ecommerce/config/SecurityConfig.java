package com.pawan.ecommerce.config;

import com.pawan.ecommerce.service.MyuserDetailsService;
import com.pawan.ecommerce.utils.JwtFilters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyuserDetailsService myuserDetailsService;

    @Autowired
    private JwtFilters  jwtFilters;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myuserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //cross site request forgery
        http.csrf().disable();

       // http.authorizeRequests().antMatchers("/api/user/**").permitAll().and().authorizeRequests().antMatchers("/api/product/*").authenticated();
       // http.authorizeRequests().antMatchers("/api/admin/**").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/api/user/**","/swagger-ui.html/**").permitAll().anyRequest().authenticated();
        //.anyRequest().authenticated()
        http.addFilterBefore(jwtFilters, UsernamePasswordAuthenticationFilter.class);
        http.cors();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
