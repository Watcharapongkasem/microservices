package microservices.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import microservices.demo.jwt.JwtAuthFilter;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private JwtAuthFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes().route("auth", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("lb://auth"))
                .route("product", r -> r.path("/product/**").filters(f -> f.filter(filter)).uri("lb://product"))
                .route("order", r -> r.path("/order/**").filters(f -> f.filter(filter)).uri("lb://order"))
                .route("checkout", r -> r.path("/checkout/**").filters(f -> f.filter(filter)).uri("lb://checkout"))
                .build();
    }

    // @Bean
    // public BCryptPasswordEncoder passwordEncoder() {
    // return new BCryptPasswordEncoder();
    // }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        CorsConfiguration corsConfiguration = new CorsConfiguration();
         
         corsConfiguration.setAllowedHeaders(List.of("*"));
         corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
         corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "HEAD", "CONNECT", "OPTIONS", "TRACE"));
         corsConfiguration.setAllowCredentials(true);
         corsConfiguration.setExposedHeaders(List.of("Authorization"));
         
        http
            // ...
            .cors()
            .configurationSource(source-> corsConfiguration)
            .and()
            .csrf(csrf -> csrf.disable());
        return http.build();
    }
    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // http
    // .authorizeRequests()
    // .antMatchers("/auth/**").permitAll()
    // .anyRequest().authenticated();

    // return http.build();
    // }

    // @Bean
    // public WebSecurityCustomizer webSecurityCustomizer() {
    // return (web) -> web.ignoring().antMatchers("/auth", "/localhost:8761");
    // }

}