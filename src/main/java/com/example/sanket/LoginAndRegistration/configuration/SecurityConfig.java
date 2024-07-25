package com.example.sanket.LoginAndRegistration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/*
	 Purpose: Configure Spring Security settings, including URL access rules, login processing, and authentication providers.
	 */
	
    @Bean
    public UserDetailsService getUserDetailsServices() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/user/**").hasRole("USER")
                    .requestMatchers("/**").permitAll()
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/signin")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/user/")
            ).logout(logout -> 
	            logout
		            .logoutUrl("/logout")
		            .logoutSuccessUrl("/signin?logout")
		            .invalidateHttpSession(true)
		            .deleteCookies("JSESSIONID")
            )
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider getDaoAuthProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getUserDetailsServices());
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationConfiguration authenticationConfiguration() {
        return new AuthenticationConfiguration();
    }
}




/*
 
 Configuration Class and Annotations:
@Configuration and @EnableWebSecurity: Marks this class as a configuration class and enables Spring Security's web security support.

UserDetailsService Bean:
getUserDetailsServices(): Creates a bean of UserDetailsServiceImpl to load user-specific data.

Password Encoder Bean:
getPasswordEncoder(): Creates a bean for BCryptPasswordEncoder to encrypt passwords.

Security Filter Chain:
securityFilterChain(HttpSecurity http): Configures security rules:
URL access rules based on roles.
Custom login page and processing URL.
Logout URL and success URL.
CSRF protection disabled (not recommended for production).

DaoAuthenticationProvider Bean:
getDaoAuthProvider(): Sets up an authentication provider with user details service and password encoder.
 
 */
