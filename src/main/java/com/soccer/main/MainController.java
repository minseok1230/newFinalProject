package com.soccer.main;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soccer.board.bo.BoardBO;
import com.soccer.board.domain.Board;
import com.soccer.team.bo.TeamBO;
import com.soccer.team.entity.TeamEntity;
import com.soccer.user.bo.UserBO;
import com.soccer.user.domain.User;

@Controller
@RequestMapping("/main")
public class MainController {

	@Autowired
	private BoardBO boardBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private TeamBO teamBO;
	
	@RequestMapping("/main_view")
	public String mainView(Model model) {
		
		// 공지사항 DB 3개 가져오기
		List<Board> noticeList = boardBO.getBoardByTypeLimit("공지사항");
		
		// 게시판 DB  3개 가져오기 
		List<Board> boardList = boardBO.getBoardByTypeLimit("게시판");
		
		
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("boardList", boardList);
		model.addAttribute("view", "main/mainPage");
		return "template/layout";
	}
	
	@RequestMapping("/my_page_view")
	public String myPageView(Model model, HttpSession session) {
		
		int userId = (int)session.getAttribute("userId");
		int teamId = (int)session.getAttribute("userTeamId");
		User user = userBO.getUserById(userId);
		TeamEntity team = teamBO.getTeamById(teamId);
		
		
		model.addAttribute("user", user);
		model.addAttribute("team", team);
		model.addAttribute("view", "main/myPage");
		return "template/layout";
	}
}
