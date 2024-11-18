package com.example.employeeservice.security;

import com.example.employeeservice.entity.employee.Employee;
import com.example.employeeservice.entity.employee.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtEntityFactory {

    public static JwtEntity create(Employee employee) {
        return new JwtEntity(
                employee.getId(),
                employee.getUsername(),
                employee.getPassword(),
                mapToGrantedAuthority(new ArrayList<>(employee.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthority(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
