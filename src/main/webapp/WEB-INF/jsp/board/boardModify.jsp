<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="w-75">
		<h4 class=" mt-5 font-weight-bold">제목</h4>
		<div class="d-flex mb-4">
			<input type="text" id="subject" class="form-control" placeholder="제목을 입력하세요" value="${boardDetail.title}">		
		</div>
		
		<h4 class="font-weight-bold">내용</h4>
		<textarea id="content" class="form-control" rows="10" placeholder="내용을 입력하세요">${boardDetail.content}</textarea>
		
		<br><br>
		
		<div class="d-flex justify-content-end">
			<button type="submit" id="updateBoardBtn" class="btn btn-primary mr-3" data-board-id ="${boardDetail.id}" data-type = "${boardDetail.type}">수정하기</button>
		</div>
	</div>
</div>

<script>
	$(document).ready(function(){
		
		/* 글 수정 */
		$('#updateBoardBtn').on('click', function(){
			let boardId = $(this).data('board-id');
			let boardType = $(this).data('type');
			let subject = $('#subject').val();
			let content = $('#content').val();
			
			if (!subject){
				alert("제목을 입력해주세요");
				return false;
			}
			
			if (!content){
				alert("내용을 입력해주세요");
				return false;
			}
			
			$.ajax({
				//request
				type : "put"
				, url : "/board/" + boardId
				, data : {"subject":subject, "content":content}
			
				//response
				, success : function(data){
					if (data.code == 1){
						alert("글 수정에 성공하였습니다.")
						location.href = "/board/board_list_view?type=" + boardType;
					} else{
						alert(data.errorMessage);
					}
				}
				
				,error : function(request, ststus, error){
					alert("글수정에 실패했습니다. 관리자 문의 바랍니다.");
				}
				
			});
		});
		
		
		/* 글 삭제 */
		
	});
</script>




