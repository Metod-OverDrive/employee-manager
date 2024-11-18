package com.example.employeeservice.service;

import com.employee.core.dto.RemindTask;

public interface KafkaSender {

    void sendTaskRemind(RemindTask remind);
}
