package com.traveldiary.back.config;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.traveldiary.back.filter.JwtAuthenticationFilter;
import com.traveldiary.back.handler.OAuth2SuccessHandler;
import com.traveldiary.back.service.implementation.OAuth2UserServiceImplementation;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configurable
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final OAuth2UserServiceImplementation oAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
            .httpBasic(HttpBasicConfigurer::disable)
            .csrf(CsrfConfigurer::disable)
            .sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(request -> request
                .requestMatchers("/", "/traveldiary/v1/", "/traveldiary/v1/auth/*",
                    "/traveldiary/v1/image/file/*",
                    "/traveldiary/v1/restaurant/*",
                    "/traveldiary/v1/tour-attractions/*",
                    "/traveldiary/v1/review/*", "/traveldiary/v1/review/*/comment/list", "/traveldiary/v1/review/*/view-count",
                    "/traveldiary/v1/user/nick-name",
                    "/traveldiary/v1/qna/list").permitAll()
                .requestMatchers(HttpMethod.GET, "/traveldiary/v1/schedule/*").permitAll()
                .requestMatchers("/traveldiary/v1/qna/").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/traveldiary/v1/restaurant/*/recommend", "/traveldiary/v1/tour-attractions/*/recommend").hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/traveldiary/v1/review/", "/traveldiary/v1/review/my-list", "/traveldiary/v1/review/my-search").hasRole("USER")
                .requestMatchers(HttpMethod.PATCH, "/traveldiary/v1/qna/*",
                    "/traveldiary/v1/restaurant/*/recommend",
                    "/traveldiary/v1/tour-attractions/*/recommend",
                    "/traveldiary/v1/review/*",
                    "/traveldiary/v1/review/*/favorite",
                    "/traveldiary/v1/review/*/comment/*",
                    "/traveldiary/v1/user/edit",
                    "/traveldiary/v1/schedule/*").hasRole("USER")
                .requestMatchers(HttpMethod.PUT, "/traveldiary/v1/user/cancle-account").hasRole("USER")
                .requestMatchers(HttpMethod.DELETE, "/traveldiary/v1/qna/*", "/traveldiary/v1/schedule/*").hasRole("USER")
                .requestMatchers("/traveldiary/v1/address/*", "/traveldiary/v1/restaurant/", "/traveldiary/v1/tour-attractions").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/traveldiary/v1/user/list", "/traveldiary/v1/user/search").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/traveldiary/v1/qna/*/comment").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/traveldiary/v1/qna/*/comment", "/traveldiary/v1/restaurant/*", "/traveldiary/v1/tour-attractions/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/traveldiary/v1/qna/*/comment", "/traveldiary/v1/restaurant/*", 
                "/traveldiary/v1/tour-attractions/*",
                "/traveldiary/v1/user/list/cancle-account").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .authorizationEndpoint(endpoint -> endpoint.baseUri("/traveldiary/v1/auth/oauth2"))
                .redirectionEndpoint(endpoint -> endpoint.baseUri("/oauth2/callback/*"))
                .userInfoEndpoint(endpoint -> endpoint.userService(oAuth2UserService))
                .successHandler(oAuth2SuccessHandler)
            )
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(new AuthorizationFailedEntryPoint())
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();

    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
    
}

class AuthorizationFailedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN); 
        response.getWriter().write("{ \"code\":\"AF\",\"message\":\"Authorization Failed\" }");
        authException.printStackTrace();

    }
    
}
