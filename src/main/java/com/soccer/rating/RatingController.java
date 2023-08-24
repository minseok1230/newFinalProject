package com.soccer.rating;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rating")
public class RatingController {

	
	// rating 화면
	@GetMapping("/rating_view")
	public String ratingView(Model model) {
		
		model.addAttribute("view", "rating/ratingCreate");
		return "template/layout";
	}
}
