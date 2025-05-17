// package com.example.auth.repository;

// import com.example.auth.entity.UserDepartment;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;

// @DataJpaTest
// public class UserDepartmentRepositoryTest {

// @Autowired
// private UserDepartmentRepository userDepartmentRepository;

// @Test
// void findByUserId_success() {
// UserDepartment ud = new UserDepartment();
// ud.setUserId(1L);
// ud.setDepartmentId(1L);
// userDepartmentRepository.save(ud);

// List<UserDepartment> result = userDepartmentRepository.findByUserId(1L);
// assertFalse(result.isEmpty());
// assertEquals(1L, result.get(0).getDepartmentId());
// }

// @Test
// void deleteByUserIdAndDepartmentId_success() {
// UserDepartment ud = new UserDepartment();
// ud.setUserId(1L);
// ud.setDepartmentId(1L);
// userDepartmentRepository.save(ud);

// userDepartmentRepository.deleteByUserIdAndDepartmentId(1L, 1L);
// List<UserDepartment> result = userDepartmentRepository.findByUserId(1L);
// assertTrue(result.isEmpty());
// }
// }