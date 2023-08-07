package com.soccer.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soccer.board.bo.BoardBO;
import com.soccer.board.domain.Board;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardBO boardBO;
	
	// 글 목록 (더보기 클릭)
	@GetMapping("/board_list_view")
	public String boardListView(
			@RequestParam("type") String type,
			Model model) {
		
		// DB 글목록 조회 (조건 : type)
		String boardType = type;
		List<Board> boardList = boardBO.getBoardByType(type);
		
		
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardList", boardList);
		model.addAttribute("view", "board/boardList");
		return "template/layout";
	}
	
	
	// 글쓰기 (글 목록에서 글쓰기 클릭)
	@GetMapping("/board_create_view")
	public String boardCreateView(
			@RequestParam("type") String type,
			Model model) {
		
		String boardType = type;
		
		model.addAttribute("boardType", boardType);
		model.addAttribute("view", "board/boardCreate");
		return "template/layout";
	}
	
	
	
	//상세페이지
	@GetMapping("/board_detail_view")
	public String boardDetailView(
			@RequestParam("boardId") int boardId,
			HttpSession session,
			Model model) {
		
		// session
		int userId = (int)session.getAttribute("userId");
		
		// db select
		Board boardDetail = boardBO.getBoardById(boardId);
		
		model.addAttribute("userId", userId);
		model.addAttribute("boardDetail", boardDetail);
		model.addAttribute("view", "board/boardDetail");
		return "template/layout";
	}
	
	//수정페이지
	@GetMapping("/board_update_view")
	public String boardUpdateView(
			@RequestParam("boardId") int boardId,
			HttpSession session,
			Model model) {
		
		// session
		int userId = (int)session.getAttribute("userId");
		
		// db select
		Board boardDetail = boardBO.getBoardById(boardId);
		
		model.addAttribute("userId", userId);
		model.addAttribute("boardDetail", boardDetail);
		model.addAttribute("view", "board/boardModify");
		return "template/layout";
	}
}











