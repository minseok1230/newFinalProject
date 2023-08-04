package com.soccer.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {

	
	@RequestMapping("/main_view")
	public String mainView(Model model) {
		model.addAttribute("view", "main/mainPage");
		return "template/layout";
	}
}
