package org.example.exoreservationsalles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/api/rooms/test").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/rooms").authenticated()
                        .anyExchange().hasRole("ADMIN")
                )
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(authenticationEntryPoint())
                        .accessDeniedHandler(accessDeniedHandler())
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .build();
    }

    @Bean
    public ServerAuthenticationEntryPoint authenticationEntryPoint() {
        return (exchange, ex) -> {
            String message = "Vous devez vous authentifier pour effectuer cette action";
            return exchange.getResponse().writeWith(Mono.fromSupplier(() -> {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                exchange.getResponse().getHeaders().add("Content-Type", "application/json");
                byte[] bytes = String.format("{\"error\" : \"UNAUTHORIZED\"\n\t\"message\" : \"%s\"}",message).getBytes();
                return exchange.getResponse().bufferFactory().wrap(bytes);
            }));
        };
    }

    @Bean
    public ServerAccessDeniedHandler accessDeniedHandler() {
        return (exchange, denied) -> {
            String message = "Vous n'avez pas l'autorisation d'effectuer cette action";
            return exchange.getResponse().writeWith(Mono.fromSupplier(() -> {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                exchange.getResponse().getHeaders().add("Content-Type", "application/json");
                byte[] bytes = String.format("{\"error\" : \"FORBIDDEN\"\n\t\"message\" : \"%s\"}",message).getBytes();
                return exchange.getResponse().bufferFactory().wrap(bytes);
            }));
        };
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails clement = User.withDefaultPasswordEncoder()
                .username("Clément")
                .password("1234")
                .roles("USER")
                .build();

        UserDetails nassim = User.withDefaultPasswordEncoder()
                .username("Nassim")
                .password("5678")
                .roles("USER")
                .build();

        UserDetails christophe = User.withDefaultPasswordEncoder()
                .username("Christophe")
                .password("password")
                .roles("ADMIN")
                .build();

        return new MapReactiveUserDetailsService(clement, nassim, christophe);
    }
}
