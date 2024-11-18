package com.example.employeeservice.entity.team;

import com.example.employeeservice.entity.BaseClass;
import com.example.employeeservice.entity.employee.Employee;
import com.example.employeeservice.entity.task.Task;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "teams")
@Getter
@Setter
public class Team extends BaseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "teams_employees",
            joinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id")})
    private List<Employee> employees;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "team_tasks",
            joinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "task_id", referencedColumnName = "id")})
    private List<Task> tasks;
}
