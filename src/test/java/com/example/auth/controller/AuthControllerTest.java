// package com.example.auth.controller;

// import com.example.auth.controller.AuthController;
// import com.example.auth.entity.User;
// import com.example.auth.repository.UserRepository;
// import com.example.auth.security.JwtUtil;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.test.web.servlet.MockMvc;

// import java.util.Collections;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @SpringBootTest
// @AutoConfigureMockMvc
// public class AuthControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private UserRepository userRepository;

//     @MockBean
//     private JwtUtil jwtUtil;

//     @MockBean
//     private AuthenticationManager authenticationManager;

//     @Autowired
//     private ObjectMapper objectMapper;

//     private User user;

//     @BeforeEach
//     void setUp() {
//         user = new User();
//         user.setId(1L);
//         user.setUsername("testuser");
//         user.setPassword("encodedPassword");
//         user.setRole("USER");
//     }

//     @Test
//     void registerUser_success() throws Exception {
//         when(userRepository.findByUsername("testuser")).thenReturn(null);
//         when(userRepository.save(any(User.class))).thenReturn(user);

//         mockMvc.perform(post("/auth/register")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(user)))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$").value("User registered"));

//         verify(userRepository).save(any(User.class));
//     }

//     @Test
//     void registerUser_usernameExists() throws Exception {
//         when(userRepository.findByUsername("testuser")).thenReturn(user);

//         mockMvc.perform(post("/auth/register")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(user)))
//                 .andExpect(status().isBadRequest())
//                 .andExpect(jsonPath("$").value("Username already exists"));
//     }

//     @Test
//     void login_success() throws Exception {
//         AuthController.LoginRequest loginRequest = new AuthController.LoginRequest();
//         loginRequest.setUsername("testuser");
//         loginRequest.setPassword("password");

//         when(userRepository.findByUsername("testuser")).thenReturn(user);
//         when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
//                 .thenReturn(new UsernamePasswordAuthenticationToken("testuser", "password", Collections.emptyList()));
//         when(jwtUtil.generateToken(any(), eq(1L), null)).thenReturn("jwt-token");

//         mockMvc.perform(post("/auth/login")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(loginRequest)))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.token").value("jwt-token"));
//     }

//     @Test
//     void login_invalidCredentials() throws Exception {
//         AuthController.LoginRequest loginRequest = new AuthController.LoginRequest();
//         loginRequest.setUsername("testuser");
//         loginRequest.setPassword("wrong");

//         when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
//                 .thenThrow(new org.springframework.security.authentication.BadCredentialsException("Bad credentials"));

//         mockMvc.perform(post("/auth/login")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(loginRequest)))
//                 .andExpect(status().isBadRequest())
//                 .andExpect(jsonPath("$").value("Invalid username or password"));
//     }
// }