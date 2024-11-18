package com.example.employeeservice.repository;

import com.example.employeeservice.entity.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> findByNameLikeIgnoreCase(String name);

    @Query(value = """
        SELECT exists(
            SELECT 1
            FROM teams_employees
            WHERE team_id = :teamId
            AND employee_id = :employeeId)
        """, nativeQuery = true)
    boolean isEmployeeOwner(@Param("teamId") Long teamId, @Param("employeeId") Long employeeId);
}
