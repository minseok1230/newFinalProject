package com.soccer.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.soccer.board.bo.BoardBO;

import lombok.Data;

@Data
public class Paging {
	
	@Autowired
	private BoardBO boardBO ;
	
	private List<Integer> currentPageList;
	private int pageCount;              // 현재 필요한 페이지 갯수    
	private int clickPageNum;           // 현재 클릭된 페이지
	private int currentPageNum;         // 현재 페이지 번호    
	private int startPageNum;           // 페이지에서 시작 번호 
	private int boardStartNum;          // 게시물 시작 번호 
    private int totalCount;             // 게시물 총 갯수           
    private boolean prev = true;        // 이전 버튼 유무      
    private boolean next = true;        // 다음 버튼 유무 
    private String type;                // 페이징할 글 유형 
    private List<Object> result;        // 결과값 
 
    
	public void PagingList(String type, int clickPageNum, int POST_MAX_SIZE, int PAGE_MAX_SIZE) {
		
		this.type = type;
		this.clickPageNum = clickPageNum;
		
		/* 1 2 3 4 5 / 6 7 8 9 10 / 11 12 13*/
		this.currentPageNum = (int) Math.ceil((double)clickPageNum / PAGE_MAX_SIZE);
		
		/* 총 게시글 수 : 마지막 NEXT버튼 구현하기 위해 */
		// 35 / 4 = 8.xx
		this.pageCount = (int) Math.ceil((double)this.totalCount / POST_MAX_SIZE);
		
		/* 1: [1,2,3,4,5] , 2: [6,7,8,9,10] ....*/
		List<Integer> currentPageList = new ArrayList<>();
		for (int i = PAGE_MAX_SIZE; i > 0; i--) {
			currentPageList.add(PAGE_MAX_SIZE * this.currentPageNum - i + 1);
			if (currentPageList.contains(this.pageCount)) {
				next = false;
				break;
			};
		};
		this.currentPageList = currentPageList;
		
		/* 앞 뒤 버튼 유무 */
		if (currentPageList.contains(1)) {
			prev = false;
		}
		
		// 각 페이지에서 시작 게시물번호 
		this.boardStartNum = POST_MAX_SIZE * clickPageNum - POST_MAX_SIZE;
		
		this.startPageNum = PAGE_MAX_SIZE * this.currentPageNum - PAGE_MAX_SIZE + 1;
		
//		switch(type) {
//	    case "게시물": this.totalCount = 
//	         break;
//	    case "공지사항": ...
//	         break;
//	    ...
//	    default: ...
//	         break;
//		}
	}

	
}
