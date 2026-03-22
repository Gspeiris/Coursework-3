package lk.ijse.cmjd.researchtracker.config;

import lk.ijse.cmjd.researchtracker.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/api/auth/**")
                .permitAll()

                // Project endpoints
                .antMatchers(
                        "/api/projects"
                ).hasAnyAuthority(UserRole.ADMIN.name(), UserRole.PI.name(), UserRole.MEMBER.name(), UserRole.VIEWER.name())
                .antMatchers(
                        "/api/projects/{id}"
                ).hasAnyAuthority(UserRole.ADMIN.name(), UserRole.PI.name(), UserRole.MEMBER.name(), UserRole.VIEWER.name())
                .antMatchers(
                        "POST", "/api/projects"
                ).hasAnyAuthority(UserRole.ADMIN.name(), UserRole.PI.name())
                .antMatchers(
                        "PUT", "/api/projects/{id}"
                ).hasAnyAuthority(UserRole.ADMIN.name(), UserRole.PI.name())
                .antMatchers(
                        "PATCH", "/api/projects/{id}/status"
                ).hasAnyAuthority(UserRole.ADMIN.name(), UserRole.PI.name())
                .antMatchers(
                        "DELETE", "/api/projects/{id}"
                ).hasAuthority(UserRole.ADMIN.name())

                // Milestone endpoints
                .antMatchers(
                         "/api/projects/{id}/milestones"
                ).hasAnyAuthority(UserRole.ADMIN.name(), UserRole.PI.name(), UserRole.MEMBER.name())
                .antMatchers(
                        "POST", "/api/projects/{id}/milestones"
                ).hasAnyAuthority(UserRole.ADMIN.name(), UserRole.PI.name(), UserRole.MEMBER.name())
                .antMatchers(
                        "PUT", "/api/milestones/{id}"
                ).hasAnyAuthority(UserRole.ADMIN.name(), UserRole.PI.name(), UserRole.MEMBER.name())
                .antMatchers(
                        "DELETE", "/api/milestones/{id}"
                ).hasAnyAuthority(UserRole.ADMIN.name(), UserRole.PI.name())

                // Document endpoints
                .antMatchers(
                        "/api/projects/{id}/documents"
                ).hasAnyAuthority(UserRole.ADMIN.name(), UserRole.PI.name(), UserRole.MEMBER.name())
                .antMatchers(
                        "POST", "/api/projects/{id}/documents"
                ).hasAnyAuthority(UserRole.ADMIN.name(), UserRole.PI.name(), UserRole.MEMBER.name())
                .antMatchers(
                        "DELETE", "/api/documents/{id}"
                ).hasAnyAuthority(UserRole.ADMIN.name(), UserRole.PI.name())

                // User endpoints
                .antMatchers(
                        "/api/users"
                ).hasAuthority(UserRole.ADMIN.name())
                .antMatchers(
                        "/api/users/{id}"
                ).hasAuthority(UserRole.ADMIN.name())
                .antMatchers(
                        "DELETE", "/api/users/{id}"
                ).hasAuthority(UserRole.ADMIN.name())

                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
