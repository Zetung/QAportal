package com.Zetung.QAportal.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

     private final UserDetailsService userDetailsService;

     public SecurityConfig(UserDetailsService userDetailsService){
          this.userDetailsService = userDetailsService;
     }
     @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
          http
                  .authorizeHttpRequests()
                    .antMatchers("/").permitAll()
                    .anyRequest()
                    .authenticated()
                  .and()
                    .formLogin()
                    .loginPage("/auth/login")
                    .permitAll()
                    .defaultSuccessUrl("/auth/success");
          return http.build();
     }

//     @Bean
//     public InMemoryUserDetailsManager userDetailsService() {
//          UserDetails user = User.builder()
//                  .username("user")
//                  .password(passwordEncoder().encode("user"))
//                  .authorities(Role.USER.getAuthorities())
//                  .build();
//          return new InMemoryUserDetailsManager(user);
//     }

     @Bean
     protected PasswordEncoder passwordEncoder(){
          return new BCryptPasswordEncoder(5);
     }

     @Bean
     protected DaoAuthenticationProvider daoAuthenticationProvider(){
          DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
          daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
          daoAuthenticationProvider.setUserDetailsService(userDetailsService);
          return daoAuthenticationProvider;

     }

}
