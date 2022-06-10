package com.Zetung.QAportal.config;

import com.Zetung.QAportal.model.Permission;
import com.Zetung.QAportal.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

     @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
          http
                  .authorizeHttpRequests()
                  .antMatchers("/").permitAll()
//                  .antMatchers(HttpMethod.GET, "/api/**").hasAnyRole(Permission.USER_READ.getPermission())
//                  .antMatchers(HttpMethod.POST, "/api/**").hasAnyRole(Permission.USER_WRITE.getPermission())
//                  .antMatchers(HttpMethod.DELETE, "/api/**").hasAnyRole(Permission.USER_WRITE.getPermission())
                  .anyRequest()
                  .authenticated()
                  .and()
                  .httpBasic(withDefaults());
          return http.build();
     }

     @Bean
     public InMemoryUserDetailsManager userDetailsService() {
          UserDetails user = User.builder()
                  .username("user")
                  .password(passwordEncoder().encode("user"))
                  .authorities(Role.USER.getAuthorities())
                  .build();
          return new InMemoryUserDetailsManager(user);
     }

     protected PasswordEncoder passwordEncoder(){
          return new BCryptPasswordEncoder(5);
     }

}
