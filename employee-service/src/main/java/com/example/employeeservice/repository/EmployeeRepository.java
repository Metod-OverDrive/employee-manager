package com.example.employeeservice.repository;

import com.example.employeeservice.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByUsernameIgnoreCase(String username);

    @Modifying
    @Query(value = """
        INSERT INTO teams_employees (team_id, employee_id)
        VALUES (:teamId, :employeeId)
        """, nativeQuery = true)
    void assignEmployee(@Param("teamId") Long teamId, @Param("employeeId") Long employeeId);

    @Query(value = """
        SELECT * FROM employees e
        JOIN teams_employees te on e.id = te.employee_id
        where te.team_id = :teamId
        """, nativeQuery = true)
    List<Employee> findAllByDepartmentId(@Param("teamId") Long teamId); // ToDo: Зачем это вообще здесь?


    @Query(value = """
        Select * FROM employees e
        JOIN teams_employees te on e.id = te.employee_id
        JOIN teams_tasks tt on te.team_id = tt.task_id
        WHERE tt.task_id = :taskId
        """, nativeQuery = true)
    List<Employee> findAllEmployeeByTaskId(@Param("taskId") Long taskId); // ToDo: Maybe, So So.
}
