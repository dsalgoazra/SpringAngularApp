package com.azra.demo.config;

import org.springframework.context.annotation.Configuration;
@Configuration
public class SecurityConfig { //extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("john").password("123").roles("USER");
//    }
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean()
//            throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().permitAll();
//    }
}