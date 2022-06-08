package com.appdeveloperblog.photoapp.users.PhotoAppDiscoveryUsers.security;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
http.csrf().disable();
http.authorizeRequests().antMatchers("/users/**").permitAll();
http.headers().frameOptions().disable();
return http.build();
}

}


// THIS IMPLEMENTATION IS NOT WORKING YET
// @Configuration
// @EnableWebSecurity
// public class WebSecurity {

//     private Environment environment;

//     @Autowired
//     public WebSecurity(Environment environment) {
//         this.environment = environment;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http.csrf().disable();
//         http.authorizeRequests().antMatchers("/**").hasIpAddress(environment.getProperty("gateway.ip"));
//         http.headers().frameOptions().disable();
//         return http.build();
//     }

// }