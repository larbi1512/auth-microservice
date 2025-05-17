package com.example.auth.controller;

import com.example.auth.entity.User;
import com.example.auth.entity.UserDepartment;
import com.example.auth.repository.UserDepartmentRepository;
import com.example.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDepartmentRepository userDepartmentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User created");
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            return ResponseEntity.notFound().build();
        if (!user.getUsername().equals(updatedUser.getUsername()) &&
                userRepository.findByUsername(updatedUser.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        if (!user.getEmail().equals(updatedUser.getEmail()) &&
                userRepository.findByEmail(updatedUser.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        if (updatedUser.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        user.setRole(updatedUser.getRole());
        userRepository.save(user);
        return ResponseEntity.ok("User updated");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted");
    }

    @GetMapping("/{userId}/departments")
    public List<UserDepartment> getUserDepartments(@PathVariable Long userId) {
        return userDepartmentRepository.findByUserId(userId);
    }

    @PostMapping("/{userId}/departments")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> assignDepartment(@PathVariable Long userId,
            @RequestBody DepartmentAssignmentRequest request) {
        UserDepartment ud = new UserDepartment();
        ud.setUserId(userId);
        ud.setDepartmentId(request.getDepartmentId());
        userDepartmentRepository.save(ud);
        return ResponseEntity.ok("Department assigned");
    }

    @DeleteMapping("/{userId}/departments/{departmentId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> unassignDepartment(@PathVariable Long userId, @PathVariable Long departmentId) {
        userDepartmentRepository.deleteByUserIdAndDepartmentId(userId, departmentId);
        return ResponseEntity.ok("Department unassigned");
    }
}

class DepartmentAssignmentRequest {
    private Long departmentId;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}