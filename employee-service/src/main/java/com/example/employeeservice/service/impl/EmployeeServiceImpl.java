package com.example.employeeservice.service.impl;

import com.example.employeeservice.entity.employee.Employee;
import com.example.employeeservice.entity.employee.Role;
import com.example.employeeservice.entity.team.Team;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.repository.RoleRepository;
import com.example.employeeservice.repository.TeamRepository;
import com.example.employeeservice.service.EmployeeService;
import com.example.employeeservice.service.TeamService;
import com.example.employeeservice.web.exception.ResourceNotFindException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TeamService teamService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFindException("Employee not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getByUsername(String username) {
        return employeeRepository.findByUsernameIgnoreCase(username).orElseThrow(() ->
                new ResourceNotFindException("Employee not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllByDepartmentId(Long id) {
        return employeeRepository.findAllByDepartmentId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployeeByTaskId(Long taskId) {
        return employeeRepository.findAllEmployeeByTaskId(taskId);
    }

    @Override
    @Transactional
    public Employee update(Employee employee) {
        Employee oldEmployee = employeeRepository.findById(employee.getId()).orElseThrow(() ->
                new ResourceNotFindException("Employee not found."));
        oldEmployee.setUsername(employee.getUsername());
        oldEmployee.setIsActive(employee.getIsActive());
        oldEmployee.setName(employee.getName());
        oldEmployee.setPhoneNumber(employee.getPhoneNumber());

        return employee;
    }

    @Override
    public void assignEmployeeWithTeam(Long employeeId, Long teamId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFindException("Employee not found."));
        Team team = teamService.getById(teamId);

        employeeRepository.assignEmployee(teamId, employeeId);
    }

    @Override
    @Transactional
    public Employee create(Employee employee) {
        if (employeeRepository.findByUsernameIgnoreCase(employee.getUsername()).isPresent()) {
            throw new IllegalStateException("Employee already exist.");
        }

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setIsActive(true);

        var updateEmployee = employeeRepository.save(employee);

        Role role = roleRepository.findByNameContainsIgnoreCase("EMPLOYEE");
        updateEmployee.setRoles(Set.of(role));

        log.info("Create Employee: id {}, name {}.", updateEmployee.getId(), updateEmployee.getName());

        return updateEmployee;
    }

    @Override
    @Transactional
    public void delete(Long employeeId) {
        employeeRepository.deleteById(employeeId);
        log.info("Delete Employee: id {}.", employeeId);
    }

}
