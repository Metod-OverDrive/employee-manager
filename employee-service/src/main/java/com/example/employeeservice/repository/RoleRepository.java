package com.example.employeeservice.repository;

import com.example.employeeservice.entity.employee.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByNameContainsIgnoreCase(String name);

}
