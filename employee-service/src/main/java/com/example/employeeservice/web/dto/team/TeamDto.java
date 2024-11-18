package com.example.employeeservice.web.dto.team;

import com.example.employeeservice.web.dto.validation.OnCreate;
import com.example.employeeservice.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class TeamDto {

    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Name must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Name length must be smaller than 255 symbols",
            groups = {OnUpdate.class, OnCreate.class})
    private String name;

}
