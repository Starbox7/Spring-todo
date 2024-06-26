package com.example.board.global.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  
  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
      .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()) //스프링과 관련된 모든것에게 서버에 대한 모든 요청 허용
      //.csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))); //h2 DB는 스프링이 아니기 때문에 별도로 혀용
      .headers((headers) -> headers.addHeaderWriter(new XFrameOptionsHeaderWriter(
        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN
      )))
      .formLogin((formLogin) -> formLogin.loginPage("/user/login").defaultSuccessUrl("/"));


        return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}


//010-2021-1779
//instar : @_ychan2