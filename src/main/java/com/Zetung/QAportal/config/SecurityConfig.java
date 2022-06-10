package com.Zetung.QAportal.config;

import com.Zetung.QAportal.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

     @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
          http
                  .authorizeHttpRequests()
                  .antMatchers("/").permitAll()
                  .antMatchers(HttpMethod.GET, "/api/**").hasAnyRole(Role.USER.name())
                  .antMatchers(HttpMethod.POST, "/api/**").hasAnyRole(Role.USER.name())
                  .antMatchers(HttpMethod.DELETE, "/api/**").hasAnyRole(Role.USER.name())
                  .anyRequest()
                  .authenticated()
                  .and()
                  .httpBasic(withDefaults());
          return http.build();
     }

     protected UserDetailsService userDetailsService(){
          return null;
     }

     protected PasswordEncoder passwordEncoder(){
          return new BCryptPasswordEncoder(5);
     }

}
