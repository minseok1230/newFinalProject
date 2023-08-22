package com.soccer.match.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.comment.bo.CommentBO;
import com.soccer.matchRelation.bo.MatchRelationBO;

@Service
public class MatchDeleteBO {

	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private MatchRelationBO matchRelationBO;
	
	@Autowired
	private MatchBO matchBO;
	
	public int deleteMatchById(int matchId) {

		// comment들 삭제
		String type = "매칭글";
		commentBO.deleteCommentByBoardIdAndType(matchId, type);

		// matchRelation 삭제
		matchRelationBO.deleteMatchRelationByMatchId(matchId);

		// match삭제
		return matchBO.deleteMatchById(matchId);
	}
}
