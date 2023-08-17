<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="d-flex justify-content-center">
	<div class="w-75">
		<div class="d-flex  justify-content-between mt-5 mb-4">
			<h3 class="font-weight-bold ">${board.board.title}</h3>
			<small class="text-primary mt-4 ml-4">작성자 : ${board.user.name}</small>
		</div>
			
		<textarea class="form-control" rows="10" readonly>${board.board.content}</textarea>
			
		<br><br>
		
		<!-- 댓글 등록 -->
		<c:if test="${board.board.type eq '게시판'}">
			<span class="font-weight-bold">댓글</span>	
			<br><br>
			<div class="d-flex">
				<input type="text" class="form-control w-75" id="content" name="content" placeholder="댓글을 입력하세요">
				<button type="submit" class="btn btn-info" id="commentSubmitBtn" name="commentSubmitBtn">댓글등록</button>
			</div>	
			
			<!-- 댓글 목록 -->
			<c:forEach items="${board.commentList}" var="comment">
				<div>
					<div class="d-flex mt-3">
						<fmt:parseDate value="${comment.comment.createdAt}" pattern="yyyy-MM-d'T'HH:mm:ss" var="parsedCreatedAt"/>
						
						<div class="mr-2">${comment.user.name} </div>
					</div>
					<div class="commentDate d-flex mt-1">
						<div><fmt:formatDate value="${parsedCreatedAt}" pattern="yyyy.M.d(E) HH:mm"/></div>
						<div class="ml-2"><a href="#">대댓글</a></div>
					</div>
					<div class="d-flex mt-2">
						<small class="text-secondary font-weight-bold">${comment.comment.content}</small>
						<c:if test="${comment.comment.userId == userId}">
							<small class="ml-3"><a href="#" class="deleteCommentBtn" data-comment-id="${comment.comment.id}">X</a></small>
						</c:if>
					</div>
					<hr>
				</div>
			</c:forEach>
		</c:if>
		
		<!-- 목록 / 수정 / 삭제 버튼 -->
		<div class="d-flex justify-content-between mt-4">
			<a href="/board/board_list_view?type=${board.board.type}" class="btn btn-secondary">목록</a>
			<c:if test="${board.board.userId == userId}">
				<div class="d-flex">
					<a href="/board/board_update_view?boardId=${board.board.id}" id="updateBoardBtn" class="btn btn-secondary mr-3">수정</a>
					<button type="submit" id="deleteBoardBtn" class="btn btn-secondary" data-board-id = ${board.board.id}>삭제</button>
				</div>
			</c:if>
		</div>
	</div>
</div>

<script>
$(document).ready(function(){
	
	// 댓글 등록
	$('#commentSubmitBtn').on('click', function(){
		let content = $('#content').val();
		let type = "${board.board.type}";
		let boardId = ${board.board.id};
		
		$.ajax({
			type : "put"	
			,url : "/comment/" + type + "/" + boardId
			,data : {"content" : content}
			
			,success: function(data){
				if (data.code == 1){
					location.reload();
				} else{
					alert(data.errorMessage);
				}
			}
			
			, error: function(request, status, error){
				alert("댓글등록에 실패했습니다. 관리자 문의 바랍니다.");
			}
			
		});
	});
	
	//댓글 삭제
	$('.deleteCommentBtn').on('click', function(e){
		e.preventDefault();
		let commentId = $(this).data("comment-id");
		
		$.ajax({
			type: "delete"
			, url : "/comment/" + commentId
			
			, success: function(data){
				if (data.code == 1){
					location.reload();
				} else{
					alert(data.errorMessage);
				}
			}
		
			, error : function(request, status, error){
				alert("댓글 삭제 실패했습니다. 관리자 문의 바랍니다.")
			}
		});
	});
	
	// 글 삭제
	$('#deleteBoardBtn').on('click', function(){
		let boardId = $(this).data("board-id")
		let type = "${board.board.type}"
		
		$.ajax({
			
			type: "delete"
			,url: "/board/" + boardId
			
			, success: function(data){
				if (data.code == 1){
					alert("글 삭제 성공");
					location.href = "/board/board_list_view?type=" + type;
				} else{
					alert(data.errorMessage);
				}
			}
			
			, error: function(request, status, error){
				alert("글 삭제에 실패하였습니다. 관리자에게 문의 바랍니다.");
			}
		});
	});
	
});
</script>















