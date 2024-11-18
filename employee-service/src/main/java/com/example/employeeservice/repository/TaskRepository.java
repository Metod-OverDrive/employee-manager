package com.example.employeeservice.repository;

import com.example.employeeservice.entity.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Modifying
    @Query(value = """
        INSERT INTO teams_tasks (team_id, task_id)
        VALUES (:departmentId, :taskId)
        """, nativeQuery = true)
    void assignTask(@Param("departmentId") Long departmentId, @Param("taskId") Long taskId);

    @Query(value = """
        SELECT * FROM tasks t
        Join teams_tasks dt on t.id = dt.task_id
        where dt.team_id = :teamId
        """, nativeQuery = true)
    List<Task> findAllByDepartmentId(@Param("teamId") Long teamId);

    @Query(value = """
        SELECT * FROM tasks t
        WHERE t.expiration_data is not null
        AND t.expiration_data between :start and :end
        """, nativeQuery = true)
    List<Task> findAllSoonTasks(@Param("start") Timestamp start,
                                @Param("end") Timestamp end);
}
