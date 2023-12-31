package com.soccer.board;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.board.bo.BoardBO;

@RestController
@RequestMapping("/board")
public class BoardRestController {

	@Autowired
	private BoardBO boardBO;
	
	/**
	 * 글쓰기
	 * @param title
	 * @param content
	 * @param type
	 * @param session
	 * @return
	 */
	@PostMapping("/create_board")
	public Map<String, Object> createBoard(
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("type") String type,
			HttpSession session
			){
		
		int userId = (int)session.getAttribute("userId");
		
		// db insert
		int insertResult =  boardBO.addBoardByTitleContentUserId(userId, type, title, content);
		Map<String, Object> result = new HashMap<>();
		
		if (insertResult > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "글등록에 실패하였습니다.");
		}
		
		return result;
	}
	
	/**
	 * 글 수정
	 * @param boardId
	 * @param subject
	 * @param content
	 * @param session
	 * @return
	 */
	@PutMapping("/{boardId}")
	public Map<String, Object> updateBoard(
			@PathVariable int boardId,
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			HttpSession session){
		
		int userId = (int)session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		// db 수정
		int insertResult = boardBO.updateBoardByIdAndUserId(boardId, userId, subject, content);
		
		if(insertResult > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "글 수정에 실패하였습니다.");
		}
		return result;
	}
	
	// 글 삭제
	@DeleteMapping("/{boardId}")
	public Map<String, Object> deleteBoard(
			@PathVariable int boardId,
			@PathVariable String type,
			HttpSession session){
		Map<String, Object> result = new HashMap<>();
		
		// 글 삭제
		int userId = (int)session.getAttribute("userId");
		int deleteResult = boardBO.deleteBoardByIdAndUserId(boardId, userId, type);
		
		if(deleteResult > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "글삭제에 실패하였습니다.");
		}
		return result;
	}
}













