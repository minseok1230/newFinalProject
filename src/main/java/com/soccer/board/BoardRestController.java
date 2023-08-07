package com.soccer.board;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.board.bo.BoardBO;

@RestController
@RequestMapping("/board")
public class BoardRestController {

	@Autowired
	private BoardBO boardBO;
	
	// 글쓰기 (게시판 , 공지사항)
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
	
	
	
}













