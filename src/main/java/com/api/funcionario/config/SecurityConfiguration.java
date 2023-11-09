package com.api.funcionario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(authorizeConfig -> {
			authorizeConfig.requestMatchers("/api/v1/funcionario/form").permitAll();
			authorizeConfig.anyRequest().authenticated();
		}).formLogin(Customizer.withDefaults()).build();
	}
}
