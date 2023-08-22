package com.soccer.board.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.board.domain.Board;
import com.soccer.board.domain.BoardView;
import com.soccer.comment.bo.CommentBO;
import com.soccer.comment.domain.CommentView;
import com.soccer.common.PageMaker;
import com.soccer.common.Paging;
import com.soccer.user.bo.UserBO;
import com.soccer.user.domain.User;

@Service
public class BoardService {
	
		private static final int POST_MAX_SIZE = 3;
		private static final int PAGE_MAX_SIZE = 4;
	
		@Autowired
		private BoardBO boardBO;
	
		@Autowired
		private UserBO userBO;
	
		@Autowired
		private CommentBO commentBO;
	
		/* boardViewList 만들기 */
		public Map<String, Object> generateBoardViewList(String type, Integer clickPageNum){
			Map<String, Object> result = new HashMap<>(); 
			
			// 리턴 값 ( 여러개의 BoardView )
			List<BoardView> boardViewList = new ArrayList<>();
			
			Integer clickPage;
			if (clickPageNum == null) {
				clickPage = 1;
			} else {
				clickPage = clickPageNum;
			}
			
			// 페이징
			Paging paging = new Paging();
			
			// total 구해주기 
			int total = boardBO.getBoardCount(type);
			paging.setTotalCount(total);
			paging.PagingList( clickPage, POST_MAX_SIZE, PAGE_MAX_SIZE );
			List<Board> boardList = boardBO.getBoardByTypeByPageNum(type, paging.getBoardStartNum(), POST_MAX_SIZE);
			
			PageMaker pageMaker = new PageMaker();;
			pageMaker.setPaging(paging);
			
			for (Board board : boardList) {
				BoardView boardView = new BoardView();
				
				// 글 세팅
				boardView.setBoard(board);
				
				// 사용자 정보 
				User user = userBO.getUserById(board.getUserId());
				boardView.setUser(user);
				
				// 댓글 세팅
				List<CommentView> commentViewList = commentBO.generateCommentViewList(board.getId());
				boardView.setCommentList(commentViewList);
				
				boardViewList.add(boardView);
			}
			
			result.put("boardViewList", boardViewList);
			result.put("pageMaker", pageMaker);
			
			
			return result;
		}
		
		
		/* 페이징  component로 빼서 따로 분리시켜보자 */ 
		public Paging pageMake(Paging paging) {
			
			return paging;
		}
		
		/* 글 한개 boardView */
		public BoardView generateBoard(int boardId) {
			BoardView boardView = new BoardView();
			Board board = boardBO.getBoardById(boardId);
			boardView.setBoard(board);
			
			User user = userBO.getUserById(board.getUserId());
			boardView.setUser(user);
			
			List<CommentView> commentViewList = commentBO.generateCommentViewList(board.getId());
			boardView.setCommentList(commentViewList);
			
			return boardView;
		}
}
