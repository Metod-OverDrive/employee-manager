package com.example.employeeservice.web.mapper;

import com.example.employeeservice.entity.task.Task;
import com.example.employeeservice.web.dto.task.TaskDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper extends Mappable<Task, TaskDto> {

    List<TaskDto> toDto(List<Task> tasks);
}
