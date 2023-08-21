package com.soccer.main;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soccer.board.bo.BoardBO;
import com.soccer.board.domain.Board;
import com.soccer.main.bo.MypageService;
import com.soccer.main.domain.MypageView;
import com.soccer.matchRelation.bo.MatchRelationService;
import com.soccer.matchRelation.domain.MatchRelationView;

@Controller
@RequestMapping("/main")
public class MainController {

	@Autowired
	private BoardBO boardBO;
	
	@Autowired
	private MypageService mypageService;
	
	
	@Autowired
	private MatchRelationService matchRelationService;
	/**
	 * 메인 페이지 
	 * @param model
	 * @return
	 */
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
	
	
	/* 마이 페이지 */
	@RequestMapping("/my_page_view")
	public String myPageView(Model model, HttpSession session) {
		
		Integer teamId = (Integer)session.getAttribute("userTeamId");
		int userId = (int)session.getAttribute("userId");
		String userRole = (String)session.getAttribute("userRole");
			
		MypageView myPageView = mypageService.generateMyPageView(userId, userRole);
		
		List<MatchRelationView> applyMatchRelationViewList = matchRelationService.generateApplyMatchRelationView(teamId);;
		List<MatchRelationView> applyedMatchRelationViewList = matchRelationService.generateApplyedMatchRelationView(teamId);
		List<MatchRelationView> doneMatchRelationViewList = matchRelationService.generateDoneMatchRelationView(teamId);
		
		model.addAttribute("doneMatchRelationViewList", doneMatchRelationViewList);
		model.addAttribute("applyedMatchRelationViewList", applyedMatchRelationViewList);
		model.addAttribute("applyMatchRelationViewList", applyMatchRelationViewList);
		model.addAttribute("myPageView", myPageView);
		model.addAttribute("view", "main/myPage");
		return "template/layout";
	}
}




