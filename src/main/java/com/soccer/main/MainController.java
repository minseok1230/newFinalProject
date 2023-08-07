package com.soccer.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soccer.board.bo.BoardBO;
import com.soccer.board.domain.Board;

@Controller
@RequestMapping("/main")
public class MainController {

	@Autowired
	private BoardBO boardBO;
	
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
}
