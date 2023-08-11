package com.soccer.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

	@GetMapping("/member_list_view")
	public String memberListView(Model model) {
		
		model.addAttribute("view", "member/memberList");
		return "template/layout";
	}
}
