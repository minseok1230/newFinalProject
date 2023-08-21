package com.soccer.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soccer.member.bo.MemberService;
import com.soccer.member.domain.MemberView;
import com.soccer.user.bo.UserBO;
import com.soccer.user.domain.User;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private UserBO userBO;
	
	@GetMapping("/member_list_view")
	public String memberListView(
			@RequestParam("teamId") int teamId,
			Model model) {
		
		// db검색
		List<MemberView> memberViewList = memberService.generateMemberViewByApproval(teamId, true);
		User leader = userBO.getUserByTeamIdAndRole(teamId, "팀장");
		
		model.addAttribute("leader", leader);
		model.addAttribute("memberViewList", memberViewList);
		model.addAttribute("view", "member/memberList");
		return "template/layout";
	}
}
