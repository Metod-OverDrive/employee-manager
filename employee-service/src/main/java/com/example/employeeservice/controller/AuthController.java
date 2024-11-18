package com.example.employeeservice.controller;

import com.example.employeeservice.entity.employee.Employee;
import com.example.employeeservice.service.AuthService;
import com.example.employeeservice.service.EmployeeService;
import com.example.employeeservice.web.dto.auth.JwtResponse;
import com.example.employeeservice.web.dto.auth.LoginRequest;
import com.example.employeeservice.web.dto.auth.RefreshToken;
import com.example.employeeservice.web.dto.employee.EmployeeDto;
import com.example.employeeservice.web.dto.validation.OnCreate;
import com.example.employeeservice.web.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;
    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public EmployeeDto register(@Validated(OnCreate.class) @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        Employee createdEmployee = employeeService.create(employee);
        return employeeMapper.toDto(createdEmployee);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@Validated @RequestBody RefreshToken refreshToken) {
        return authService.refresh(refreshToken);
    }

}
