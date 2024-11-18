package com.example.employeeservice.controller;

import com.example.employeeservice.entity.employee.Employee;
import com.example.employeeservice.service.EmployeeService;
import com.example.employeeservice.web.dto.employee.EmployeeDto;
import com.example.employeeservice.web.dto.validation.OnCreate;
import com.example.employeeservice.web.dto.validation.OnUpdate;
import com.example.employeeservice.web.mapper.EmployeeMapper;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
@Validated
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;

    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable Long id) {
        Employee employee = employeeService.getById(id);
        return employeeMapper.toDto(employee);
    }

    @PostMapping()
    public EmployeeDto create(@Validated({OnCreate.class}) @RequestBody EmployeeDto dto) {
        Employee employee = employeeMapper.toEntity(dto);
        Employee createdEmployee = employeeService.create(employee);
        return employeeMapper.toDto(createdEmployee);
    }

    @PutMapping("{id}")
    public EmployeeDto update(@Validated({OnUpdate.class}) @RequestBody EmployeeDto dto,
                              @PathParam("id") Long employeeId) {
        Employee employee = employeeMapper.toEntity(dto);
        employee.setId(employeeId);

        Employee updatedEmployee = employeeService.update(employee);
        return employeeMapper.toDto(updatedEmployee);
    }

    @PatchMapping("{eId}/team/{tId}")
    public ResponseEntity<?> assignEmployeeWithTeam(
            @PathVariable("eId") Long employeeId,
            @PathVariable("tId") Long teamId) {
        employeeService.assignEmployeeWithTeam(employeeId, teamId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        employeeService.delete(id);
    }

}
