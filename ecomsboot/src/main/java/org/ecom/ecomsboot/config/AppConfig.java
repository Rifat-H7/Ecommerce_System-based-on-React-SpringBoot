//package org.ecom.ecomsboot.config;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//
//import java.util.Arrays;
//
//@Configuration
//public class AppConfig {
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeHttpRequests(Authorize-> Authorize.requestMatchers("/api/**").authenticated().anyRequest().permitAll())
//                .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class).csrf().disable()
//                .cors().configurationSource(new CorsConfigurationSource(){
//                    @Override
//                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//                        CorsConfiguration cors = new CorsConfiguration();
//                        cors.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:3000"));
//                        cors.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//                        cors.setAllowedHeaders(Arrays.asList("*"));
//                        cors.setAllowCredentials(true);
//                        cors.setExposedHeaders(Arrays.asList("Authorization"));
//                        cors.setMaxAge(3600L);
//                        return cors;
//                    }
//                })
//        .and().httpBasic().and().formLogin();
//        return http.build();
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//}
