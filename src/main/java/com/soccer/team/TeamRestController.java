package com.soccer.team;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.soccer.team.bo.TeamBO;
import com.soccer.team.domain.Team;

@RestController
@RequestMapping("/team")
public class TeamRestController {

	@Autowired
	private TeamBO teamBO;
	
	/**
	 * 팀명 중복 검사
	 * @param teamName
	 * @return
	 */
	@RequestMapping("/is_duplicated_team")
	public Map<String, Object> isDuplicatedTeam(
			@RequestParam("teamName") String teamName){
		
		Map<String, Object> result = new HashMap<>();
		result.put("isDuplicationTeam", false);
		
		// db select
		Team team = teamBO.getTeamByName(teamName);
		if (team != null) {
			result.put("isDuplicationTeam", true);
		} 
		
		return result;
	}
	
	/**
	 * 팀만들기
	 * @param teamName
	 * @param skill
	 * @param activeArea
	 * @param introduce
	 * @param session
	 * @return
	 */
	@PostMapping("/create_team")
	public Map<String, Object> createTeam(
			@RequestParam("teamName") String teamName,
			@RequestParam("skill") String skill,
			@RequestParam("activeArea") String activeArea,
			@RequestParam(value = "introduce", required = false) String introduce,
			HttpSession session
			){
		
		Map<String, Object> result = new HashMap<>();
		
		// session 
		int leaderId = (int)session.getAttribute("userId");
		Integer userTeamId = (Integer)session.getAttribute("userTeamId");
		
		if (userTeamId == null) {
			// DB 조회 후  insert
			teamBO.addTeam(leaderId, teamName, skill, activeArea, introduce);
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("errorMessage", "이미 팀이 존재합니다.");
		}
		
		return result;
	}
	
	
	// 팀 수정 
	@PutMapping("/{teamId}")
	public Map<String, Object> updateTeam(
			@PathVariable int teamId,
			@RequestParam("teamName") String teamName,
			@RequestParam("skill") String skill,
			@RequestParam("activeArea") String activeArea,
			@RequestParam(value = "introduce", required = false) String introduce,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpSession session
			){
		
		
		//BO update
		teamBO.updateTeam(teamId, teamName, skill, activeArea, introduce, file);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		return result;
		
	}
	
	// 팀 삭제
	@DeleteMapping("/{teamId}")
	public Map<String, Object> deleteTeam(
			@PathVariable int teamId){
		
		
		// BO 넘겨서 삭제 
		/* ***********************  나중에 구현!!! *********************  */
		/* ***********************  나중에 구현!!! *********************  */
		/* ***********************  나중에 구현!!! *********************  */
		/* ***********************  나중에 구현!!! *********************  */
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
}











