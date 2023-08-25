package com.soccer.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soccer.team.bo.TeamBO;
import com.soccer.team.entity.TeamEntity;

@Controller
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private TeamBO teamBO;
	
	// rating 화면
	@GetMapping("/rating_view")
	public String ratingView(
			@RequestParam("teamId") int teamId,
			@RequestParam("matchedTeamId") int matchedTeamId,
			@RequestParam("matchId") int matchId,
			Model model) {
		
		TeamEntity oppositeTeam = teamBO.getTeamById(matchedTeamId);
		
		model.addAttribute("oppositeTeam", oppositeTeam);
		model.addAttribute("teamId", teamId);
		model.addAttribute("matchId", matchId);
		model.addAttribute("view", "rating/ratingCreate");
		return "template/layout";
	}
}
