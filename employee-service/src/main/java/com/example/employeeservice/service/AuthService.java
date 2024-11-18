package com.example.employeeservice.service;

import com.example.employeeservice.web.dto.auth.JwtResponse;
import com.example.employeeservice.web.dto.auth.LoginRequest;
import com.example.employeeservice.web.dto.auth.RefreshToken;

public interface AuthService {

    JwtResponse login(LoginRequest request);

    JwtResponse refresh(RefreshToken refreshToken);
}
