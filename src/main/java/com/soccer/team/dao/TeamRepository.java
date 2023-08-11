package com.soccer.team.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soccer.team.entity.TeamEntity;

@Repository
public interface TeamRepository  extends JpaRepository<TeamEntity, Integer>{

		// 수정할 나의 팀 가져오기 
		public TeamEntity findAllById(int userTeamId);
}
