package com.dn.shoptech.security;
import javax.servlet.http.HttpServletResponse;

import com.dn.shoptech.repository.UserRepo;
import com.dn.shoptech.utils.jwt.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableGlobalMethodSecurity(
        prePostEnabled = true, securedEnabled = true, jsr250Enabled = true
)
@EnableWebSecurity
@RequiredArgsConstructor
public class WebConfigSecurity {
    @Autowired private UserRepo userRepo;
    @Autowired private JwtTokenFilter jwtTokenFilter;
    private AuthenticationConfiguration authConfig;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepo.findByUsername(username)
                        .orElseThrow(
                                () -> new UsernameNotFoundException("User " + username + " not found"));
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {

        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().and();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/api/v1/auth/**", "/api/v1/**").permitAll()
                .antMatchers("/api/v1/admin/**").authenticated()
                .antMatchers("/api/user/**").permitAll()
                .anyRequest().authenticated();

        http.exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                );

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}