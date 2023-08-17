package com.soccer.match;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.match.bo.MatchBO;

@RestController
@RequestMapping("/match")
public class MatchRestController {

	
	@Autowired
	private MatchBO matchBO;
	
	
	/**
	 * 매칭 만들기
	 * @param reservationId
	 * @param title
	 * @param teamName
	 * @param price
	 * @param content
	 * @param session
	 * @return
	 */
	@PostMapping("/create_match")
	public Map<String, Object> createMatch(
			@RequestParam("reservationId") int reservationId,
			@RequestParam("title") String title,
			@RequestParam("teamName") String teamName,
			@RequestParam("price") int price,
			@RequestParam("content") String content,
			HttpSession session
			){
		
		int teamId = (int)session.getAttribute("userTeamId");
		Map<String, Object> result = new HashMap<>();

		//db insert
		int insertMatch = matchBO.addMatch(teamId, reservationId, title, price, content);
		if (insertMatch > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("errorMessgae", "팀매칭등록에 실패하였습니다.");
		}
		
		return result;
	}
	
	// 매칭 수정
	@PutMapping("/{matchId}")
	public Map<String, Object> updateMatch(
			@PathVariable int matchId,
			@RequestParam("title") String title,
			@RequestParam("price") int price,
			@RequestParam("content") String content
			){
		
		Map<String, Object> result = new HashMap<>();
		
		// db update
		int updateResult = matchBO.updateMatchById(matchId, title, price, content);
		if (updateResult >0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("errorMessage", "매칭글 수정에 실패하였습니다.");
		}
		
		
		
		return result;
		
	}
}


















