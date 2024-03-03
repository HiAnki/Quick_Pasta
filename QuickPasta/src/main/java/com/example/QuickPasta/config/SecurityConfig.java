package com.example.QuickPasta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("removal")
@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService getUserDetailService() {
        return new CustomUserDetailService();
    }

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/home/**")
                .permitAll()
                .requestMatchers("/customer/**")
                .hasRole("CUSTOMER")
                .requestMatchers("/restaurant/**")
                .hasRole("RESTAURANT")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/home/user/login")
                .defaultSuccessUrl("/home/")
                .failureUrl("/home/user/login?error=true")
                .permitAll()
                .and().logout().logoutSuccessUrl("/home/user/login?logout=true").invalidateHttpSession(true)
                .permitAll();
        return httpSecurity.build();
    }

    @Bean
    public DaoAuthenticationProvider getDaoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getUserDetailService());
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return daoAuthenticationProvider;
    }
}
