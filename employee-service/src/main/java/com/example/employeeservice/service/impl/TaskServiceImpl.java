package com.example.employeeservice.service.impl;

import com.example.employeeservice.entity.task.TaskStatus;
import com.example.employeeservice.entity.task.Task;
import com.example.employeeservice.entity.team.Team;
import com.example.employeeservice.repository.TaskRepository;
import com.example.employeeservice.service.TaskService;
import com.example.employeeservice.service.TeamService;
import com.example.employeeservice.web.exception.ResourceNotFindException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TeamService teamService;

    @Override
    @Transactional(readOnly = true)
    public Task getById(Long id) {
        return taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFindException("Task not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllByDepartmentId(Long id) {
        return taskRepository.findAllByDepartmentId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllSoonTasks(Duration duration) {
        LocalDateTime now = LocalDateTime.now();
        return taskRepository.findAllSoonTasks(Timestamp.valueOf(now),
                Timestamp.valueOf(now.plus(duration)));
    }

    @Override
    @Transactional
    public Task update(Task task) {
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task create(Task task, Long departmentId) {
        task.setStatus(TaskStatus.TODO);
        Task updateTask = taskRepository.save(task);

        Team team = teamService.getById(departmentId);
        taskRepository.assignTask(departmentId, updateTask.getId());
        return updateTask;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
