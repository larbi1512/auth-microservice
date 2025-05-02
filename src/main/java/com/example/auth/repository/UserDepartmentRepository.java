package com.example.auth.repository;
import com.example.auth.entity.UserDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserDepartmentRepository extends JpaRepository<UserDepartment, Long> {
    List<UserDepartment> findByUserId(Long userId);

    void deleteByUserIdAndDepartmentId(Long userId, Long departmentId);
}