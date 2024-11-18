package com.example.employeeservice.web.dto.employee;

import com.example.employeeservice.web.dto.validation.OnCreate;
import com.example.employeeservice.web.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class EmployeeDto {

    private Long id;

    @NotNull(message = "Name must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Name length must be smaller than 255 symbols",
            groups = {OnUpdate.class, OnCreate.class})
    private String name;

    @NotNull(message = "Username must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Username length must be smaller than 255 symbols",
            groups = {OnUpdate.class, OnCreate.class})
    private String username;

    @NotNull(message = "PhoneNumber must be not null", groups = {OnUpdate.class, OnCreate.class})
    private String phoneNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must be not null", groups = {OnUpdate.class, OnCreate.class})
    private String password;

    @NotNull(message = "Must be active or not", groups = {OnUpdate.class, OnCreate.class})
    private Boolean isActive;
}
