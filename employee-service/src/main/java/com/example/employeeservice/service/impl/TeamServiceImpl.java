package com.example.employeeservice.service.impl;

import com.example.employeeservice.entity.team.Team;
import com.example.employeeservice.repository.TeamRepository;
import com.example.employeeservice.service.TeamService;
import com.example.employeeservice.web.exception.ResourceNotFindException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    @Transactional(readOnly = true)
    public Team getById(Long id) {
        return teamRepository.findById(id).orElseThrow(() ->
                new ResourceNotFindException("Department not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public Team getByName(String name) {
        return teamRepository.findByNameLikeIgnoreCase(name).orElseThrow(() ->
                new ResourceNotFindException("Department not found."));
    }

    @Override
    @Transactional
    public Team update(Team department) {
        return teamRepository.save(department);
    }

    @Override
    @Transactional
    public Team create(Team department) {
        if (teamRepository.findByNameLikeIgnoreCase(department.getName()).isPresent()) {
            throw new IllegalStateException("Department already exists.");
        }
        teamRepository.save(department);
        return teamRepository.save(department);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isEmployeeFromDepartment(Long departmentId, Long employeeId) {
        return teamRepository.isEmployeeOwner(departmentId, employeeId);
    }

/*    @Override
    @Transactional(readOnly = true)
    public boolean isTaskFromDepartment(Long departmentId, Long taskId) {
        return teamRepository.isTaskOwner(departmentId, taskId);
    }*/

    @Override
    @Transactional(readOnly = true)
    public boolean isTaskFromDepartment(Long departmentId, Long taskId) {
        return false;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }
}
