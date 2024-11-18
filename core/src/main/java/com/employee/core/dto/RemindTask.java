package com.employee.core.dto;

import java.util.List;

public class RemindTask {

    private TaskRemind task;
    private List<EmployeeRemind> employees;

    public RemindTask() {
    }

    public RemindTask(TaskRemind task, List<EmployeeRemind> employees) {
        this.task = task;
        this.employees = employees;
    }

    public List<EmployeeRemind> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeRemind> employees) {
        this.employees = employees;
    }

    public TaskRemind getTask() {
        return task;
    }

    public void setTask(TaskRemind task) {
        this.task = task;
    }
}
