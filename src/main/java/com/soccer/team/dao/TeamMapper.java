package com.soccer.team.dao;

import org.springframework.stereotype.Repository;

import com.soccer.team.domain.Team;

@Repository
public interface TeamMapper {

	public Team selectTeamByName(String name);
}
