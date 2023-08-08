package com.soccer.team;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/team")
public class TeamController {

	
	@GetMapping("/team_create_view")
	public String teamCreateView(Model model) {
		
		model.addAttribute("view", "team/teamCreate");
		return "template/layout";
	}
}
