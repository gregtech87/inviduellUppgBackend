//package com.greger.inviduelluppg.securtiy;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
//import javax.sql.DataSource;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }
//
//    @Bean
//    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspect) {
//        return new MvcRequestMatcher.Builder(introspect);
//    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
//        http.authorizeHttpRequests(configurer ->
//                configurer
//                        //Rad 1 för att göra h2 databasen synlig
//                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
//                        .requestMatchers(mvc.pattern("/mypages/**")).hasRole("USER")
//                        .requestMatchers(mvc.pattern("/admin/**")).hasRole("ADMIN")
//        );
//        http.httpBasic(Customizer.withDefaults());
//        http.csrf(csrf -> csrf
//                //Rad 2 för att göra h2 databasen synlig
//                .ignoringRequestMatchers(mvc.pattern("/h2-console/**"))
//                .disable());
//        //Rad 3 för att göra h2 databasen synlig
//        http.headers().frameOptions().sameOrigin();
//        return http.build();
//    }
//}
