package com.greger.inviduelluppg.securtiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    //    *********** Egna uppgifter ************

//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource){
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT id, password, active FROM staff WHERE id=?");
//        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT id, role FROM roles WHERE id=?");
//
//        return jdbcUserDetailsManager;
//    }


//    *********** Standard Spring auto uppgifter ************
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }


//    *********** Hårdkodade uppgifter ************
//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){ //Har högre prioritet än application.properties
//        UserDetails tiger = User.builder()
//                .username("tiger")
//                .password("{noop}tass")
//                .roles("USER")
//                .build();
//
//        UserDetails jerry = User.builder()
//                .username("jerry")
//                .password("{noop}tass")
//                .roles("USER", "ADMIN")
//                .build();
//
//        UserDetails tiffany = User.builder()
//                .username("tiffany")
//                .password("{noop}tass")
//                .roles("USER", "ADMIN", "GOD")
//                .build();
//        return new InMemoryUserDetailsManager(tiger, jerry, tiffany);
//    }


    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(mvc.pattern("/mypages/**")).hasRole("USER")
//                        .requestMatchers(mvc.pattern( "/mypages/members/**")).hasRole("USER")
                        .requestMatchers(mvc.pattern("/admin/**")).hasRole("ADMIN")
//                        .requestMatchers(mvc.pattern(HttpMethod.POST,"/admin/members")).hasRole("ADMIN")
//                        .requestMatchers(mvc.pattern(HttpMethod.PUT,"/admin/members")).hasRole("GOD")
//                        .requestMatchers(mvc.pattern("/admin/members/**")).hasRole("GOD")
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf
                .ignoringRequestMatchers(mvc.pattern("/h2-console/**"))
                .disable());
        //Raden nedan enbart för att göra h2 databasen synlig
        http.headers().frameOptions().sameOrigin();
        return http.build();
    }
}
