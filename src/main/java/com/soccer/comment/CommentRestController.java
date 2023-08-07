package com.soccer.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.comment.bo.CommentBO;

@RestController
@RequestMapping("/comment")
public class CommentRestController {
	
	
	@Autowired
	private CommentBO commentBO;
	
	@PutMapping("/{type}/{boardId}")
	public Map<String, Object> commentCreate(
			@PathVariable String type,
			@PathVariable int boardId,
			@RequestParam("content") String content,
			HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		
		// 로그인 여부 확인
		if (userId == null) {
			result.put("code", 300);
			result.put("errorMessage", "로그인이 필요합니다.");
			return result;
		}
		
		int rowCount = commentBO.addComment(userId, boardId, type, content);
		if (rowCount > 0) {
			result.put("code", 1);
			result.put("result", "성공"); 
		} else {
			result.put("code", 500);
			result.put("errorMessage", "댓글 작성에 실패했습니다.");
		}
		return result;
	}

}
