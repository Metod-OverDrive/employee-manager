package com.example.employeeservice.web.mapper;

import com.example.employeeservice.entity.team.Team;
import com.example.employeeservice.web.dto.team.TeamDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper extends Mappable<Team, TeamDto> {

}
