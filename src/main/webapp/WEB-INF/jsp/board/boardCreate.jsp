<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="w-75">
		<c:if test="${boardType eq '공지사항'}">
		
			<h3 class="font-weight-bold mt-5 mb-4">글쓰기(공지사항)</h3>
		</c:if>
		<c:if test="${boardType eq '게시판'}">
			<h3 class="font-weight-bold mt-5 mb-4">글쓰기(게시판)</h3>
		</c:if>
		
		<!-- 게시판 / 공지사항 제목, 내용 -->
		<input type="text" id="title" name="title" class="form-control w-50" placeholder="제목"><br>
		<textarea class="form-control" rows="15" id="content" name="content" placeholder="내용"></textarea>
		
		
		<!-- 목록 / 등록 버튼 -->
		<div class="d-flex justify-content-between mt-4">
			<a href="/board/board_list_view?type=${boardType}" class="btn btn-secondary">목록</a>
			<button type="submit" id="createBoardBtn" class="btn btn-secondary" data-type="${boardType}">등록</button>
		</div>
	</div>
</div>

<script>
$(document).ready(function(){
	$('#createBoardBtn').on('click', function(){
		let title = $('#title').val().trim();
		let content = $('#content').val();
		let type = $(this).data('type');
		
		if (!title){
			alert("제목을 입력하세요");
			return false;
		}
		
		if (!content){
			alert("내용을 입력하세요");
			return false;
		}
		
		$.ajax({
			//request
			
			type: "post"
			, url: "/board/create_board"
			, data : {"title":title, "content":content, "type":type}
		
			// response
			,success: function(data){
				
				if(data.code == 1){
					alert("글등록에 성공하였습니다.");
					location.href = "/board/board_list_view?type=" + type;
				} else{
					alert(data.errorMessage);
				}
			}
		
			, error: function(request, status, error){
				alert("글등록에 실패했습니다. 관리자 문의 바랍니다.");
			}
			
		});
	});
	
});

</script>