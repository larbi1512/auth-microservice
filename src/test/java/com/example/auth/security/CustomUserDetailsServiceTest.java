// package com.example.auth.security;

// import com.example.auth.entity.User;
// import com.example.auth.repository.UserRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class)
// public class CustomUserDetailsServiceTest {

//     @Mock
//     private UserRepository userRepository;

//     @InjectMocks
//     private CustomUserDetailsService userDetailsService;

//     private User user;

//     @BeforeEach
//     void setUp() {
//         user = new User();
//         user.setUsername("testuser");
//         user.setPassword("encodedPassword");
//         user.setRole("USER");
//     }

//     @Test
//     void loadUserByUsername_success() {
//         when(userRepository.findByUsername("testuser")).thenReturn(user);

//         UserDetails userDetails = userDetailsService.loadUserByUsername("testuser");

//         assertNotNull(userDetails);
//         assertEquals("testuser", userDetails.getUsername());
//         assertEquals("encodedPassword", userDetails.getPassword());
//         assertTrue(userDetails.getAuthorities().stream()
//                 .anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
//     }

//     @Test
//     void loadUserByUsername_userNotFound() {
//         when(userRepository.findByUsername("testuser")).thenReturn(null);

//         assertThrows(UsernameNotFoundException.class, () -> {
//             userDetailsService.loadUserByUsername("testuser");
//         });
//     }
// }