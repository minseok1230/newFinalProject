package com.soccer.team.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.soccer.common.FileManagerService;
import com.soccer.match.dao.MatchMapper;
import com.soccer.match.domain.Match;
import com.soccer.reservation.bo.ReservationBO;
import com.soccer.reservation.domain.Reservation;
import com.soccer.team.dao.TeamMapper;
import com.soccer.team.dao.TeamRepository;
import com.soccer.team.domain.Team;
import com.soccer.team.domain.TeamView;
import com.soccer.team.entity.TeamEntity;
import com.soccer.user.bo.UserBO;
import com.soccer.user.domain.User;

@Service
public class TeamBO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TeamMapper teamMapper;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private ReservationBO reservationBO;
	
	@Autowired
	private MatchMapper matchMapper;
	
	@Autowired
	private FileManagerService fileManager;
	
	public Team getTeamByName(String name) {
		return teamMapper.selectTeamByName(name);
	}
	
	/* 팀 생성 */ 
	public User addTeam(int leaderId, String teamName, String skill, String activeArea, String introduce) {
		Map<String, Object> teamParameter = new HashMap<>();
		
		
		// DB select
		teamParameter.put("newTeamId", null);
		teamParameter.put("leaderId", leaderId);
		teamParameter.put("teamName", teamName);
		teamParameter.put("skill", skill);
		teamParameter.put("activeArea", activeArea);
		teamParameter.put("introduce", introduce);
		teamMapper.insertTeam(teamParameter);
		
		// DB insert
		Integer newTeamId = (Integer)teamParameter.get("newTeamId");
		String role = "팀장";
		userBO.updateUserByTeamIdAndRole(newTeamId, role, leaderId);
		
		User user = userBO.getUserById(leaderId);
		
		return user;
		
	}
	
	public List<Team> getTeam(){
		return teamMapper.selectTeam();
	}
	
	public TeamEntity getTeamById(Integer userTeamId) {
		return teamRepository.findAllById(userTeamId);
	}
	
	
	/* 팀 수정 (로고 사진도) */
	public void updateTeam(int teamId, String teamName, String skill, String activeArea, String introduce, MultipartFile file) {
		
		TeamEntity team = teamRepository.findAllById(teamId);
		if (team == null) {
			logger.warn("##########[팀 수정] team is null. teamId:{}", teamId);
			return;
		}
		
		String profileImagePath = null;
		if (file != null) {
			profileImagePath = fileManager.saveFile(teamId, file);
			
			if (profileImagePath != null && team.getProfileImagePath() != null) {
				fileManager.deleteFile(team.getProfileImagePath());
			}
		}
		
		// 3.글 업데이트
		teamMapper.updateTeamByTeamId(teamId, teamName, skill, activeArea, introduce, profileImagePath);
	}
	
	public List<TeamView> generateTeamViewList(){
		
		// 리턴 값 ( 여러개의 TeamView )
		List<TeamView> teamViewList = new ArrayList<>();
		
		List<Team> teamList = teamMapper.selectTeam();
		
		for (Team team : teamList) {
			TeamView teamView = new TeamView();
			
			// 팀 리더(주장 정보)
			User leader = userBO.getUserById(team.getLeaderId());
			teamView.setLeader(leader);
			
			// 팀 정보 
			teamView.setTeam(team);
			
			// 팀 경기장 정보
			List<Reservation> reservationList = reservationBO.getReservationByTeamId(team.getId());
			teamView.setReservationList(reservationList);
			
			// 팀 매칭 정보 
			List<Match> matchList = matchMapper.selectMatchByTeamId(team.getId());
			teamView.setMatchList(matchList);
			
			teamViewList.add(teamView);
		}
		
		return teamViewList;
	}
	
	
}











