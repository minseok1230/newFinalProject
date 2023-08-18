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
import com.soccer.board.bo.BoardService;
import com.soccer.board.domain.Board;
import com.soccer.board.domain.BoardView;
import com.soccer.common.Paging;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardBO boardBO;
	
	@Autowired
	private BoardService boardService;
	
	// 글 목록 (더보기 클릭)
	@GetMapping("/board_list_view")
	public String boardListView(
			@RequestParam("type") String type,
			@RequestParam(value = "clickPage", required = false) Integer clickPage,
			Model model) {
		
		String boardType = type;
		List<BoardView> boardViewList = boardService.generateBoardViewList(type, clickPage);
		Paging pageMaker = boardService.pageMaker(type, clickPage);
		
		
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("boardViewList", boardViewList);
		model.addAttribute("boardType", boardType);
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
		
		// db select
		BoardView board = boardService.generateBoard(boardId);
		int userId = (int)session.getAttribute("userId");
		
		model.addAttribute("userId", userId);
		model.addAttribute("board", board);
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











