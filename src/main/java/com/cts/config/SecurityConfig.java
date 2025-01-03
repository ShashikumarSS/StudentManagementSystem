package com.cts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cts.filter.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig 
{
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
   	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
   	{
   		return http.csrf(csrf->csrf.disable())
   		 		.authorizeHttpRequests((authorize)->authorize
   		 			.requestMatchers("/user/**").permitAll()
   		 			.requestMatchers("/authenticate").permitAll()
   		 			.requestMatchers("/error").permitAll()
   				.anyRequest().authenticated())
   		 		.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
   		 		.httpBasic(Customizer.withDefaults())
   		 		.build();
   	}

    @Bean 
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    	return configuration.getAuthenticationManager();
    }

}
