
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Primary;
// import org.springframework.security.authentication.AuthenticationManager;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class TestSecurityConfig {

// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
// Exception {
// http
// .csrf().disable()
// .authorizeHttpRequests()
// .anyRequest().permitAll() // Allow all requests for testing
// .and()
// .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
// return http.build();
// }

// @Bean
// public PasswordEncoder passwordEncoder() {
// return new BCryptPasswordEncoder();
// }

// @Bean
// @Primary
// public UserDetailsService userDetailsService() {
// return new InMemoryUserDetailsManager(
// User.withUsername("admin")
// .password(passwordEncoder().encode("admin"))
// .roles("ADMIN")
// .build(),
// User.withUsername("user")
// .password(passwordEncoder().encode("user"))
// .roles("USER")
// .build()
// );
// }

// @Bean
// public AuthenticationManager authenticationManager(HttpSecurity http,
// UserDetailsService userDetailsService, PasswordEncoder passwordEncoder)
// throws Exception {
// return http.getSharedObject(AuthenticationManager.class);
// }
// }