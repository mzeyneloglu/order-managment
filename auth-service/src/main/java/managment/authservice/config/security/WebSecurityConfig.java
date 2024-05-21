package managment.authservice.config.security;


import lombok.RequiredArgsConstructor;
import managment.authservice.constants.ApiEndpoints;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    public WebSecurityConfig(JWTAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests.
                        requestMatchers(ApiEndpoints.END_POINT+"/**", "/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated());
        return http.build();
    }

        @Bean
        public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws
        Exception {
            return authenticationConfiguration.getAuthenticationManager();
        }
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


