package com.example.employeeservice.service;

import com.example.employeeservice.entity.team.Team;

public interface TeamService {

    Team getById(Long id);
    Team getByName(String name);
    Team update(Team department);
    Team create(Team department);
    boolean isEmployeeFromDepartment(Long departmentId, Long employeeId);
    boolean isTaskFromDepartment(Long departmentId, Long taskId);
    void delete(Long id);
}