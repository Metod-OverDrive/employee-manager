package com.example.employeeservice.controller;

import com.example.employeeservice.entity.employee.Employee;
import com.example.employeeservice.entity.task.Task;
import com.example.employeeservice.entity.team.Team;
import com.example.employeeservice.service.EmployeeService;
import com.example.employeeservice.service.TaskService;
import com.example.employeeservice.service.TeamService;
import com.example.employeeservice.web.dto.employee.EmployeeDto;
import com.example.employeeservice.web.dto.task.TaskDto;
import com.example.employeeservice.web.dto.team.TeamDto;
import com.example.employeeservice.web.dto.validation.OnUpdate;
import com.example.employeeservice.web.mapper.EmployeeMapper;
import com.example.employeeservice.web.mapper.TaskMapper;
import com.example.employeeservice.web.mapper.TeamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
@Validated
public class TeamController {

    private final TeamService teamService;
    private final EmployeeService employeeService;
    private final TaskService taskService;

    private final TeamMapper teamMapper;
    private final EmployeeMapper employeeMapper;
    private final TaskMapper taskMapper;

    @GetMapping("/{id}")
    public TeamDto getById(@PathVariable Long id) {
        Team team = teamService.getById(id);
        return teamMapper.toDto(team);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskDto> getTasksByTeamId(@PathVariable Long id) {
        List<Task> tasks = taskService.getAllByDepartmentId(id);
        return taskMapper.toDto(tasks);
    }

    @GetMapping("/{id}/employees")
    public List<EmployeeDto> getEmployeesByTeamId(@PathVariable Long id) {
        List<Employee> employees = employeeService.getAllByDepartmentId(id);
        return employeeMapper.toDto(employees);
    }

    @PutMapping
    public TeamDto update(@Validated(OnUpdate.class)
                                    @RequestBody TeamDto dto) {
        Team team = teamMapper.toEntity(dto);
        Team updatedTeam = teamService.update(team);
        return teamMapper.toDto(updatedTeam);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable final Long id) {
        teamService.delete(id);
    }

}
