package com.example.employeeservice.controller;

import com.example.employeeservice.entity.task.Task;
import com.example.employeeservice.service.TaskService;
import com.example.employeeservice.web.dto.task.TaskDto;
import com.example.employeeservice.web.dto.validation.OnCreate;
import com.example.employeeservice.web.dto.validation.OnUpdate;
import com.example.employeeservice.web.mapper.TaskMapper;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Validated
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping("{id}")
    public TaskDto getById(@PathVariable Long id) {
        Task task = taskService.getById(id);
        return taskMapper.toDto(task);
    }

    @GetMapping("team/{id}")
    public List<TaskDto> getByTeamId(@PathVariable Long id) {
        List<Task> tasks = taskService.getAllByDepartmentId(id);
        return taskMapper.toDto(tasks);
    }

    @PostMapping("team/{id}")
    public TaskDto create(@Validated(OnCreate.class)
                          @RequestBody TaskDto dto,
                          @PathParam("id") Long teamId) {
        Task task = taskMapper.toEntity(dto);
        Task updatedTask = taskService.create(task, teamId);
        return taskMapper.toDto(updatedTask);
    }

    @PutMapping
    public TaskDto update(@Validated(OnUpdate.class)
                          @RequestBody  TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task updatedTask = taskService.update(task);
        return taskMapper.toDto(updatedTask);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        taskService.delete(id);
    }

}
