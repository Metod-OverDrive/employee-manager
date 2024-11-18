package com.example.employeeservice.service;

import com.example.employeeservice.entity.employee.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getById(Long id);
    Employee getByUsername(String username);
    List<Employee> getAllByDepartmentId(Long id);
    List<Employee> getAllEmployeeByTaskId(Long taskId);
    Employee update(Employee employee);
    void assignEmployeeWithTeam(Long employeeId, Long departmentId);
    Employee create(Employee employee);
    void delete(Long id);
}
