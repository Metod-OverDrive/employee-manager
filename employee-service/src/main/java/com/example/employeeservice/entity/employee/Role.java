package com.example.employeeservice.entity.employee;

import com.example.employeeservice.entity.BaseClass;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role extends BaseClass {

    @Column(name = "name")
    private String name;
}
