package com.example.employeeservice.web.mapper;

import com.example.employeeservice.entity.employee.Employee;
import com.example.employeeservice.web.dto.employee.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "roles", ignore = true)
    Employee toEntity(EmployeeDto employeeDto);

    EmployeeDto toDto(Employee employee);

    List<EmployeeDto> toDto(List<Employee> employees);

}
