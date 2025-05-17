// package com.example.auth.security;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.test.util.ReflectionTestUtils;

// import java.util.Collections;

// import static org.junit.jupiter.api.Assertions.*;

// @SpringBootTest
// public class JwtUtilTest {

//     @Autowired
//     private JwtUtil jwtUtil;

//     private UserDetails userDetails;

//     @BeforeEach
//     void setUp() {
//         ReflectionTestUtils.setField(jwtUtil, "secret", "testsecretkeytestsecretkeytestsecretkey");
//         ReflectionTestUtils.setField(jwtUtil, "expiration", 3600000); // 1 hour
//         userDetails = new User("testuser", "password",
//                 Collections.singletonList(
//                         new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_USER")));
//     }

//     @Test
//     void generateToken_success() {
//         String token = jwtUtil.generateToken(userDetails, 1L);
//         assertNotNull(token);
//         assertEquals("testuser", jwtUtil.getUsernameFromToken(token));
//         assertEquals(1L, jwtUtil.getUserIdFromToken(token));
//     }

//     @Test
//     void validateToken_success() {
//         String token = jwtUtil.generateToken(userDetails, 1L);
//         assertTrue(jwtUtil.validateToken(token, userDetails));
//     }

//     @Test
//     void validateToken_invalidUsername() {
//         String token = jwtUtil.generateToken(userDetails, 1L);
//         UserDetails wrongUser = new User("wronguser", "password", Collections.emptyList());
//         assertFalse(jwtUtil.validateToken(token, wrongUser));
//     }

//     @Test
//     void validateToken_expired() throws InterruptedException {
//         ReflectionTestUtils.setField(jwtUtil, "expiration", 1); // Set expiration to 1ms
//         String token = jwtUtil.generateToken(userDetails, 1L);
//         Thread.sleep(2); // Wait for token to expire
//         assertFalse(jwtUtil.validateToken(token, userDetails));
//     }
// }