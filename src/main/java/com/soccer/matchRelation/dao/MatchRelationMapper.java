package com.soccer.matchRelation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.soccer.matchRelation.domain.MatchRelation;

@Repository
public interface MatchRelationMapper {

	public MatchRelation selectMatchRelationById(int id);
	
	public MatchRelation selectMatchRelationByMatchIdAndState(
			@Param("matchId") int matchId,
			@Param("state") String state);
	
	public MatchRelation selectMatchRelationByTeamIdMatchIdMatchedTeamId(
			@Param("teamId") int teamId, 
			@Param("matchId") int matchId, 
			@Param("matchedTeamId") int matchedTeamId, 
			@Param("state") String state);
	
	public List<MatchRelation> selectMatchRelationByMatchedTeamId(
			@Param("matchedTeamId") int matchedTeamId,
			@Param("state") String state);
	
	public List<MatchRelation> selectMatchRelationByTeamId(
			@Param("teamId") int teamId,
			@Param("state") String state);
	
	public List<MatchRelation> selectMatchRelationByTeamIdAndMatchedTeamId(
			@Param("teamId") int teamId, 
			@Param("state") String state);
	
	public int insertMatchRelationByTeamIdMatchIdMatchedTeamId(
			@Param("teamId") int teamId, 
			@Param("matchId") int matchId, 
			@Param("matchedTeamId") int matchedTeamId,
			@Param("state") String state
			);
	
	public int updateMatchRelationByIdState(
			@Param("id") int id, 
			@Param("state") String state);
	
	
	public void deleteMatchRelationByMatchId(int matchId);
	
	public void deleteMatchRelationMatchIdAndTeamIdAndMatchedTeamId(
			@Param("matchId") int matchId, 
			@Param("teamId") int teamId, 
			@Param("matchedTeamId") int matchedTeamId);
}





