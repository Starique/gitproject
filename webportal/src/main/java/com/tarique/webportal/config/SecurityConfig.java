package com.tarique.webportal.config;

import com.tarique.webportal.backend.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mehnuma on 1/8/2017.
 */
@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private Environment env;


    /**
     * the encryption salt
     */
    private static final String SALT = "123ewq!@#EWQMygoaloflifeistohelppeople123ewq!@#EWQ";

    /**
     * The larger the strength the program needs to work exponentially
     * for example 20 takes about 2 min to boot. The applications is noticeable slow
     * when it 15 though it only took 10 sec to boot. valur of 12 is a good balance
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    /**
     * Public URLs.
     */
    private static final String[] PUBLIC_MATCHERS = {
            "/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/",
            "/about/**",
            "/contact/**",
            "/error/**/*",
            "/console/**",
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**
         * This is needed for H2 console to work
         */
        List<String> activeprofiles = Arrays.asList(env.getActiveProfiles());
        if (activeprofiles.contains("dev")) {
            http.csrf().disable();
            http.headers().frameOptions().disable();
        }
        http
                .authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/payload")
                .failureUrl("/login?error").permitAll()
                .and()
                .logout().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user")
//                .password("password")
//                .roles("USER");
        auth
                .userDetailsService(userSecurityService)
                .passwordEncoder(passwordEncoder());
    }
}
