package com.example.employeeservice.entity.task;

import com.example.employeeservice.entity.BaseClass;
import com.example.employeeservice.entity.employee.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task extends BaseClass {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;

    @Column(name = "expiration_data")
    private LocalDateTime expirationData;
}
