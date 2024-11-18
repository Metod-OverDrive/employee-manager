package com.example.employeeservice.service.impl;

import com.employee.core.dto.EmployeeRemind;
import com.employee.core.dto.RemindTask;
import com.employee.core.dto.TaskRemind;
import com.example.employeeservice.entity.employee.Employee;
import com.example.employeeservice.entity.task.Task;
import com.example.employeeservice.service.EmployeeService;
import com.example.employeeservice.service.KafkaSender;
import com.example.employeeservice.service.ReminderTaskService;
import com.example.employeeservice.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReminderTaskServiceImpl implements ReminderTaskService {

    private final static Duration REMIND_DURATION = Duration.ofDays(1);

    private final EmployeeService employeeService;
    private final TaskService taskService;
    private final KafkaSender kafkaSender;

    @Scheduled(cron = "30 * * * * *")
    @Override
    public void remindForTask() {
        List<Task> tasks = taskService.getAllSoonTasks(REMIND_DURATION);
        tasks.forEach(task -> {
            List<Employee> employees = employeeService.getAllEmployeeByTaskId(task.getId());
            RemindTask remind = getRemind(task, employees);
            kafkaSender.sendTaskRemind(remind);
        });
    }

    @Override
    public void testRemind() {

    }

    private RemindTask getRemind(Task task, List<Employee> employees) {
        TaskRemind taskRemind = new TaskRemind(
                task.getId(), task.getTitle(), task.getDescription(), task.getExpirationData());

        List<EmployeeRemind> employeeRemindList = employees.stream()
                .map(el -> new EmployeeRemind(el.getId(), el.getName(), el.getUsername())).toList();

        return new RemindTask(taskRemind, employeeRemindList);
    }
}
