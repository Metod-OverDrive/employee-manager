package com.example.employeeservice.security;

import com.example.employeeservice.config.properties.JwtProperties;
import com.example.employeeservice.entity.employee.Employee;
import com.example.employeeservice.entity.employee.Role;
import com.example.employeeservice.service.EmployeeService;
import com.example.employeeservice.web.dto.auth.JwtResponse;
import com.example.employeeservice.web.exception.AccessDeniedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;
    private final JwtUserDetailsService jwtEntityService;
    private final EmployeeService employeeService;
    private Key key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }


    public String createAccessToken(Long userId, String username, Set<Role> roles){
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("id",  userId);
        claims.put("roles", resolveRoles(roles));
        Instant validity = Instant.now()
                .plus(jwtProperties.getAccess(), ChronoUnit.HOURS);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(Date.from(validity))
                .signWith(key)
                .compact();
    }

    public String createRefreshToken(Long userId, String username){
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("id", userId);
        Instant validity = Instant.now()
                .plus(jwtProperties.getRefresh(), ChronoUnit.DAYS);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(Date.from(validity))
                .signWith(key)
                .compact();
    }

    public JwtResponse refreshUserToken(String refreshToken){
        JwtResponse jwtResponse = new JwtResponse();
        if (!validateToken(refreshToken)){
            throw new AccessDeniedException("Token is not valid");
        }
        Long employeeId = Long.valueOf(getId(refreshToken));
        Employee employee = employeeService.getById(employeeId);
        jwtResponse.setId(employeeId);
        jwtResponse.setUsername(employee.getUsername());
        jwtResponse.setAccessToken(createAccessToken(employeeId, employee.getUsername(), employee.getRoles()));
        jwtResponse.setRefreshToken(createRefreshToken(employeeId, employee.getUsername()));
        return jwtResponse;
    }

    public Authentication getAuthentication(String token){
        String username = getUsername(token);
        UserDetails userDetails = jwtEntityService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(userDetails,"", userDetails.getAuthorities());
    }


    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException ignore) {
            return false;
        }
    }

    private String getId(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("id")
                .toString();
    }

    private String getUsername(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private List<String> resolveRoles(Set<Role> roles){
        return roles.stream().map(Role::getName).collect(Collectors.toList());
    }

}
