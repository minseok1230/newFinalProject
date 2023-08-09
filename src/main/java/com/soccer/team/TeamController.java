package com.soccer.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soccer.team.bo.TeamBO;
import com.soccer.team.domain.Team;

@Controller
@RequestMapping("/team")
public class TeamController {

	
	@Autowired
	private TeamBO teamBO;
	
	@GetMapping("/team_create_view")
	public String teamCreateView(Model model) {
		
		model.addAttribute("view", "team/teamCreate");
		return "template/layout";
	}
	
	@GetMapping("/team_list_view")S
	public String teamListView(Model model) {
		
		// db 가져오기(teamlist)
		
		List<Team> teamList = teamBO.getTeam();
		
		
		
		model.addAttribute("view", "team/teamList");
		return "template/layout";
	}
}
