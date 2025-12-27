package com.example.customers.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, KeycloakRoleConverter roleConverter) throws Exception {

		JwtAuthConverter jwtAuthConverter = new JwtAuthConverter(roleConverter);

		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/actuator/**").permitAll()
						.requestMatchers("/admin/**").hasRole("ADMIN").requestMatchers("/user/**")
						.hasAnyRole("USER", "ADMIN").anyRequest().authenticated())
				.oauth2ResourceServer(oauth -> oauth.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)));

		return http.build();
	}

}
