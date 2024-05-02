package br.com.cairu.projeto.integrador.brecho.config.security;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${api.security.token.secret.public}")
    private RSAPublicKey secretPublic;

    @Value("${api.security.token.secret.private}")
    private RSAPrivateKey secretPriv;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.GET, "/user/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/user/all").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/user/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/user/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/user/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/category/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/category/all").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/category/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/category/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/category/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/product/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/product/all").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/product/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/product/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/product/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/product/category/{id}").permitAll()
                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .oauth2ResourceServer(
                        conf -> conf.jwt(Customizer.withDefaults()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
