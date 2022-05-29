package com.example.todoList.config;

import com.example.todoList.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

  private final UserService userService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf()
        .disable()
      .authorizeRequests()
      .antMatchers("/users/registration").not().fullyAuthenticated()
      .anyRequest().authenticated()
      .and()
      .formLogin()
      .defaultSuccessUrl("/tasks/all", true)
      .permitAll()
      .and()
      .logout()
      .logoutUrl("/logout")
      .permitAll()
      .and()
      .httpBasic();
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
  }
}
