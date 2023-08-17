package com.soccer.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.soccer.board.bo.BoardBO;
import com.soccer.board.domain.Board;


public class Paging {
	
	
	@Autowired
	private BoardBO boardBO;
	
	private static final int POST_MAX_SIZE = 3;
	private static final int PAGE_MAX_SIZE = 5;
	
	private Map<Integer, List<Integer>> currentPage; //          ㅇ
	private int pageCount;              // 현재 필요한 페이지 갯수    o    
	private int totalPage;              // 총 페이지 갯수        o
	private int clickPageNum;           // 현재 클릭된 페이
	private int currentPageNum;         // 현재 페이지 번호    o 
	private int startPageNum;           // 페이지에서 시작 번호 
    private int totalCount;             // 게시물 총 갯수           o
    private boolean prev;               // 이전 버튼 유무      
    private boolean next;               // 다음 버튼 유무 
    private String type;                // 페이징할 글 유형 
 
    
    // total : 47개 , page: 47 / POST_MAX_SIZE = 18개 , pageNum = 18개 / 5 = 3.6-> 4 개
    //1 2 3 4 5 // 6 7 8 9 10
	public void PagingList(String type, int pageNum) {
		this.type = type;
		this.currentPageNum = pageNum;
		
		List<Integer> currentPageList = new ArrayList<>();
		for (int i = PAGE_MAX_SIZE; i > 0; i++) {
			currentPageList.add(PAGE_MAX_SIZE * pageNum - i + 1);
			// 1 2 3 4 5 ,,, 6 7 8 9 10 , , , 11 12 13 14 15
		}
		
		this.startPageNum = PAGE_MAX_SIZE * pageNum - 4;
		this.currentPage.put(pageNum, currentPageList);
		
//		switch(type) {
//	    case "게시물": this.totalCount = 
//	         break;
//	    case "공지사항": ...
//	         break;
//	    ...
//	    default: ...
//	         break;
//		}
		
		this.totalCount = boardBO.getBoardCount("게시판"); // 게시물, 공지사항, 팀목록, 팀매칭 
		this.pageCount = this.totalCount / POST_MAX_SIZE;
		this.totalPage = this.pageCount / PAGE_MAX_SIZE;
	}


//	public int startListNum() {
//    	return (currentPageNum - 1) * POST_MAX_SIZE;
//    	// currentPageNum 은 1부터 입력! 초기값 설정 0보다 같거나 작을 수 없다.
//    }
    
    // 최종 결과물 return
	// 현재 페이지 1 : 1,2,3,4,5    // 2: 6,7,8,9,10
    private List<Board> boardList(int clickPage){
    	 this.clickPageNum = (clickPage - 1) * POST_MAX_SIZE;
    	 return boardBO.get boardMapper.selectBoardByTypeByPageNum(type, this.clickPageNum, POST_MAX_SIZE);
    }
    		
    
    
    //  123 / 456 / 789 / 101112/ 131415/ 161718/ 192021 / 2223 
	
}
