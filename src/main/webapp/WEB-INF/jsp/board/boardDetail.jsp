<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="w-75">
		<div class="d-flex  mt-5 mb-4">
			<h3 class="font-weight-bold ">${boardDetail.title}</h3>
			<h5 class="font-weight-bold mt-2 ml-4">${boardDetail.id}</h5>
		</div>
			
		<div class="form-control h-50">
			${boardDetail.content}
		</div>
		<br><br>
		
		<!-- 댓글 등록 -->
		<c:if test="${boardDetail.type eq '게시판'}">
			<span class="font-weight-bold">댓글</span>	
			<br><br>
			<div class="d-flex">
				<input type="text" class="form-control w-75" id="content" name="content" placeholder="댓글을 입력하세요">
				<button type="submit" class="btn btn-info" id="commentSubmitBtn" name="commentSubmitBtn">댓글등록</button>
			</div>	
			
			<!-- 댓글 목록 -->
			<!-- 예시1 -->
			<div>
				<div class="d-flex mt-3">
					<div class="mr-2">최민석 : (2020.01.05  11:30)</div>
					<small class="mt-1"><a href="#">대댓글</a></small>
				</div>
				<div class="d-flex">
					<small>오늘 매칭 가능하신가요??</small>
					<small class="ml-3"><a href="#">X</a></small>
				</div>
				<hr>
			</div>
			<!-- 예시2 -->
			<div>
				<div class="d-flex mt-3">
					<div class="mr-2">홍길동 : (2020.01.05  11:30)</div>
					<small class="mt-1"><a href="#">대댓글</a></small>
				</div>
				<div class="d-flex">
					<small>실력이 어떠신가요??</small>
					<small class="ml-3"><a href="#">X</a></small>
				</div>
				<hr>
			</div>
		</c:if>
		
		<!-- 목록 / 수정 / 삭제 버튼 -->
		<div class="d-flex justify-content-between mt-4">
			<a href="/board/board_list_view" class="btn btn-secondary">목록</a>
			<c:if test="${boardDetail.userId == userId}">
				<div class="d-flex">
					<a href="/board/board_update_view?boardId=${boardDetail.id}" id="updateBoardBtn" class="btn btn-secondary mr-3">수정</a>
					<button type="submit" id="deleteBoardBtn" class="btn btn-secondary">삭제</button>
				</div>
			</c:if>
		</div>
	</div>
</div>