package com.univ.sohwakhaeng.global.config;

import com.univ.sohwakhaeng.auth.handler.CustomLogoutSuccessHandler;
import com.univ.sohwakhaeng.auth.jwt.JwtAccessDeniedHandler;
import com.univ.sohwakhaeng.auth.jwt.JwtAuthenticationEntryPoint;
import com.univ.sohwakhaeng.auth.jwt.JwtFilter;
import com.univ.sohwakhaeng.auth.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends Exception {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(c -> {
                            CorsConfigurationSource source = request -> {
                                var cors = new CorsConfiguration();
                                cors.addAllowedOriginPattern("*");
                                cors.setAllowedMethods(List.of("*"));
                                cors.setAllowedHeaders(List.of("*"));
                                cors.setAllowCredentials(true);
                                cors.setMaxAge(3600L);
                                return cors;
                            };
                            c.configurationSource(source);
                        }
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(FormLoginConfigurer::disable)
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()
                )
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(
                        (exception) -> exception
                                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                )
                .logout((logout) -> logout
                        .logoutUrl("/public/user/logout")
                        .logoutSuccessHandler(customLogoutSuccessHandler)
                );

        return http.build();
    }


}